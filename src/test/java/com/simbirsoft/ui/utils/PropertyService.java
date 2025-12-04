package com.simbirsoft.ui.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyService {
    private static final PropertyService INSTANCE = new PropertyService();
    private final Properties PROPERTIES;

    private PropertyService(){
        try(InputStream inputStream = PropertyService.class.getResourceAsStream("/conf.properties")) {
            PROPERTIES = new Properties();
            PROPERTIES.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static PropertyService getInstance() {
        return INSTANCE;
    }

    public String getProperty(String key) {
        return PROPERTIES.getProperty(key);
    }
}
