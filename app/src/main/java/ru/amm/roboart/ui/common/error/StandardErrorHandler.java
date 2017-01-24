package ru.amm.roboart.ui.common.error;


import android.text.TextUtils;

import com.agna.ferro.mvp.component.scope.PerScreen;

import javax.inject.Inject;

import ru.amm.roboart.R;
import ru.amm.roboart.interactor.common.network.error.ApiServiceException;
import ru.amm.roboart.interactor.common.network.error.ConversionException;
import ru.amm.roboart.interactor.common.network.error.HttpError;
import ru.amm.roboart.interactor.common.network.error.NoInternetException;
import ru.amm.roboart.ui.common.message.MessagePresenter;

/**
 * Стандартный обработчик ошибок, возникающих при работе с сервером
 */
@PerScreen
public class StandardErrorHandler extends ErrorHandler {

    private final MessagePresenter messagePresenter;

    @Inject
    public StandardErrorHandler(MessagePresenter messagePresenter) {
        this.messagePresenter = messagePresenter;
    }


    @Override
    protected void handleApiServiceError(ApiServiceException err) {
        if (!TextUtils.isEmpty(err.getUserMessage())) {
            messagePresenter.show(err.getUserMessage());
        } else {
            messagePresenter.show(R.string.service_error_message);
        }
    }

    @Override
    protected void handleHttpError(HttpError e) {
        messagePresenter.show(R.string.server_error_message);
    }

    @Override
    protected void handleNoInternetError(NoInternetException e) {
        messagePresenter.show(R.string.no_internet_connection_error_message);
    }

    @Override
    protected void handleConversionError(ConversionException e) {
        messagePresenter.show(R.string.bad_response_error_message);
    }

    @Override
    protected void handleOtherError(Throwable e) {
        messagePresenter.show(R.string.unexpected_error_error_message);
    }
}
