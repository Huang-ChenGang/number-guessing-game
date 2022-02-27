package com.hcg.ngg.generator;

import java.util.Random;

public class NumberGenerator {

    private static final int GENERATOR_BOUND = 10;

    public static int generate() {
        Random random = new Random();
        return random.nextInt(GENERATOR_BOUND) + 1;
    }
}
