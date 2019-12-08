package com.anton.chernikov.g244;

import java.util.Map;

/** The Virus class describes an object that infects computers in a certain way */
public class Virus {
    private Computer[] computers;
    private boolean[][] connections;
    private int currentIndex;
    private int startIndex;
    private ValueGenerator valueGenerator;

    /** Sets initial parameters */
    public Virus(ValueGenerator randomGenerator) {
        currentIndex = -1;
        this.valueGenerator = randomGenerator;
    }

    /** Attaches to computers on the local network and infects one of them */
    public void connect(Computer[] computers, boolean[][] connections) {
        this.computers = computers;
        this.connections = connections;
        infect();
    }

    /** Tries to infect the current computer */
    public boolean tryToInfect() {
        if (areAllInfected()) {
            return false;
        }
        for (int i = currentIndex + 1; i < computers.length; i++) {
            if (!computers[i].isInfected() && computers[i].isLinked()) {
                currentIndex = i;
                break;
            }
            if (i == computers.length - 1) {
                currentIndex = startIndex;
            }
        }
        for (int i = 0; i < computers.length; i++) {
            if (connections[currentIndex][i] && computers[i].isInfected()) {
                double probability = getProbability(computers[i].getOS());
                if (valueGenerator.getValue() <= probability) {
                    computers[currentIndex].infect();
                    break;
                }
            }
        }
        if (currentIndex == computers.length - 1) {
            currentIndex = -1;
        }
        return true;
    }

    /** Returns a probability by operating system */
    private double getProbability(OS os) {
        for (Map.Entry<Double, OS> entry : Const.systems.entrySet()) {
            if (entry.getValue().equals(os)) {
                return entry.getKey();
            }
        }
        return 0;
    }

    /** Checks computers that are at risk for infection for infection */
    private boolean areAllInfected() {
        for (int i = 0; i < computers.length; i++) {
            if (!computers[i].isInfected() && computers[i].isLinked()) {
                return false;
            }
        }
        return true;
    }

    /** Infects a computer which is determined by the value generator */
    private void infect() {
        int infectedIndex = (int) (valueGenerator.getValue() * computers.length);
        infect(infectedIndex);
    }

    /** Infects the selected computer */
    private void infect(int index) {
        computers[index].infect();
        setLink(index);
        for (int i = 0; i < computers.length; i++) {
            if (!computers[i].isInfected() && computers[i].isLinked()) {
                startIndex = i;
                break;
            }
        }
    }

    /** Marks computers that are at risk of infection */
    private void setLink(int index) {
        computers[index].setLink();
        for (int i = 0; i < computers.length; i++) {
            if (connections[index][i] && !computers[i].isLinked()) {
                setLink(i);
            }
        }
    }
}
