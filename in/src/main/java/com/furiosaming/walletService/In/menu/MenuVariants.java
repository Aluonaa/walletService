package com.furiosaming.walletService.In.menu;

/**
 * Класс, в котором хранятся пункты меню: стартовое и для авторизованных пользователей
 */
public class MenuVariants {
    /**
     * Метод, который выводит на экран пункты стартового меню
     */
    public static void displayStartMenuVariants() {
        System.out.println("Добро пожаловать в walletService. Войдите или зарегистрируйтесь");
        System.out.println("1. Войти");
        System.out.println("2. Зарегистрироваться");
        System.out.println("3. Закончить работу");
    }

    /**
     * Метод, который выводит на экран меню для авторизованных пользователей
     */
    public static void displayAuthorizedActionsVariants(){
        System.out.println("1. Узнать баланс");
        System.out.println("2. Пополнить счет");
        System.out.println("3. Вывести средства");
        System.out.println("4. История операций");
        System.out.println("5. Выход");
    }
}
