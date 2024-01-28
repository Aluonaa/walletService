 package com.furiosaming.walletService.app;



import java.io.IOException;

import static com.furiosaming.walletService.app.liquibase.Liquibase.createDataBase;

/**
 * Программа walletService Сервис для управления
 * кредитными/дебетовыми транзакциями от имени игроков
 *
 * @author  Strelets Alyona
 * @version  1.0
 */
public class walletServiceApp {
    /**
     * Стартовый метод программы, при запуске создается список пользователей,
     * который существует на протяжении всей работы программы,
     * заменяет базу данных. Через него в программе можно получить
     * любые другие сущности, такие как банковские аккаунты, транзакции, действия в аккаунте
     * @param args аргументы командной строки
     * @throws IOException исключения ввода-вывода
     */


    public static void main(String[] args) throws IOException {
        createDataBase();
        //Menu.start();
    }
}
