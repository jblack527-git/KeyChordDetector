package com.jblack.objects;

public class Chord {
    public int position;
    public String chord;

    public Chord(int position, String chord) {
        this.position = position;
        this.chord = chord;
    }

    public String toString() {
        return position + ": " + chord;
    }
}
