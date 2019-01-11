package com.academy.automation.framework.test;

import com.academy.automation.framework.BaseBDManager;
import com.academy.automation.framework.BaseRestManager;
import com.academy.automation.framework.BaseUiManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.*;

import java.lang.reflect.Method;
import java.util.Arrays;

public class BaseTestInitializer {
    protected static final Logger LOG = LogManager.getLogger(BaseTestInitializer.class);
    private BaseUiManager uiManager;
    private BaseBDManager bdManager;
    private BaseRestManager restManager;

    protected void registerUIManager(BaseUiManager uiManager) {
        this.uiManager = uiManager;
    }

    protected void registerBDManager(BaseBDManager bdManager) {
        this.bdManager = bdManager;
    }

    protected void registerRestManager(BaseRestManager restManager) {
        this.restManager = restManager;
    }

    protected boolean ifUiMode() {
        return uiManager.isUiMode();
    }

    protected boolean ifBdMode() {
        return bdManager.isBdMode();
    }

    protected boolean ifRestMode() {
        return restManager.isRestMode();
    }

    @Parameters("browser")
    @BeforeClass(alwaysRun = true)
    public void setUp(@Optional("chrome")String browser) throws Exception {
        if (uiManager != null)
            uiManager.init(browser);

        if (bdManager != null)
            bdManager.init();

        if (restManager != null)
            restManager.init();
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() throws Exception {
        if (uiManager != null)
            uiManager.stop();

        if (bdManager != null)
            bdManager.stop();

        if (restManager != null)
            restManager.stop();
    }

    @BeforeMethod
    public void logTestStart(Method method, Object[] params) {
        LOG.info("Start test {} with parameters {}",
                method.getName(), Arrays.toString(params));
    }

    @AfterMethod(alwaysRun = true)
    public void logTestStop(Method method) {
        LOG.info("Stop test {}", method.getName());
    }
}
