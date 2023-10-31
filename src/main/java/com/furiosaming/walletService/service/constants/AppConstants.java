package com.furiosaming.walletService.service.constants;

/**
 * Класс, описывающий константы для возвращения пользователю
 * описания результатов выполнения методов
 */
public class AppConstants {
    public static final String SUCCESS = "Операция выполнена успешно";
    public static final String LOGIN_ALREADY_EXISTS = "Такой логин уже существует";
    public static final String TRASACTION_CODE_ALREADY_EXISTS = "Такой код транзакции уже существует";

    public static final String FAILED_TO_CREATE = "Ошибка создания";
    public static final String FAILED_TO_CASH_IN = "Ошибка пополнения счета";

    public static String MISSING_TRANSACTION_CODE = "Не введен код транзакции";
    public static String MISSING_BANK_ACCOUNT_ID = "Не введен id баковского счета";
    public static String MISSING_PERSON_ID = "Не введен id пользователя";
    public static String MISSING_FIELDS = "Некоторые поля не заполнены";

    public static String INCORRECT_LOGIN_OR_PASSWORD = "Неправильный логин или пароль";
    public static String INCORRECT_SUM = "Введите сумму больше нуля";
    public static String INSUFFICIENT_FUNDS = "Недостаточно средств на счету";

    public static String BANK_ACCOUNT_NOT_FOUND = "Банковский счет не найден";

}
