package com.ITCompany;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class ProjectTeam {
    public int id;
    public Date startDate;
    public Date endDate;
    public ArrayList<Integer> programmerID;
    public ArrayList<String> programmerActivity;

    public ProjectTeam() {
    }

    public ProjectTeam(int i, String s, Date s1, int i1, String tester, int i2, String analyst) {
    }

    public ProjectTeam(int id, String startDate, String endDate, ArrayList<Integer> programmerID, ArrayList<String> programmerActivity) {
    }

    public ProjectTeam(int i, String s, String s1, int i1, String tester, int i2, String analyst) {
    }

    //    add a new project to the pool of available projects
    public void addProject(ArrayList<ActiveProgrammers> list1, ArrayList<ProjectTeam> list2) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Integer> adding = new ArrayList<>();
        ArrayList<String> func = new ArrayList<>();
        ProjectTeam t = new ProjectTeam();
        int s = list2.size();
        t = list2.get(s - 1);
        int id = t.getId() + 1;
        System.out.println("Start Date: ");
        String startDate = scanner.nextLine();

        System.out.println("End Date: ");
        String endDate = scanner.nextLine();
        int n = 0;
        boolean exit = false;
        for (ActiveProgrammers programmer : list1) {
            if (!programmer.active) {
                n++;
            }
        }
//        add programmers to the project
        if (n < 2) {
            System.out.println("You don't have enough inactive programmers to create a Project Team. Please go back, and insert new Programmers.");
            System.out.println(" ");
            Menu.printMenu();
            return;
        } else {
            while (!exit) {
                for (ActiveProgrammers programmer : list1) {
                    if (!programmer.active) {
                        System.out.println(programmer.getId() + "- " + programmer.getFirstName() + " " + programmer.getLastName());
                    }
                }
                if (n >= 2) {
                    System.out.println("Add from the existing programmers?");
                    String yn = scanner.nextLine();
                    if (yn.equals("Y") || yn.equals("y")) {
                        System.out.println("The programmers were added to the team.");
                        for (ActiveProgrammers programmer : list1) {
                            if (!programmer.isActive()) {
                                adding.add(programmer.getId());
                                programmer.setActive(true);
                            }
                        }
                        System.out.println("Please define the activity of the programmers in the project. ");
                        System.out.println("Programmer 1: ");
                        String activity1 = scanner.nextLine();
                        System.out.println("Programmer 2: ");
                        String activity2 = scanner.nextLine();
                        func.add(activity1);
                        func.add(activity2);
                        ProjectTeam team = new ProjectTeam(id, startDate, endDate, adding, func);
                        list2.add(team);
                        System.out.println("Project was created with success.");
                        exit = true;

                    } else if (yn.equals("N") || yn.equals("n")) {
                        return;
                    } else {
                        System.out.println("Invalid character");
                        break;
                    }
                }
            }
        }

    }

    public ProjectTeam(int id, Date startDate, Date endDate, ArrayList<Integer> programmerID, ArrayList<String> programmerActivity) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.programmerID = programmerID;
        this.programmerActivity = programmerActivity;
    }

    public int getId() {
        return id;
    }

    public ArrayList<Integer> getProgrammerID() {
        return programmerID;
    }


    public ArrayList<String> getProgrammerActivity() {
        return programmerActivity;
    }


    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

}
