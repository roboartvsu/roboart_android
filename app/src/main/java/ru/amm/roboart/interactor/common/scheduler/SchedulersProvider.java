package ru.amm.roboart.interactor.common.scheduler;

import rx.Scheduler;

public interface SchedulersProvider {
    Scheduler main();
    Scheduler worker();
}