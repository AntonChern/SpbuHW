package com.anton.chernikov.g244;

public class Main {
    public static void main(String[] args) {
        Network network = new Network("File.txt");
        network.infect();

        System.out.println("LAN operation started\n");

        while (true) {
            network.showStatistics(System.out);
            if (!network.move()) {
                break;
            }
        }
        System.out.println("LAN operation has ended");
    }
}
