package com.anton.chernikov.g244;

import java.util.Map;

/** Class storing infection probabilities for each operating system */
public abstract class Const {
    public static Map<Double, OS> systems = Map.of(0.3, OS.Windows,
                                                   0.2, OS.Linux,
                                                   0.1, OS.MacOS,
                                                   1.0, OS.TestOS);
}
