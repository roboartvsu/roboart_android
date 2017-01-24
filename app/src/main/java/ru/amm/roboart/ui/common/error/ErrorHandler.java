package ru.amm.roboart.ui.common.error;

import java.util.List;

import ru.amm.roboart.interactor.common.network.error.ApiServiceException;
import ru.amm.roboart.interactor.common.network.error.ConversionException;
import ru.amm.roboart.interactor.common.network.error.HttpError;
import ru.amm.roboart.interactor.common.network.error.NetworkException;
import ru.amm.roboart.interactor.common.network.error.NoInternetException;
import rx.exceptions.CompositeException;

/**
 * Базовый класс для обработки ошибок, возникающий при работе с Observable из слоя Interactor
 */
public abstract class ErrorHandler {

    public void handleError(Throwable err) {
        if (err instanceof CompositeException) {
            handleCompositeException((CompositeException) err);
        } else if (err instanceof ConversionException) {
            handleConversionError((ConversionException) err);
        } else if (err instanceof HttpError) {
            handleHttpError((HttpError) err);
        } else if (err instanceof NoInternetException) {
            handleNoInternetError((NoInternetException) err);
        } else if (err instanceof ApiServiceException) {
            handleApiServiceError((ApiServiceException) err);
        } else {
            handleOtherError(err);
        }
    }

    /**
     * @param err - CompositeException может возникать при комбинировании Observable
     */
    private void handleCompositeException(CompositeException err) {
        List<Throwable> exceptions = err.getExceptions();
        NetworkException networkException = null;
        Throwable otherException = null;
        for (Throwable e : exceptions) {
            if (e instanceof NetworkException) {
                if (networkException == null) {
                    networkException = (NetworkException) e;
                }
            } else if (otherException == null) {
                otherException = e;
            }
        }
        if (networkException != null) {
            handleError(networkException);
        }
        if (otherException != null) {
            handleOtherError(otherException);
        }
    }

    protected abstract void handleApiServiceError(ApiServiceException err);

    protected abstract void handleHttpError(HttpError e);

    protected abstract void handleNoInternetError(NoInternetException e);

    protected abstract void handleConversionError(ConversionException e);

    protected abstract void handleOtherError(Throwable e);
}
