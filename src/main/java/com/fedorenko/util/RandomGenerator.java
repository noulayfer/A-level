package com.fedorenko.util;

import java.util.Random;

public class RandomGenerator {
    private final Random random = new Random();

    public int getRandomNumber() {
        return random.nextInt(11);
    }
}
