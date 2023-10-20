package com.furiosaming.walletService.In.help;

import java.io.IOException;

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
     * @return возвращает идентификатор транзакции
     * @throws IOException исключения ввода-вывода
     */
    public static Long inputCodeForCashInOut() throws IOException {
        System.out.println("Введите идентификатор транзакции:");
        Long transactionCode = 0L;
        transactionCode = idInputErrorsHandling(transactionCode);
        return transactionCode;
    }

    /**
     * Метод выводит указание на ввод суммы, которая будет снята со счета,
     * либо положена на этот счет и передает управление методу, обрабатывающему исключения,
     * связанные с вводом неверных символов при вводе суммы
     * @return возвращает сумму транзакции
     * @throws IOException исключения ввода-вывода
     */
    public static Long inputSumForCashInOut() throws IOException {
        System.out.println("Введите сумму:");
        long sum = 0;
        sum = sumInputErrorsHandling(sum);
        return sum;
    }

    /**
     * Метод обрабатывает исключение ввода неверного символа
     * и возвращает к вводу суммы заново
     * @param sum переменная для ввода суммы транзакции
     * @return сумма транзакции
     * @throws IOException исключения ввода-вывода
     */
    public static Long sumInputErrorsHandling(Long sum)
            throws IOException {
        try {
            sum = Long.valueOf(in.readLine());
        }
        catch (NumberFormatException | IOException n){
            System.out.println("Введены недопустимые символы, попробуйте еще раз.");
            inputSumForCashInOut();
        }
        return sum;
    }

    /**
     * Метод обрабатывает исключение ввода неверного символа
     * и возвращает к вводу идентификатора заново
     * @param id переменная для записи идентификатора транзакции
     * @return идентификатор транзакции
     * @throws IOException исключения ввода-вывода
     */
    public static Long idInputErrorsHandling(Long id)
            throws IOException {
        try {
            id = Long.valueOf(in.readLine());
        }
        catch (NumberFormatException | IOException n){
            System.out.println("Введены недопустимые символы, попробуйте еще раз.");
            inputCodeForCashInOut();
        }
        return id;
    }

    /**
     * Метод обрабатывает исключение ввода неверного символа
     * при выборе пункта меню программы
     * @param menuItem переменная для записи выбранного пукта меню
     * @return выбранный пункт меню
     * @throws IOException исключения ввода-вывода
     */
    public static int menuItemErrorsCorrection(int menuItem)
            throws IOException {
        try {
            menuItem = Integer.parseInt(in.readLine());
        }
        catch (NumberFormatException | IOException n){
            System.out.println("Введены недопустимые символы, попробуйте еще раз.");
            start();
        }
        return menuItem;
    }
}
