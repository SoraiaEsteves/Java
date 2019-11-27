package com.ITCompany;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;


public class Main {

    public static void main(String[] args) throws ParseException {
//        initialize the arrays to save the data we want stored
        ArrayList<ActiveProgrammers> list1 = new ArrayList<>();
        ArrayList<ProjectTeam> list2 = new ArrayList<>();
        ArrayList<Date> date1 = new ArrayList<>();

//        get the information from the xlm file onto the lists
        Read file = new Read();
        file.readFile(list1, list2, date1);

//        call the application menu in order to access all functionalities
        Menu menu = new Menu();
        menu.startMenu(list1, list2, date1);
        System.out.println(list1);

    }

}

