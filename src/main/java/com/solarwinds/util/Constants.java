package com.solarwinds.util;

/**
 * A class to hold constants
 */
public class Constants {

    public static final String BASE_URL;
    public static final String API_URL;
    public static final String REFRESH_TOKEN ;
    public static final boolean DEBUG;

    static {
        ClientProperties properties = new ClientProperties();
        BASE_URL = properties.getProperty(ClientProperties.BASE_URL);
        API_URL = BASE_URL + "/api";
        REFRESH_TOKEN = properties.getProperty(ClientProperties.REFRESH_TOKEN);
        DEBUG = Boolean.valueOf(properties.getProperty(ClientProperties.DEBUG));
    }

    private Constants() {}

}
