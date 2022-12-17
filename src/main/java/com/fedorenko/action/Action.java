package com.fedorenko.action;

import com.fedorenko.service.CarService;

public interface Action {
    public static final CarService CAR_SERVICE = CarService.getInstance();

    void execute();
}
