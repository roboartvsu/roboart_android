package ru.amm.roboart.interactor.common.network.error;

/**
 * отсутствует подключение к интернету
 */
public class NoInternetException extends NetworkException {

    public NoInternetException(Throwable e) {
        super(e);
    }
}
