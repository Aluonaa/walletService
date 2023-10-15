package com.furiosaming.walletService.In.help;

import com.furiosaming.walletService.persistence.model.Person;

import java.io.IOException;
import java.util.List;

import static com.furiosaming.walletService.In.StaticVariables.*;
import static com.furiosaming.walletService.In.menu.Menu.start;

/**
 * Класс для обработки исключений при вводе некорректных символов
 */
public class InputErrorsCorrection {

    /**
     * Метод выводит указание на ввод идентификатора транзакции по счету
     * и передает управление методу, обрабатывающему исключения,
     * связанные с вводом неверных символов при вводе идентификатора
     * @param personList список пользователей
     * @return возвращает идентификатор транзакции
     * @throws IOException исключения ввода-вывода
     */
    public static Long inputIdForCashInOut(List<Person> personList) throws IOException {
        System.out.println("Введите идентификатор транзакции:");
        Long transactionId = 0L;
        transactionId = idInputErrorsHandling(transactionId, personList);
        return transactionId;
    }

    /**
     * Метод выводит указание на ввод суммы, которая будет снята со счета,
     * либо положена на этот счет и передает управление методу, обрабатывающему исключения,
     * связанные с вводом неверных символов при вводе суммы
     * @param personList список пользователей
     * @return возвращает сумму транзакции
     * @throws IOException исключения ввода-вывода
     */
    public static Long inputSumForCashInOut(List<Person> personList) throws IOException {
        System.out.println("Введите сумму:");
        long sum = 0;
        sum = sumInputErrorsHandling(sum, personList);
        return sum;
    }

    /**
     * Метод обрабатывает исключение ввода неверного символа
     * и возвращает к вводу суммы заново
     * @param sum переменная для ввода суммы транзакции
     * @param personList список пользователей
     * @return сумма транзакции
     * @throws IOException исключения ввода-вывода
     */
    public static Long sumInputErrorsHandling(Long sum, List<Person> personList)
            throws IOException {
        try {
            sum = Long.valueOf(in.readLine());
        }
        catch (NumberFormatException | IOException n){
            System.out.println("Введены недопустимые символы, попробуйте еще раз.");
            inputSumForCashInOut(personList);
        }
        return sum;
    }

    /**
     * Метод обрабатывает исключение ввода неверного символа
     * и возвращает к вводу идентификатора заново
     * @param id переменная для записи идентификатора транзакции
     * @param personList список пользователей
     * @return идентификатор транзакции
     * @throws IOException исключения ввода-вывода
     */
    public static Long idInputErrorsHandling(Long id, List<Person> personList)
            throws IOException {
        try {
            id = Long.valueOf(in.readLine());
        }
        catch (NumberFormatException | IOException n){
            System.out.println("Введены недопустимые символы, попробуйте еще раз.");
            inputIdForCashInOut(personList);
        }
        return id;
    }

    /**
     * Метод обрабатывает исключение ввода неверного символа
     * и возвращает к вводу идентификатора банковского счета
     * @return идентификатор банковского счета. Необходим для его создания
     * @throws IOException исключения ввода-вывода
     */
    public static Long enteringBankAccountId() throws IOException {
        System.out.println("Введите id банковского счета:");
        long bankAccountId = 0L;
        try {
            bankAccountId = Long.parseLong(in.readLine());
        }
        catch (NumberFormatException n){
            System.out.println("Введите корректный id банковского счета");
            enteringBankAccountId();
        }
        return bankAccountId;
    }

    /**
     * Метод обрабатывает исключение ввода неверного символа
     * при выборе пункта меню программы
     * @param menuItem переменная для записи выбранного пукта меню
     * @param personList список пользователей
     * @return выбранный пункт меню
     * @throws IOException исключения ввода-вывода
     */
    public static int menuItemErrorsCorrection(int menuItem, List<Person> personList)
            throws IOException {
        try {
            menuItem = Integer.parseInt(in.readLine());
        }
        catch (NumberFormatException | IOException n){
            System.out.println("Введены недопустимые символы, попробуйте еще раз.");
            start(personList);
        }
        return menuItem;
    }
}
