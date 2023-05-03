package com.jblack;

import com.jblack.objects.Degree;

import java.io.File;
import java.util.List;
import java.util.Map;

import static com.jblack.controllers.DegreeController.*;
import static com.jblack.utils.FileToListConverter.fileToList;

public class Main {

    public static void main(String[] args) throws Exception {
        File chordsFile = new File("src/com/jblack/resources/chords_dataset.csv");
        List<Map<String, String>> stringList = fileToList(chordsFile);
        List<Degree> degreeList = createDegreeList(stringList);
        printDegreeList(degreeList);
        printDegree(getDegree("C", "Major", degreeList));
    }
}
