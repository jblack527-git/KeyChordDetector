package com.jblack.controllers;

import com.jblack.objects.Chord;
import com.jblack.objects.Degree;
import com.jblack.utils.RomanToIntegerConverter;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static com.jblack.utils.FileToListConverter.fileToList;

public class DegreeController {
    private static final List<String> POSITIONS = Arrays.asList("I", "II", "III", "IV", "V", "VI", "VII");

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

    public static void printDegreeList(List<Degree> degreeList) {
        for (Degree degree : degreeList) {
            printDegree(degree);
        }
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
            for(String position : POSITIONS) {
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
