package ru.amm.roboart.ui.base.proxy.proxy;

import android.content.Intent;

import rx.Observable;
import rx.subjects.PublishSubject;

/**
 * прокси для событий onNewIntent, для получения этих событий следует зарегистрировать прокси
 * с помощью метода {@link ru.amm.roboart.ui.base.activity.BaseActivityView#registerNewIntentProxy(NewIntentProxy)}
 * Позволяет оповещать слушателей через Observable
 * для оповещения слушателей необходимо вызвать метод {@link #notifyListeners(Object)}
 * @param <E> тип события
 */
public abstract class NewIntentProxyRx<E> implements NewIntentProxy {
    protected final PublishSubject<E> eventSubject = PublishSubject.create();

    public abstract void handleIntent(Intent newIntent);

    public Observable<E> observeEvent(){
        return eventSubject.first();
    }

    protected void notifyListeners(E event){
        eventSubject.onNext(event);
    }
}