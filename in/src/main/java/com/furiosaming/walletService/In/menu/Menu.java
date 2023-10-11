package com.furiosaming.walletService.In.menu;

import com.furiosaming.walletService.persistence.model.Person;

import java.io.IOException;
import java.util.List;

import static com.furiosaming.walletService.In.menu.SelectingMenuItem.chooseStartMenuItem;

import static com.furiosaming.walletService.In.menu.MenuVariants.displayStartMenuVariants;

/**
 * Класс, с которого начинается взаимодействие с пользователем
 */
public class Menu {
    /**
     * Метод, который вызывает вывод на экран стартового меню,
     * а затем вызывает метод выбора пункта данного меню
     * @param personList список пользователей
     * @throws IOException исключения ввода-вывода
     */
    public static void start(List<Person> personList) throws IOException {
        displayStartMenuVariants();
        chooseStartMenuItem(personList);
    }
}



