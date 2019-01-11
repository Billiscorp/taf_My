package com.academy.automation.framework;

import com.google.common.io.Files;
import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.client.ClientUtil;
import net.lightbody.bmp.core.har.Har;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

public abstract class BaseUiManager {
    protected static final Logger LOG = LogManager.getLogger(BaseUiManager.class);
    protected static final Logger LOG_BROWSER = LogManager.getLogger("BROWSER");
    protected static final Logger LOG_PERFORMANCE = LogManager.getLogger("PERFORMANCE");
    protected static final Logger LOG_TRAFFIC = LogManager.getLogger("TRAFFIC");

    protected Configuration cfg;
    protected EventFiringWebDriver driver;
    private BrowserMobProxy proxy;

    public BaseUiManager(Configuration cfg) {
        this.cfg = cfg;
    }

    public EventFiringWebDriver getDriver() {
        return driver;
    }

    public boolean isUiMode() {
        return this.cfg.isUiMode();
    }

    public void init(String browser) throws IOException {
        switch (browser) {
            case "chrome":
                System.setProperty("webdriver.chrome.driver", cfg.getChromeDriverLocation());

                ChromeOptions options = new ChromeOptions();

                // performance
                if (cfg.isLogPerformance()) {
                    LoggingPreferences logPrefs = new LoggingPreferences();
                    logPrefs.enable(LogType.PERFORMANCE, Level.ALL);
                    options.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);
                }

                // proxy
                if (cfg.isLogHttp()) {
                    proxy = new BrowserMobProxyServer();
                    proxy.start(0);

                    // get the Selenium proxy object
                    Proxy seleniumProxy = ClientUtil.createSeleniumProxy(proxy);

                    // configure it as a desired capability
                    options.setCapability(CapabilityType.PROXY, seleniumProxy);
                    proxy.newHar("automation");
                }

                // start the browser up
                driver = new EventFiringWebDriver(new ChromeDriver(options));

                break;

            case "firefox":
                System.setProperty("webdriver.gecko.driver", cfg.getGeckoDriverLocation());
                driver = new EventFiringWebDriver(new FirefoxDriver());
                break;
        }

        driver.register(new DetailWebDriverEventListener());
        driver.manage().timeouts().implicitlyWait(cfg.getImplicitlyWait(), TimeUnit.SECONDS);
        //        driver.manage().window().maximize();
        initHelpers();
    }

    public void stop() {
        if (cfg.isLogHttp()) {
            Har har = proxy.endHar();
            har.getLog().getEntries().forEach(l -> LOG_TRAFFIC.debug(l.getResponse().getStatus() + ":" + l.getRequest().getUrl()));
        }

        driver.quit();
    }

    protected abstract void initHelpers();

    class DetailWebDriverEventListener extends AbstractWebDriverEventListener {

        @Override
        public void beforeFindBy(By by, WebElement element, WebDriver driver) {
            LOG.debug("Try find by {}", by);
        }

        @Override
        public void afterFindBy(By by, WebElement element, WebDriver driver) {
            LOG.debug("Found by {}", by);
        }

        @Override
        public void onException(Throwable err, WebDriver driver) {
            LOG.error("Error occurs: {}", err);

            makeScreenshot();
        }

        @Override
        public void afterNavigateTo(String url, WebDriver driver) {
            LOG.debug("Navigated to {}", url);

            if (cfg.isLogBrowser()) {
                LOG_BROWSER.debug("Navigated to {}", url);
                driver.manage().logs().get("browser").forEach(LOG_BROWSER::debug);
            }

            if (cfg.isLogPerformance()) {
                LOG_PERFORMANCE.debug("Navigated to {}", url);
                driver.manage().logs().get("performance").forEach(LOG_PERFORMANCE::debug);
            }
        }

        private void makeScreenshot() {
            File tmp = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String screenName = "screen_" + System.currentTimeMillis()+".png";
            String screenPath = cfg.getScreenShotLocation() + "/" + screenName;
            File screen = new File(screenPath);
            try {
                Files.copy(tmp, screen);
            } catch (IOException exc) {
                LOG.error("Error copying screenshot from '{}' to '{}'. Details: {}",
                        tmp, screen, exc);
            }
        }
    }
}
