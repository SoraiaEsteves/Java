package com.ITCompany;

import java.text.SimpleDateFormat;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Print {
    public static void printReport(ArrayList<ActiveProgrammers> list1, ArrayList<ProjectTeam> list2, ArrayList<Date> date1) {
//        current date
        Date currentDate = date1.get(0);

        Date timePoint = new Date();
        Calendar time = Calendar.getInstance();
        time.setTime(timePoint);
        YearMonth dayCount = YearMonth.of(timePoint.getYear(), timePoint.getMonth());
        int daysInMonth = dayCount.lengthOfMonth();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");


        int totalActiveDays = 0;
        int active = 0;
        int daysLeft = 0;

        //        days worked by each programmer
        for (ActiveProgrammers prog : list1) {
            totalActiveDays += prog.getDaysWorked();
            if (prog.isActive() || (!prog.isActive() && prog.getDaysWorked() != 0)) {
                active++;
            }
        }

//        days left of work in the month
        for (ProjectTeam proj : list2) {
            Calendar end = Calendar.getInstance();
            end.setTime(proj.getEndDate());
            if (time.get(Calendar.MONTH) == end.get(Calendar.MONTH)) {
                daysLeft += end.get(Calendar.DAY_OF_MONTH) - currentDate.getTime();
            } else {
                daysLeft += (daysInMonth - time.get(Calendar.DAY_OF_MONTH)) * proj.getProgrammerID().size();
            }

        }

        if (daysLeft < 0) {
            daysLeft = 0;
        }


//        company report with all the calculations
        System.out.println("IT COMPANY - Report");
        System.out.println(" ");
        System.out.println("IT Company is currently composed of " + list2.size() + " project teams, and " + list1.size() + " programmers.");
        System.out.println("This month, " + active + " programmers have worked a total of " + totalActiveDays + " days, with " + daysLeft + " days left of work for the rest of the month.");


        System.out.println("Project Team details: ");
        for (ProjectTeam proj : list2) {
            System.out.println("Project Team " + proj.getId());
            for (int i = 0; i < proj.getProgrammerID().size(); i++) {
                for (ActiveProgrammers prog : list1) {
                    if (prog.getId() == proj.getProgrammerID().get(i)) {
                        System.out.println(prog.getLastName() + ", " + prog.getFirstName() + ", in charge of " + proj.getProgrammerActivity().get(i) + " from " + dateFormat.format(prog.getStartDate())
                                + " to " + dateFormat.format(proj.getEndDate()) + ", has worked " + prog.getDaysWorked() + " days this month. Total salary: " + prog.calculateSalary(prog) + "€)");
                        break;
                    }
                }
            }
        }

    }
}
