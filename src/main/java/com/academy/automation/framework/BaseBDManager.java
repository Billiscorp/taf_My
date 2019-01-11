package com.academy.automation.framework;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class BaseBDManager {
    protected static final Logger LOG = LogManager.getLogger(BaseBDManager.class);

    private Configuration cfg;

    public BaseBDManager(Configuration cfg) {
        this.cfg = cfg;
    }

    public void init() {
        try {
            Class.forName(cfg.getJdbcDriver());
        } catch (ClassNotFoundException e) {
            LOG.error("Error initializing jdbc connection. Details: {}", e.getMessage());
        }

        initHelpers();
    }

    public void stop() {
    }

    protected abstract void initHelpers();

    public boolean isBdMode() {
        return cfg.isBdMode();
    }

    public String jdbcUrl() {
        return cfg.getJdbcUrl();
    }
}
