package me.skully.santibot.utils;

import java.util.Random;

public class RandomUtils {
    private static final Random RANDOM = new Random();

    public static Random getRANDOM() {
        return RANDOM;
    }

    public static int getRandomInt(int lower, int upper) {
        return RANDOM.nextInt(upper - lower + 1) + lower;
    }
}

