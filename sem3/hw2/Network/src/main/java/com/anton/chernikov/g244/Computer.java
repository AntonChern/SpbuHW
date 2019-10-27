package com.anton.chernikov.g244;

/** Class describing computer */
public class Computer {
    private OS os;
    private boolean isInfected;
    private boolean isLinked;

    public Computer() {
        isInfected = false;
        isLinked = false;
    }

    /** Method infecting a computer */
    public void infect() {
        isInfected = true;
    }

    /** Method returning computer infection status */
    public boolean isInfected() {
        return isInfected;
    }

    /** Method marking a computer as at risk of infection */
    public void setLink() {
        isLinked = true;
    }

    /** Method returning the status of being at risk of infection */
    public boolean isLinked() {
        return isLinked;
    }

    /** Method setting the computer's operating system as selected */
    public void setOS(OS os) {
        this.os = os;
    }

    /** Method returning the computer operating system */
    public OS getOS() {
        return os;
    }
}
