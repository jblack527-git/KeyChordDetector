package com.jblack.controllers;

import com.jblack.objects.Chord;
import com.jblack.objects.Degree;
import com.jblack.utils.RomanToIntegerConverter;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.jblack.objects.Chord.getPositionList;
import static com.jblack.objects.Degree.getNoteList;
import static com.jblack.objects.Degree.getScaleList;
import static com.jblack.utils.FileToListConverter.fileToList;

public class DegreeController {
    public static void printDegree(Degree degree) {
        System.out.println(degree.note
                + " " + degree.scale
                + " | " + degree.chords.toString().substring(1, degree.chords.toString().length() - 1));
    }

    public static Degree getDegree(String note, String scale, List<Degree> degreeList) throws Exception {
        for (Degree degree : degreeList) {
            if (degree.note.contains("#") || degree.note.contains("b")) {
                if (degree.note.contains(note) && degree.scale.equals(scale)) {
                    return degree;
                }
            }
            if (degree.note.equals(note) && degree.scale.equals(scale)) {
                return degree;
            }
        }

        throw new Exception("Degree not found");
    }

    public static List<Degree> getDegreesFromChords(List<String> chordList, List<Degree> degreeList) {
        List<Degree> degreesContainingChords = new ArrayList<>();
        for (Degree degree : degreeList) {
            int chordsPresent = 0;
            for (String chord : chordList) {
                for (Chord degreeChord : degree.chords) {
                    if (degreeChord.chord.contains("/")) {
                        var accidentals = degreeChord.chord.split("/");
                        if (accidentals[0].equals(chord) || accidentals[1].equals(chord)) {
                            chordsPresent++;
                        }
                    }
                    if (degreeChord.chord.equals(chord)) {
                        chordsPresent++;
                    }
                }
            }
            if (chordsPresent == chordList.stream().count()) {
                degreesContainingChords.add(degree);
            }
        }

        return degreesContainingChords;
    }

    public static void printDegreeList(List<Degree> degreeList) {
        for (Degree degree : degreeList) {
            printDegree(degree);
        }
    }

    public static void printNoteList() {
        System.out.println(getNoteList());
    }

    public static void printScaleList() {
        System.out.println(getScaleList());
    }

    public static List<Degree> initialiseDegreeList() throws IOException {
        File chordsFile = new File("src/com/jblack/resources/chords_dataset.csv");
        List<Map<String, String>> stringList = fileToList(chordsFile);
        return createDegreeList(stringList);
    }

    private static List<Degree> createDegreeList(List<Map<String, String>> stringList) {
        List<Degree> degreeList = new ArrayList<>();
        for (Map entry : stringList) {
            List<Chord> chordList = new ArrayList<>();
            for(String position : getPositionList()) {
                if (entry.containsKey(position)) {
                    chordList.add(new Chord(RomanToIntegerConverter.convertRomanToInt(position), entry.get(position).toString()));
                }
            }
            degreeList.add(new Degree(entry.get("Note").toString(),
                    entry.get("Scale").toString(),
                    chordList));
        }

        return degreeList;
    }
}
