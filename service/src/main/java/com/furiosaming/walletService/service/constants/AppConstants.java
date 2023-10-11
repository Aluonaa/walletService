package com.furiosaming.walletService.service.constants;

/**
 * Класс, описывающий константы для возвращения пользователю
 * описания результатов выполнения методов
 */
public class AppConstants {
    public static final String SUCCESS = "Операция выполнена успешно";
    public static final String UUID_ALREADY_EXISTS = "Такая почта уже существует";
    public static final String LOGIN_ALREADY_EXISTS = "Такой логин уже существует";
    public static final String ID_TRASACTION_ALREADY_EXISTS = "Такой id транзакции уже существует";

    public static String MISSING_BANK_ACCOUNT_ID = "Не введен id банковского счета";
    public static String MISSING_FIELDS = "Некоторые поля не заполнены";

    public static String INCORRECT_LOGIN_OR_PASSWORD = "Неправильный логин или пароль";
    public static String INCORRECT_SUM = "Введите сумму больше нуля";
    public static String INSUFFICIENT_FUNDS = "Недостаточно средств на счету";

}
