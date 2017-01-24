package ru.amm.roboart.ui.base.proxy.proxy;

import rx.Observable;
import rx.subjects.PublishSubject;

/**
 * прокси для событий onActivityResult, для получения этих событий следует зарегистрировать прокси
 * с помощью метода {@link BaseActivityView#registerActivityResultProxy(ActivityResultProxy)}
 * {@link BaseFragmentView#registerActivityResultProxy(ActivityResultProxy)}
 * Позволяет оповещать слушателей через Observable
 * для оповещения слушателей необходимо вызвать метод {@link #notifyListeners(Object)}
 * @param <E> тип события
 */
public abstract class ActivityResultProxyRx<E> implements ActivityResultProxy {
    private final PublishSubject<E> eventSubject = PublishSubject.create();

    public Observable<E> observeResult(){
        return eventSubject;
    }

    protected void notifyListeners(E event){
        eventSubject.onNext(event);
    }
}
