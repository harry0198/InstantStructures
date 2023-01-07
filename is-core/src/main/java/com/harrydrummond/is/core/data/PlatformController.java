package com.harrydrummond.is.core.data;

import com.harrydrummond.is.core.configuration.ConfigurationManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.google.common.base.Preconditions.checkNotNull;

public class PlatformController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PlatformController.class);

    private Platform preferredPlatform;
    private ConfigurationManager configurationManager;

    public PlatformController() {}

    public Platform getPreferredPlatform() {
        return preferredPlatform;
    }

    public ConfigurationManager getConfigurationManager() {
        return configurationManager;
    }

    public synchronized void registerPlatform(final Platform platform) {
        checkNotNull(platform);

        preferredPlatform = platform;
        this.configurationManager = new ConfigurationManager(platform.getDataFolder());
        LOGGER.info("Registered platform [" + platform.getClass().getCanonicalName() + "]");
    }

    public synchronized void unregisterPlatform(final Platform platform) {
        if (preferredPlatform == platform) {
            preferredPlatform = null;
        }
    }
}