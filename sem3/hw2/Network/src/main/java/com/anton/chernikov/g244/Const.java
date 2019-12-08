package com.anton.chernikov.g244;

import java.util.Map;

/** The Const class stores infection probabilities for each operating system */
public abstract class Const {
    public static Map<Double, OS> systems = Map.of(0.3, OS.Windows,
                                                   0.2, OS.Linux,
                                                   0.1, OS.MacOS);
}
