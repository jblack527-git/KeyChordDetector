package com.jblack.objects;

import java.util.Arrays;
import java.util.List;

public class Degree {
    public String note;
    public String scale;
    public List<Chord> chords;

    private static final List<String> NOTES = Arrays.asList("C", "C#/Db", "D", "D#/Eb", "E", "F", "F#/Gb", "G", "G#/Ab",
            "A", "A#/Bb", "B");
    private static final List<String> SCALES = Arrays.asList("Major", "Minor", "Harmonic", "Melodic");

    public Degree(String note, String scale, List<Chord> chords) {
        this.note = note;
        this.scale = scale;
        this.chords = chords;
    }

    public static List<String> getNoteList() {
        return NOTES;
    }

    public static List<String> getScaleList() {
        return SCALES;
    }
}
