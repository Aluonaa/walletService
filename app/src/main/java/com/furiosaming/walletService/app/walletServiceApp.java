package com.furiosaming.walletService.app;


import com.furiosaming.walletService.In.menu.Menu;
import com.furiosaming.walletService.persistence.model.Person;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
        List<Person> personList = new ArrayList<>();
        Menu.start(personList);
    }
}
