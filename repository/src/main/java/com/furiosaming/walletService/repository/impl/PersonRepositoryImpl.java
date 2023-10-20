package com.furiosaming.walletService.repository.impl;

import com.furiosaming.walletService.persistence.model.Person;
import com.furiosaming.walletService.repository.PersonRepository;
import com.furiosaming.walletService.repository.dbProperties.PropertiesLoader;

import java.sql.*;
import java.util.Properties;

/**
 * Имплементация интерфейса репозитория пользователя
 */
public class PersonRepositoryImpl implements PersonRepository {

    /**
     * Метод поиска пользователя по логину, необходим для регистрации
     * и уникальности всех пользователей по логину
     * @param login логин пользователя
     * @return возвращает безопасный тип optional
     */
    @Override
    public Person findByLogin(String login) {
        Properties properties = PropertiesLoader.loadProperties();
        String sqlFindById = "SELECT * FROM wallet_service_schema.person WHERE login = ?";
        try (Connection connection = DriverManager.getConnection(properties.getProperty("datasource.url"),
                properties.getProperty("datasource.username"),
                properties.getProperty("datasource.password"));
             PreparedStatement preparedStatement = connection.prepareStatement(sqlFindById)) {
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                Person person = new Person();
                person.setId(resultSet.getLong(1));
                person.setPassport(resultSet.getString(2));
                person.setLogin(resultSet.getString(3));
                person.setPassword(resultSet.getString(4));
                return person;
            }
        } catch (SQLException exception) {
            System.out.println("Got SQL Exception " + exception.getMessage());
        }
        return null;
    }

    /**
     * Метод создает в базе данных пользователя
     * @param person пользователь, которого необходимо сохранить
     * @return возвращает созданного пользователя
     */
    public Person createPerson(Person person) {
        Properties properties = PropertiesLoader.loadProperties();
        String sqlInsertPerson = "INSERT INTO wallet_service_schema.person (passport, login, password) VALUES (?, ?, ?)";
        try (Connection connection = DriverManager.getConnection(properties.getProperty("datasource.url"),
                properties.getProperty("datasource.username"),
                properties.getProperty("datasource.password"));
             PreparedStatement preparedStatement = connection.prepareStatement(sqlInsertPerson, Statement.RETURN_GENERATED_KEYS)) {
            connection.setAutoCommit(false);
            preparedStatement.setString(1, person.getPassport());
            preparedStatement.setString(2, person.getLogin());
            preparedStatement.setString(3, person.getPassword());
            preparedStatement.executeUpdate();
            connection.commit();
            ResultSet result = preparedStatement.getGeneratedKeys();
            if(result.next()){
                person.setId(result.getLong(1));
                System.out.println("Пользователь зарегистрирован");
            }
        } catch (SQLException exception) {
            System.out.println("Got SQL Exception for person create" + exception.getMessage());
        }
        return person;
    }
    /**
     * Метод авторизует пользователя
     * (проверяет в списке всех пользователей совпадения логина и пароля)
     * @param login логин пользователя
     * @param password пароль пользователя
     * @return возвращает либо найденного пользователя, либо null в
     *          безопасном типе Optional
     */
    @Override
    public Person authorize(String login, String password) {
        Properties properties = PropertiesLoader.loadProperties();
        String sqlFindById = "SELECT * FROM wallet_service_schema.person WHERE login = ? and password = ?";
        try (Connection connection = DriverManager.getConnection(properties.getProperty("datasource.url"),
                properties.getProperty("datasource.username"),
                properties.getProperty("datasource.password"));
             PreparedStatement preparedStatement = connection.prepareStatement(sqlFindById)) {


            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                Person person = new Person();
                person.setId(resultSet.getLong(1));
                person.setPassport(resultSet.getString(2));
                person.setLogin(resultSet.getString(3));
                person.setPassword(resultSet.getString(4));
                return person;
            }
        } catch (SQLException exception) {
            System.out.println("Got SQL Exception " + exception.getMessage());
        }
        return null;
    }
}
