package com.fedorenko.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Random;

@Getter
@Setter
public class Engine {
    int power;
    Type type;

    private final Random random = new Random();

    public Engine() {
        power = random.nextInt(1000);
        type = getRandomType();
    }

    private Type getRandomType() {
        Type[] types = Type.values();
        int index = random.nextInt(types.length);
        return types[index];
    }
}
