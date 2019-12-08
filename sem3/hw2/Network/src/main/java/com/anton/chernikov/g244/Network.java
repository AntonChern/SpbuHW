package com.anton.chernikov.g244;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;

/** The Network class describes the state of the local network */
public class Network {
    private Computer[] computers;
    private boolean[][] connections;
    private int moves;
    private Virus virus;

    /** Fills data on a local network with data from a file */
    public Network(String fileName) {
        try {
            Scanner file = new Scanner(new FileInputStream(fileName));
            int size = file.nextInt();
            computers = new Computer[size];
            for (int i = 0; i < computers.length; i++) {
                computers[i] = new Computer();
            }
            connections = new boolean[size][size];
            for (int i = 0; i < computers.length; i++) {
                for (int j = 0; j < computers.length; j++) {
                    connections[i][j] = file.nextInt() == 1;
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
                    default: {
                        break;
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /** Attaches the virus to this local network */
    public void setVirus(Virus virus) {
        virus.connect(computers, connections);
        this.virus = virus;
    }

    /** Changes the state of this network */
    public boolean move() {
        moves++;
        return virus.tryToInfect();
    }

    /** Prints statistics */
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

    /** Returns a computer by index */
    public Computer getComputer(int index) {
        return computers[index];
    }
}

