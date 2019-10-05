package com.anton.chernikov.g244;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;

/** Class describing the operation of the local network */
public class Network {
    private Computer[] computers;
    private int[][] connections;
    private int currentIndex;
    private int startIndex;
    private int moves;

    /** Constructor filling data on a local network with data from a file */
    public Network(String fileName) {
        try {
            Scanner file = new Scanner(new FileInputStream(fileName));
            currentIndex = -1;
            moves = 0;
            int size = file.nextInt();
            computers = new Computer[size];
            for (int i = 0; i < computers.length; i++) {
                computers[i] = new Computer();
            }
            connections = new int[size][size];
            for (int i = 0; i < computers.length; i++) {
                for (int j = 0; j < computers.length; j++) {
                    connections[i][j] = file.nextInt();
                }
            }
            file.nextLine();
            file.nextLine();
            for (int i = 0; i < computers.length; i++) {
                switch (file.nextLine()) {
                    case "Windows": {
                        computers[i].setOS(OS.Windows);
                        break;
                    }
                    case "Linux": {
                        computers[i].setOS(OS.Linux);
                        break;
                    }
                    case "MacOS": {
                        computers[i].setOS(OS.MacOS);
                        break;
                    }
                    case "TestOS": {
                        computers[i].setOS(OS.TestOS);
                        break;
                    }
                    default: {
                        break;
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /** Method infecting a random computer */
    public void infect() {
        Random rand = new Random();
        int infectedIndex = (int) (rand.nextDouble() * 100) % computers.length;
        infect(infectedIndex);
    }

    /** Method infecting the selected computer */
    public void infect(int index) {
        computers[index].infect();
        setLink(index);
        for (int i = 0; i < computers.length; i++) {
            if (!computers[i].isInfected() && computers[i].isLinked()) {
                startIndex = i;
                break;
            }
        }
    }

    /** Method marking computers that are at risk of infection */
    private void setLink(int index) {
        computers[index].setLink();
        for (int i = 0; i < computers.length; i++) {
            if (connections[index][i] == 1 && !computers[i].isLinked()) {
                setLink(i);
            }
        }
    }

    /** Method trying to infect the current computer */
    public boolean move() {
        for (int i = currentIndex + 1; i < computers.length; i++) {
            if (!computers[i].isInfected() && computers[i].isLinked()) {
                currentIndex = i;
                break;
            }
            if (i == computers.length - 1) {
                currentIndex = startIndex;
            }
        }
        if (areAllInfected()) {
            return false;
        }
        for (int i = 0; i < computers.length; i++) {
            if (connections[currentIndex][i] == 1 && computers[i].isInfected()) {
                double probability = getProbability(computers[i].getOS());
                Random rand = new Random();
                if (rand.nextDouble() < probability) {
                    computers[currentIndex].infect();
                    moves++;
                    if (currentIndex == computers.length - 1) {
                        currentIndex = -1;
                    }
                    return true;
                }
            }
        }
        moves++;
        if (currentIndex == computers.length - 1) {
            currentIndex = -1;
        }
        return true;
    }

    /** Auxiliary method for finding a system */
    private double getProbability(OS os) {
        for (Map.Entry<Double, OS> entry : Const.systems.entrySet()) {
            if (entry.getValue().equals(os)) {
                return entry.getKey();
            }
        }
        return 0;
    }

    /** Method checking computers that are at risk for infection for infection */
    private boolean areAllInfected() {
        for (int i = 0; i < computers.length; i++) {
            if (!computers[i].isInfected() && computers[i].isLinked()) {
                return false;
            }
        }
        return true;
    }

    /** Method printing statistics */
    public void showStatistics(OutputStream out) {
        try {
            out.write("Move ".getBytes());
            out.write(Integer.toString(moves).getBytes());
            out.write(':');
            out.write('\n');
            for (int i = 0; i < computers.length; i++) {
                out.write(Integer.toString(i + 1).getBytes());
                out.write(" -".getBytes());
                if (!computers[i].isInfected()) {
                    out.write(" not".getBytes());
                }
                out.write(" infected".getBytes());
                out.write('\n');
            }
            out.write('\n');
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /** Method returning a computer by index */
    public Computer getComputer(int index) {
        return computers[index];
    }
}

