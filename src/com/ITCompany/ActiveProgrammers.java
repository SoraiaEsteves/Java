package com.ITCompany;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class ActiveProgrammers implements Programmers {
    public int id;
    public String firstName;
    public String lastName;
    public Date startDate;
    public int daysWorked;
    public int salary;
    public boolean active;
    public int percentage;

    public ActiveProgrammers() {
    }

    public ActiveProgrammers(int id, String firstName, String lastName, Date startDate, int daysWorked, int salary, boolean active, int percentage) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.startDate = startDate;
        this.daysWorked = daysWorked;
        this.salary = salary;
        this.active = active;
        this.percentage = percentage;
    }

    public ActiveProgrammers(int id, String firstName, String lastName, String startDate, int daysWorked, int salary, boolean active, int percentage) {
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Date getStartDate() {
        return startDate;
    }

    public int getSalary() {
        return salary;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getDaysWorked() {
        return daysWorked;
    }

    public int getPercentage() {
        return percentage;
    }

    //    add a programmer to the pool of programmers in the system
    public void addProgrammer(ArrayList<ActiveProgrammers> list1) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Scanner scanner = new Scanner(System.in);
        ActiveProgrammers a = new ActiveProgrammers();
        int c = list1.size();
        a = list1.get(c - 1);
        int id = a.getId() + 1;
        System.out.println("First Name: ");
        String firstName = scanner.nextLine();
        System.out.println("Last Name: ");
        String lastName = scanner.nextLine();
        System.out.println("Start Date: ");
        String date = scanner.nextLine();
        Date startDate = dateFormat.parse(date);
        System.out.println("Hourly Wage: ");
        int salary = scanner.nextInt();
        System.out.println("Salary Percentage: ");
        int percentage = scanner.nextInt();
        this.active = false;
        ActiveProgrammers member = new ActiveProgrammers(id, firstName, lastName, startDate, daysWorked, salary, active, percentage);
        list1.add(member);

    }

    //    calculate the salary of a programmer based on the amount of days worked and the percentage they receive
    public double calculateSalary(ActiveProgrammers person) {
        return person.getDaysWorked() * person.getSalary() * person.getPercentage() / 100;
    }


}
