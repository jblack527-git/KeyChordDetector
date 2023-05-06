package com.jblack.controllers;

import com.jblack.objects.Degree;

import java.util.List;
import java.util.Scanner;

import static com.jblack.controllers.DegreeController.*;
import static java.lang.System.exit;

// https://computinglearner.com/how-to-create-a-java-console-menu-application/
public class MenuController {
    public static void printMenu(String[] options) {
        for (String option : options) {
            System.out.println(option);
        }
        System.out.println("Choose your option: ");
    }

    public static void menu(List<Degree> degreeList) {
        String[] options = {
                "1 - Print all scales and respective chords",
                "2 - Print chords for a single scale",
                "3 - Exit"
        };
        Scanner scanner = new Scanner(System.in);
        int option = 1;
        while (option != 3){
            printMenu(options);
            try {
                option = scanner.nextInt();
                switch (option) {
                    case 1: printScales(degreeList); break;
                    case 2: printScale(degreeList); break;
                    case 3: exit(0);
                }
            }
            catch (Exception ex) {
                System.out.println("Please enter an integer value between 1 and " + options.length);
                scanner.next();
            }
        }
    }

    private static void printScales(List<Degree> degreeList) {
        printDegreeList(degreeList);
    }

    private static void printScale(List<Degree> degreeList) throws Exception {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Choose a note: ");
        String note = scanner.next();
        System.out.println("Choose a scale: ");
        String scale = scanner.next();
        String capScale = scale.substring(0, 1).toUpperCase() + scale.substring(1).toLowerCase();
        printDegree(getDegree(note, capScale, degreeList));
        System.out.println();
    }
}
