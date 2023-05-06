package com.jblack.objects;

import java.util.Arrays;
import java.util.List;

public class Chord {
    public int position;
    public String chord;
    private static final List<String> POSITIONS = Arrays.asList("I", "II", "III", "IV", "V", "VI", "VII");

    public Chord(int position, String chord) {
        this.position = position;
        this.chord = chord;
    }

    public String toString() {
        return position + ": " + chord;
    }

    public static List<String> getPositionList() {
        return POSITIONS;
    }
}
