package com.furiosaming.walletService.repository.impl;

import com.furiosaming.walletService.persistence.model.BankAccount;
import com.furiosaming.walletService.persistence.model.Person;
import com.furiosaming.walletService.repository.BankAccountRepository;
import com.furiosaming.walletService.repository.dbProperties.PropertiesLoader;

import java.sql.*;
import java.util.Properties;

/**
 * Имплементация интерфейса репозитория банковского счета
 */
public class BankAccountRepositoryImpl implements BankAccountRepository {
    /**
     * Метод создает в базе данных банковский счет
     * @param person владелец аккаунта
     * @return созданный банковский счет
     */
    public BankAccount createBankAccount(Person person) {
        BankAccount bankAccount = new BankAccount();
        Properties properties = PropertiesLoader.loadProperties();
        String sqlInsertBankAccount = "INSERT INTO wallet_service_schema.bank_account (cash_value, person) VALUES ( ?, ?)";
        try (Connection connection = DriverManager.getConnection(properties.getProperty("datasource.url"),
                properties.getProperty("datasource.username"),
                properties.getProperty("datasource.password"));
             PreparedStatement preparedStatement = connection.prepareStatement(sqlInsertBankAccount, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setLong(1, 0);
            preparedStatement.setLong(2, person.getId());
            preparedStatement.executeUpdate();
            ResultSet result = preparedStatement.getGeneratedKeys();
            if(result.next()){
                bankAccount.setId(result.getLong(1));
                bankAccount.setPerson(person);
                bankAccount.setCashValue(0);
                System.out.println("Банковский счет создан");
            }
        } catch (SQLException exception) {
            System.out.println("Got SQL Exception for bankAccount create" + exception.getMessage());
        }
        return bankAccount;
    }

    /**
     * Находит банковский счет по id в базе данных
     * @param id уникальный идентификатор банковского счета
     * @return банковский счет или null
     */
    @Override
    public BankAccount getBankAccountByPersonId(Long id) {
        Properties properties = PropertiesLoader.loadProperties();
        String sqlFindById = "SELECT * FROM wallet_service_schema.bank_account WHERE person = ?";
        try (Connection connection = DriverManager.getConnection(properties.getProperty("datasource.url"),
                properties.getProperty("datasource.username"),
                properties.getProperty("datasource.password"));
             PreparedStatement preparedStatement = connection.prepareStatement(sqlFindById)) {


            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                BankAccount bankAccount = new BankAccount();
                bankAccount.setId(resultSet.getLong(1));
                bankAccount.setCashValue(resultSet.getLong(2));
                return bankAccount;
            }
        } catch (SQLException exception) {
            System.out.println("Got SQL Exception for getting bank account by id" + exception.getMessage());
        }
        return null;
    }
}
