package com.AntonChernikov.g144;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class SecondPartTasks {

    private SecondPartTasks() {}

    // Найти строки из переданных файлов, в которых встречается указанная подстрока.
    public static List<String> findQuotes(List<String> paths, CharSequence sequence) {
        return paths.stream().flatMap(path -> {
            try {
                return Files.lines(Paths.get(path));
            } catch (IOException e) {
                throw new IllegalArgumentException("Incorrect path: " + path);
            }
        })
                .filter(string -> string.contains(sequence))
                .collect(Collectors.toList());
    }

    // В квадрат с длиной стороны 1 вписана мишень.
    // Стрелок атакует мишень и каждый раз попадает в произвольную точку квадрата.
    // Надо промоделировать этот процесс с помощью класса java.util.Random и посчитать, какова вероятность попасть в мишень.
    public static double piDividedBy4() {
        long attempts = 999999;
        double radius = 0.5;
        final Random random = new Random();
        long luckyRadiuses = Stream
                .generate(() -> Math.sqrt(Math.pow(random.nextDouble() - 0.5, 2) + Math.pow(random.nextDouble() - 0.5, 2)))
                .limit(attempts)
                .filter(r -> r < radius)
                .count();
        return (double) luckyRadiuses / attempts;
    }

    // Дано отображение из имени автора в список с содержанием его произведений.
    // Надо вычислить, чья общая длина произведений наибольшая.
    public static String findPrinter(Map<String, List<String>> compositions) {
        return compositions
                .entrySet()
                .stream()
                .max(Comparator.comparingLong(
                        e -> String.join("", e.getValue())
                                .chars()
                                .count()
                        )
                )
                .orElse(new AbstractMap.SimpleEntry<>("", null))
                .getKey();
    }

    // Вы крупный поставщик продуктов. Каждая торговая сеть делает вам заказ в виде Map<Товар, Количество>.
    // Необходимо вычислить, какой товар и в каком количестве надо поставить.
    public static Map<String, Integer> calculateGlobalOrder(List<Map<String, Integer>> orders) {
        return orders.stream()
                .map(Map::entrySet)
                .flatMap(Collection::stream)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (firstValue, secondValue) -> firstValue + secondValue));
    }
}