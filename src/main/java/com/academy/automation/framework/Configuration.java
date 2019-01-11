package com.academy.automation.framework;

public class Configuration {

    // for 'chrome' driver
    private String chromeDriverLocation;
    // for 'firefox' browser
    private String geckoDriverLocation;

    private int implicitlyWait;
    private String baseUrl;
    private String login;
    private String password;

    private boolean logHttp = false;
    private boolean logPerformance = false;
    private boolean logBrowser = false;

    private String screenShotLocation;

    private boolean uiMode = true;
    private boolean bdMode = false;
    private boolean restMode = false;
    private boolean soapMode = false;

    private String jdbcDriver;
    private String jdbcUrl;

    private String restPath;

    public String getChromeDriverLocation() {
        return chromeDriverLocation;
    }

    public void setChromeDriverLocation(String chromeDriverLocation) {
        this.chromeDriverLocation = chromeDriverLocation;
    }

    public String getGeckoDriverLocation() {
        return geckoDriverLocation;
    }

    public void setGeckoDriverLocation(String geckoDriverLocation) {
        this.geckoDriverLocation = geckoDriverLocation;
    }

    public boolean isLogHttp() {
        return logHttp;
    }

    public void setLogHttp(boolean logHttp) {
        this.logHttp = logHttp;
    }

    public boolean isLogPerformance() {
        return logPerformance;
    }

    public void setLogPerformance(boolean logPerformance) {
        this.logPerformance = logPerformance;
    }

    public boolean isLogBrowser() {
        return logBrowser;
    }

    public void setLogBrowser(boolean logBrowser) {
        this.logBrowser = logBrowser;
    }

    public String getScreenShotLocation() {
        return screenShotLocation;
    }

    public void setScreenShotLocation(String screenShotLocation) {
        this.screenShotLocation = screenShotLocation;
    }

    public int getImplicitlyWait() {
        return implicitlyWait;
    }

    public void setImplicitlyWait(int implicitlyWait) {
        this.implicitlyWait = implicitlyWait;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isUiMode() {
        return uiMode;
    }

    public void setUiMode(boolean uiMode) {
        this.uiMode = uiMode;
    }

    public boolean isBdMode() {
        return bdMode;
    }

    public void setBdMode(boolean bdMode) {
        this.bdMode = bdMode;
    }

    public boolean isRestMode() {
        return restMode;
    }

    public void setRestMode(boolean restMode) {
        this.restMode = restMode;
    }

    public boolean isSoapMode() {
        return soapMode;
    }

    public void setSoapMode(boolean soapMode) {
        this.soapMode = soapMode;
    }

    public String getJdbcDriver() {
        return jdbcDriver;
    }

    public void setJdbcDriver(String jdbcDriver) {
        this.jdbcDriver = jdbcDriver;
    }

    public String getJdbcUrl() {
        return jdbcUrl;
    }

    public void setJdbcUrl(String jdbcUrl) {
        this.jdbcUrl = jdbcUrl;
    }

    public String getRestPath() {
        return restPath;
    }

    public void setRestPath(String restPath) {
        this.restPath = restPath;
    }

    // withXXX methods
    public Configuration withChromeDriverLocation(String chromeDriverLocation) {
        this.chromeDriverLocation = chromeDriverLocation;
        return this;
    }

    public Configuration withGeckoDriverLocation(String geckoDriverLocation) {
        this.geckoDriverLocation = geckoDriverLocation;
        return this;
    }

    public Configuration withLogHttp(boolean logHttp) {
        this.logHttp = logHttp;
        return this;
    }

    public Configuration withLogPerformance(boolean logPerformance) {
        this.logPerformance = logPerformance;
        return this;
    }

    public Configuration withLogBrowser(boolean logBrowser) {
        this.logBrowser = logBrowser;
        return this;
    }

    public Configuration withScreenShotLocation(String screenShotLocation) {
        this.screenShotLocation = screenShotLocation;
        return this;
    }

    public Configuration withImplicitlyWait(int implicityWait) {
        this.implicitlyWait = implicityWait;
        return this;
    }

    public Configuration withBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
        return this;
    }

    public Configuration withLogin(String login) {
        this.login = login;
        return this;
    }

    public Configuration withPassword(String password) {
        this.password = password;
        return this;
    }

    public Configuration withUiMode(boolean uiMode) {
        this.uiMode = uiMode;
        return this;
    }

    public Configuration withBdMode(boolean bdMode) {
        this.bdMode = bdMode;
        return this;
    }

    public Configuration withRestMode(boolean restMode) {
        this.restMode = restMode;
        return this;
    }

    public Configuration withSoapMode(boolean soapMode) {
        this.soapMode = soapMode;
        return this;
    }

    public Configuration withJdbcDriver(String jdbcDriver) {
        this.jdbcDriver = jdbcDriver;
        return this;
    }

    public Configuration withJdbcUrl(String jdbcUrl) {
        this.jdbcUrl = jdbcUrl;
        return this;
    }

    public Configuration withRestPath(String restPath) {
        this.restPath = restPath;
        return this;
    }
}
