package com.anton.chernikov.g244;

public class Main {
    public static void main(String[] args) {
        Network network = new Network("File.txt");
        network.setVirus(new Virus(new RandomGenerator()));

        System.out.println("LAN operation started\n");

        network.showStatistics(System.out);
        while (network.move()) {
            network.showStatistics(System.out);
        }

        System.out.println("LAN operation has ended");
    }
}
