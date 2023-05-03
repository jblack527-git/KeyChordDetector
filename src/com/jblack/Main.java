package com.jblack;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.jblack.objects.Chord;
import com.jblack.objects.Degree;
import com.jblack.utils.RomanToIntegerConverter;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        File chordsFile = new File("src/com/jblack/resources/chords_dataset.csv");
        List<Map<String, String>> stringList = readData(chordsFile);
        List<Degree> degreeList = createDegreeList(stringList);
        printDegreeList(degreeList);
        printDegree(getDegree("C", "Major", degreeList));
    }

    private static void printDegreeList(List<Degree> degreeList) {
        for (Degree degree : degreeList) {
            printDegree(degree);
        }
    }

    private static void printDegree(Degree degree) {
        System.out.println(degree.note
                + " " + degree.scale
                + " " + degree.chords.toString());
    }

    private static Degree getDegree(String note, String scale, List<Degree> degreeList) throws Exception {
        for (Degree degree : degreeList) {
            if (degree.note.equals(note) && degree.scale.equals(scale)) {
                return degree;
            }
        }

        throw new Exception("Degree not found");
    }

    private static List<Map<String, String>> readData(File file) throws IOException {
        // https://stackoverflow.com/questions/20068383/convert-csv-values-to-a-hashmap-key-value-pairs-in-java
        List<Map<String, String>> dataList = new LinkedList<>();
        CsvMapper mapper = new CsvMapper();
        CsvSchema schema = CsvSchema.emptySchema().withHeader();
        MappingIterator<Map<String, String>> iterator = mapper.readerFor(Map.class)
                .with(schema)
                .readValues(file);
        while (iterator.hasNext()) {
            dataList.add(iterator.next());
        }
        return dataList;
    }

    private static List<Degree> createDegreeList(List<Map<String, String>> stringList) {
        List<Degree> degreeList = new ArrayList<>();
        for (Map entry : stringList) {
            List<String> positions = Arrays.asList("I", "II", "III", "IV", "V", "VI", "VII");
            List<Chord> chordList = new ArrayList<>();
            // iterating through positions array
            for(String position : positions) {
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
