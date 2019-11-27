package com.ITCompany;

import java.text.ParseException;
import java.util.ArrayList;

public interface Programmers {

    void addProgrammer(ArrayList<ActiveProgrammers> list1) throws ParseException;
    double calculateSalary(ActiveProgrammers person);
}
