package com.academy.automation.framework;

import io.restassured.RestAssured;
import io.restassured.config.LogConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.io.IoBuilder;

import static io.restassured.RestAssured.config;

public abstract class BaseRestManager {
    protected static final Logger LOG = LogManager.getLogger(BaseRestManager.class);

    private Configuration cfg;

    private String baseUrlWithoutPort;
    private int port;

    public BaseRestManager(Configuration cfg) {
        this.cfg = cfg;
    }

    private void parseUrlAndPort(String rawUrl) {
        try {
            String[] parts = rawUrl.split(":");
            baseUrlWithoutPort = parts[0]+":"+parts[1];
            port = Integer.valueOf(parts[2]);
        } catch (NumberFormatException e) {
            LOG.error("Error parsing url {}. Details:", rawUrl, e.getMessage());
        }
    }

    public void init() {
        parseUrlAndPort(cfg.getBaseUrl());

        RestAssured.baseURI = baseUrlWithoutPort + cfg.getRestPath();
        RestAssured.port = port;

        config = config()
                .logConfig(new LogConfig()
                        .defaultStream(IoBuilder.forLogger(LOG).buildPrintStream()));

        initHelpers();
    }

    public void stop() {
    }

    protected abstract void initHelpers();

    public boolean isRestMode() {
        return cfg.isRestMode();
    }
}
