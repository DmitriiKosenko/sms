package com.springapp.mvc;

import net.java.quickcheck.generator.PrimitiveGenerators;

/**
 * Created by dmitry on 04.10.15.
 */
public class Util {
    public static long randomTelNumber() {
        return PrimitiveGenerators.longs(79000000000l, 79999999999l).next();
    }

    public static int randomStatus() {
        return PrimitiveGenerators.integers(0, 1).next();
    }

    public static String randomMessage() {
        return PrimitiveGenerators.strings(1000).next();
    }
}
