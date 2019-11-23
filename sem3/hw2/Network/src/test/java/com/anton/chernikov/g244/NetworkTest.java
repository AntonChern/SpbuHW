package com.anton.chernikov.g244;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NetworkTest {

    @Test
    void move() {
        Network network = new Network("TestFile.txt");

        assertFalse(network.getComputer(0).isInfected());
        assertFalse(network.getComputer(1).isInfected());
        assertFalse(network.getComputer(2).isInfected());
        assertFalse(network.getComputer(3).isInfected());
        assertFalse(network.getComputer(4).isInfected());

        network.setVirus(new Virus(new ZeroGenerator()));
        for (int i = 0; i < 2; i++) {
            assertTrue(network.getComputer(0).isInfected());
            assertFalse(network.getComputer(1).isInfected());
            assertFalse(network.getComputer(2).isInfected());
            assertFalse(network.getComputer(3).isInfected());
            assertFalse(network.getComputer(4).isInfected());
            network.move();
        }

        for (int i = 0; i < 2; i++) {
            assertTrue(network.getComputer(0).isInfected());
            assertTrue(network.getComputer(2).isInfected());
            assertFalse(network.getComputer(1).isInfected());
            assertFalse(network.getComputer(3).isInfected());
            assertFalse(network.getComputer(4).isInfected());
            network.move();
        }

        for (int i = 0; i < 2; i++) {
            assertTrue(network.getComputer(0).isInfected());
            assertTrue(network.getComputer(2).isInfected());
            assertTrue(network.getComputer(4).isInfected());
            assertFalse(network.getComputer(1).isInfected());
            assertFalse(network.getComputer(3).isInfected());
            network.move();
        }

        assertTrue(network.getComputer(0).isInfected());
        assertTrue(network.getComputer(2).isInfected());
        assertTrue(network.getComputer(3).isInfected());
        assertTrue(network.getComputer(4).isInfected());
        assertFalse(network.getComputer(1).isInfected());
        network.move();

        assertTrue(network.getComputer(0).isInfected());
        assertTrue(network.getComputer(1).isInfected());
        assertTrue(network.getComputer(2).isInfected());
        assertTrue(network.getComputer(3).isInfected());
        assertTrue(network.getComputer(4).isInfected());
    }

}