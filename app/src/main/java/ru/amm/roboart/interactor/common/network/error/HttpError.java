package ru.amm.roboart.interactor.common.network.error;


/**
 * получен ответ не 2xx
 */
public class HttpError extends NetworkException {
    private int code;

    public HttpError(Throwable cause, String message, int code) {
        super(message, cause);
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
