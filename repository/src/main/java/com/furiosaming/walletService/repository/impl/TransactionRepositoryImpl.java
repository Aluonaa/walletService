package com.furiosaming.walletService.repository.impl;

import com.furiosaming.walletService.persistence.model.Transaction;
import com.furiosaming.walletService.persistence.model.enums.TransactionType;
import com.furiosaming.walletService.repository.dbProperties.PropertiesLoader;
import com.furiosaming.walletService.repository.TransactionRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Имплементация интерфейса репозитория транзакций
 */
public class TransactionRepositoryImpl implements TransactionRepository {
    /**
     * Метод создаeт в базе данных транзакцию
     * @param transaction транзакция, которую необходимо сохранить
     */
    public Transaction createTransaction(Transaction transaction) {
        Properties properties = PropertiesLoader.loadProperties();
        String sqlInsertPerson = "INSERT INTO wallet_service_schema.transaction (transaction_type, cash_value, date, bank_account, transaction_code) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = DriverManager.getConnection(properties.getProperty("datasource.url"),
                properties.getProperty("datasource.username"),
                properties.getProperty("datasource.password"));
             PreparedStatement preparedStatement = connection.prepareStatement(sqlInsertPerson, Statement.RETURN_GENERATED_KEYS)) {
            connection.setAutoCommit(false);
            preparedStatement.setString(1, String.valueOf(transaction.getTransactionType()));
            preparedStatement.setLong(2, transaction.getCashValue());
            preparedStatement.setTimestamp(3, Timestamp.valueOf(transaction.getDate()));
            preparedStatement.setLong(4,transaction.getBankAccount().getId());
            preparedStatement.setLong(5, transaction.getTransactionCode());
            preparedStatement.executeUpdate();
            connection.commit();
            ResultSet result = preparedStatement.getGeneratedKeys();
            if(result.next()){
                transaction.setId(result.getLong(1));
                System.out.println("Транзакция выполнена");
            }
        } catch (SQLException exception) {
            System.out.println("Got SQL Exception for transaction create " + exception.getMessage());
        }
        return transaction;
    }

    /**
     * Метод ищет транзакцию в базе данных с совпадающим кодом транзакции
     * @param transactionCode код транзакции
     * @return возвращает найденную транзакцию или null
     */
    @Override
    public Boolean isPresentTransactionByTransactionCode(Long transactionCode) {
        Properties properties = PropertiesLoader.loadProperties();
        String sqlFindByTransactionCode = "SELECT * FROM wallet_service_schema.transaction WHERE transaction_code = ?";
        try (Connection connection = DriverManager.getConnection(properties.getProperty("datasource.url"),
                properties.getProperty("datasource.username"),
                properties.getProperty("datasource.password"));
             PreparedStatement preparedStatement = connection.prepareStatement(sqlFindByTransactionCode)) {

            preparedStatement.setLong(1, transactionCode);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return true;
            }
        } catch (SQLException exception) {
            System.out.println("Got SQL Exception " + exception.getMessage());
        }
        return false;
    }

    /**
     * Получение всех транзакций банковского счета из базы данных
     * @param id уникальный идентификатор банковского счета
     * @return список транзакций
     */
    @Override
    public List<Transaction> getTransactionsByBankAccountId(Long id) {
        List<Transaction> transactionList = new ArrayList<>();
        Properties properties = PropertiesLoader.loadProperties();
        String sqlFindByBankAccountId = "SELECT * FROM wallet_service_schema.transaction WHERE bank_account = ?";
        try (Connection connection = DriverManager.getConnection(properties.getProperty("datasource.url"),
                properties.getProperty("datasource.username"),
                properties.getProperty("datasource.password"));
             PreparedStatement preparedStatement = connection.prepareStatement(sqlFindByBankAccountId)) {


            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Transaction transaction = new Transaction();
                transaction.setId(resultSet.getLong(1));
                transaction.setTransactionType(TransactionType.valueOf(resultSet.getString(2)));
                transaction.setCashValue(resultSet.getLong(3));
                transaction.setDate(resultSet.getTimestamp(4).toLocalDateTime());
                transaction.setTransactionCode(resultSet.getLong(6));
                transactionList.add(transaction);
            }
        } catch (SQLException exception) {
            System.out.println("Got SQL Exception for getting transactions by bank account id " + exception.getMessage());
        }
        return transactionList;
    }
}
