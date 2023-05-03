package com.jblack.objects;

import java.util.List;

public class Degree {
    public String note;
    public String scale;
    public List<Chord> chords;

    public Degree(String note, String scale, List<Chord> chords) {
        this.note = note;
        this.scale = scale;
        this.chords = chords;
    }
}
