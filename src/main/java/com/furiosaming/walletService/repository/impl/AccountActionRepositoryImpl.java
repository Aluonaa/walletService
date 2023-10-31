package com.furiosaming.walletService.repository.impl;

import com.furiosaming.walletService.app.liquibase.dbProperties.PropertiesLoader;
import com.furiosaming.walletService.persistence.model.AccountAction;
import com.furiosaming.walletService.repository.AccountActionRepository;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.Properties;

/**
 * Имплементация интерфейса репозитория действий в аккаунте
 */
public class AccountActionRepositoryImpl implements AccountActionRepository {
    /**
     * Метод создает действие в аккаунте в базе данных
     * @param accountAction действие в аккаунте
     * @return возвращает действие в аккаунте или null
     */
    public AccountAction createAccountAction(AccountAction accountAction) {
        Properties properties = PropertiesLoader.loadProperties();
        String sqlInsertPerson = "INSERT INTO wallet_service_schema.account_action (action_type, date, person) VALUES (?, ?, ?)";
        try (Connection connection = DriverManager.getConnection(properties.getProperty("datasource.url"),
                properties.getProperty("datasource.username"),
                properties.getProperty("datasource.password"));
             PreparedStatement preparedStatement = connection.prepareStatement(sqlInsertPerson, Statement.RETURN_GENERATED_KEYS)) {
            connection.setAutoCommit(false);
            preparedStatement.setString(1, String.valueOf(accountAction.getActionType()));
            preparedStatement.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
            preparedStatement.setLong(3, accountAction.getPerson().getId());
            preparedStatement.executeUpdate();
            connection.commit();
            ResultSet result = preparedStatement.getGeneratedKeys();
            if(result.next()){
                accountAction.setId(result.getLong(1));
                System.out.println("Действие аккаунта зарегистрировано");
                return accountAction;
            }
        } catch (SQLException exception) {
            System.out.println("Got SQL Exception for person create" + exception.getMessage());
        }
        return null;
    }
}
