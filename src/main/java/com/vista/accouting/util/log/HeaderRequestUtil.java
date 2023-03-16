package com.vista.accouting.util.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Locale;

public final class HeaderRequestUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(HeaderRequestUtil.class);

    private HeaderRequestUtil() {}


    public static final Locale extractLocale(String lang) {
        return getLocale(lang);
    }

    private static final Locale getLocale(String lang) {
        try {
            String[] split = lang.split("-");
            if (split.length > 1) {
                return new Locale(split[0], split[1]);
            } else {
                String language = split[0];
                if (language.equalsIgnoreCase("fa")) {
                    return new Locale(language, "IR");
                } else {
                    return new Locale(language, "US");
                }
            }
        } catch (Exception e) {
            LOGGER.trace("Fallback to default locale", e);
            return new Locale("fa", "IR");
        }
    }
}
