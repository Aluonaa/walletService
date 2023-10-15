package com.furiosaming.walletService.service.response;

import com.furiosaming.walletService.service.constants.AppConstants;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Класс, являющийся возвращаемой из сервисов сущностью,
 * который хранит в себе результаты работы метода и его описание
 * @param <T> любой, необходимый для возвращения, тип переменной
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Response<T> {

    /** Статус результата работы метода, успешно, либо одна из типов ошибок */
    private boolean status;
    /** Описание статуса результата работы метода, успешно, либо указания к
     * исправлению введенных данных */
    private String description;
    /** любой, необходимый для возвращения, тип переменной */
    private T result;

    /** Класс, реализующий паттерн билдер для возвращения результатов работы метода */
    public static class Builder<T> {
        /** Возвращаемая из методов сущность */
        private Response<T> response;
        public Builder() {
            response = new Response<>();
        }

        /** Метод создания сущности в случае успеха */
        public Builder<T> success(T result) {
            response.status = true;
            response.description = AppConstants.SUCCESS;
            response.result = result;
            return this;
        }
        /** Метод формирования сущности в случае, если необходимые данные не найдены */
        public Builder<T> notFound(String description) {
            response.status = false;
            response.description = description;
            response.result = null;
            return this;
        }
        /** Метод формирования сущности в случае, если необходимые для работы метода
         * данные пропущены пользователем */
        public Builder<T> missing(String description) {
            response.status = false;
            response.description = description;
            response.result = null;
            return this;
        }
        /** Метод формирования сущности в случае, если необходимые для работы метода
         * данные неверно введены пользователем */
        public Builder<T> wrongData(String description) {
            response.status = false;
            response.description = description;
            response.result = null;
            return this;
        }
        /** Метод формирования сущности в случае, если создаваемая сущность
         * уже существует и не может быть создана повторно */
        public Builder<T> alreadyExist(String description) {
            response.status = false;
            response.description = description;
            response.result = null;
            return this;
        }
        /** Метод создания сущности ответа */
        public Response<T> build() {
            return response;
        }
    }
}
