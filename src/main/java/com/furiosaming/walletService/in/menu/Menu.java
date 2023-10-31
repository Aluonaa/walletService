package com.furiosaming.walletService.in.menu;

import java.io.IOException;

import static com.furiosaming.walletService.in.menu.MenuVariants.displayStartMenuVariants;

/**
 * Класс, с которого начинается взаимодействие с пользователем
 */
public class Menu {
    /**
     * Метод, который вызывает вывод на экран стартового меню,
     * а затем вызывает метод выбора пункта данного меню
     * @throws IOException исключения ввода-вывода
     */
    public static void start() throws IOException {
        displayStartMenuVariants();
        //chooseStartMenuItem();
    }
}



