package com.fedorenko.model;

import java.util.Random;

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

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
