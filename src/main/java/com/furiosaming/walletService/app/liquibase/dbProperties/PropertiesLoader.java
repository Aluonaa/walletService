package com.furiosaming.walletService.app.liquibase.dbProperties;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesLoader {

    public static Properties loadProperties() {
        Properties configuration = new Properties();
        try (InputStream inputStream = PropertiesLoader.class
                .getClassLoader()
                .getResourceAsStream("application.properties")) {
            configuration.load(inputStream);
        }
        catch (IOException io){
            System.out.println("Ошибка конфигурации");
        }
        return configuration;
    }
}
