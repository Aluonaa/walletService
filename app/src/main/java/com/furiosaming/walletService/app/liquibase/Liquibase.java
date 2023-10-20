package com.furiosaming.walletService.app.liquibase;

import com.furiosaming.walletService.repository.dbProperties.PropertiesLoader;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.resource.ClassLoaderResourceAccessor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class Liquibase {
    public static void createDataBase(){
        Properties properties = PropertiesLoader.loadProperties();
        try {
            Connection connection = DriverManager.getConnection(
                    properties.getProperty("datasource.urldatabase.setDefaultSchemaName(\"service_tables_schema\");"),
                    properties.getProperty("datasource.username"),
                    properties.getProperty("datasource.password")
            );
            Database database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(connection));
            connection.createStatement().execute("CREATE SCHEMA IF NOT EXISTS wallet_service_schema");
            connection.createStatement().execute("CREATE SCHEMA IF NOT EXISTS service_tables_schema");

            liquibase.Liquibase liquibase = new liquibase.Liquibase(properties.getProperty("liquibase.change-log"), new ClassLoaderResourceAccessor(), database);
            liquibase.update();
            System.out.println("Миграции успешно выполнены!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
