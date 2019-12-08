package com.anton.chernikov.g244;

/** The Computer class describes computer which contains operating system, infection status and status of being at risk of infection */
public class Computer {
    private OS os;
    private boolean isInfected;
    private boolean isLinked;

    /** Infects this computer */
    public void infect() {
        isInfected = true;
    }

    /** Returns computer infection status */
    public boolean isInfected() {
        return isInfected;
    }

    /** Marks this computer as at risk of infection */
    public void setLink() {
        isLinked = true;
    }

    /** Returns the status of being at risk of infection */
    public boolean isLinked() {
        return isLinked;
    }

    /** Sets the computer's operating system as selected */
    public void setOS(OS os) {
        this.os = os;
    }

    /** Returns the computer operating system */
    public OS getOS() {
        return os;
    }
}
