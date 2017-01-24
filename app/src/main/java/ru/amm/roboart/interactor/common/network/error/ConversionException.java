package ru.amm.roboart.interactor.common.network.error;

/**
 * ошибка парсинга ответа
 */
public class ConversionException extends NetworkException {
    public ConversionException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConversionException(String message) {
        super(message);
    }
}
