package com.jblack;

import com.jblack.objects.Degree;

import java.util.List;

import static com.jblack.controllers.DegreeController.initialiseDegreeList;
import static com.jblack.controllers.MenuController.menu;

public class Main {
    public static void main(String[] args) throws Exception {
        List<Degree> degreeList = initialiseDegreeList();
        menu(degreeList);
    }
}
