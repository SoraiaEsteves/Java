package com.ITCompany;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class Menu {

    //    set up of the options available on the application
    public static void startMenu(ArrayList<ActiveProgrammers> list1, ArrayList<ProjectTeam> list2, ArrayList<Date> date1) throws ParseException {
        System.out.println("Program is loading...");
        Scanner scanner = new Scanner(System.in);
        boolean quit = false;
        int choice = 0;
        printMenu();
        while (!quit) {
            System.out.println("Please select an action: ");
            choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 0:
                    printMenu();
                    break;
                case 1:
                    Print.printReport(list1, list2, date1);
                    break;
                case 2:
                    ActiveProgrammers prog = new ActiveProgrammers();
                    prog.addProgrammer(list1);
                    save(list1, list2, date1);
                    break;
                case 3:
                    ProjectTeam proj = new ProjectTeam();
                    proj.addProject(list1, list2);
                    save(list1, list2, date1);
                    break;
                case 4:
                    update(list1, list2, date1);
                    break;
                case 5:
                    quit = true;
                    break;
            }
        }
    }

    //    list printed of the options
    public static void printMenu() {
        System.out.println("\t 0 - Options");
        System.out.println("\t 1 - Print Company Report");
        System.out.println("\t 2 - Add Active Programmer");
        System.out.println("\t 3 - Add Project Team");
        System.out.println("\t 4 - Update");
        System.out.println("\t 5 - Quit program");

    }


    //    save the data to the storage file
    public static void save(ArrayList<ActiveProgrammers> list1, ArrayList<ProjectTeam> list2, ArrayList<Date> date1) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            File file = new File("./resources/programmers.xml");
            if (!file.exists()) {
                initializeFile(list1, list2, date1);
            }

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(file);

            Element root = doc.getDocumentElement();

//            save the date node
            NodeList dateList = doc.getElementsByTagName("currentDate");
            for (int i = 0; i < dateList.getLength(); i++) {
                Node eachNode = dateList.item(i);
                eachNode.getParentNode().removeChild(eachNode);
                i--;
            }

            for (int i = 0; i < date1.size(); i++) {
                String date = dateFormat.format(date1.get(i));
                Element dates = doc.createElement("currentDate");

                Element pdate = doc.createElement("date");
                pdate.appendChild(doc.createTextNode(date));
                dates.appendChild(pdate);

                root.appendChild(dates);
            }

//            save the programmers node
            NodeList programmerList = doc.getElementsByTagName("programmer");

            for (int i = 0; i < programmerList.getLength(); i++) {
                Node eachNode = programmerList.item(i);
                eachNode.getParentNode().removeChild(eachNode);
                i--;
            }

            for (int i = 0; i < list1.size(); i++) {
                String id = Integer.toString(list1.get(i).getId());
                String firstName = list1.get(i).getFirstName();
                String lastName = list1.get(i).getLastName();
                String startDate = dateFormat.format(list1.get(i).getStartDate());
                String daysWorked = Integer.toString(list1.get(i).getDaysWorked());
                String salary = Integer.toString(list1.get(i).getSalary());
                String active = Boolean.toString(list1.get(i).isActive());
                String percentage = Integer.toString(list1.get(i).getPercentage());

                Element prog = doc.createElement("programmer");

                Element pId = doc.createElement("id");
                pId.appendChild(doc.createTextNode(id));
                prog.appendChild(pId);

                Element pFirstName = doc.createElement("firstName");
                pFirstName.appendChild(doc.createTextNode(firstName));
                prog.appendChild(pFirstName);

                Element pLastName = doc.createElement("lastName");
                pLastName.appendChild(doc.createTextNode(lastName));
                prog.appendChild(pLastName);

                Element pStart = doc.createElement("startDate");
                pStart.appendChild(doc.createTextNode(startDate));
                prog.appendChild(pStart);

                Element pDays = doc.createElement("daysWorked");
                pDays.appendChild(doc.createTextNode(daysWorked));
                prog.appendChild(pDays);

                Element pSalary = doc.createElement("salary");
                pSalary.appendChild(doc.createTextNode(salary));
                prog.appendChild(pSalary);

                Element pActive = doc.createElement("active");
                pActive.appendChild(doc.createTextNode(active));
                prog.appendChild(pActive);

                Element pPercentage = doc.createElement("percentage");
                pPercentage.appendChild(doc.createTextNode(percentage));
                prog.appendChild(pPercentage);

                root.appendChild(prog);
            }

//                save the projects node
            NodeList projectList = doc.getElementsByTagName("project");
            for (int i = 0; i < projectList.getLength(); i++) {
                Node eachNode = projectList.item(i);
                eachNode.getParentNode().removeChild(eachNode);
                i--;
            }

            for (ProjectTeam project : list2) {
                String id = Integer.toString(project.getId());
                String startDate = dateFormat.format(project.getStartDate());
                String endDate = dateFormat.format(project.getEndDate());

                Element proj = doc.createElement("project");
                Element pId = doc.createElement("id");
                pId.appendChild(doc.createTextNode(id));
                proj.appendChild(pId);
                Element pStart = doc.createElement("startDate");
                pStart.appendChild(doc.createTextNode(startDate));
                proj.appendChild(pStart);

                Element pEnd = doc.createElement("endDate");
                pEnd.appendChild(doc.createTextNode(endDate));
                proj.appendChild(pEnd);


                for (int j = 0; j < project.getProgrammerID().size(); j++) {
                    Element prog = doc.createElement("programmerID");
                    prog.appendChild(doc.createTextNode(project.getProgrammerID().get(j).toString()));
                    proj.appendChild(prog);
                }
                for (int k = 0; k < project.getProgrammerActivity().size(); k++) {
                    Element prog = doc.createElement("programmerActivity");
                    prog.appendChild(doc.createTextNode(project.getProgrammerActivity().get(k)));
                    proj.appendChild(prog);
                }

                root.appendChild(proj);
            }

//           send everything to the file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("./resources/programmers.xml"));
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(source, result);
            System.out.println("Your data has been saved!");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


    }

    //    add information to the xml file
    public static void initializeFile(ArrayList<ActiveProgrammers> list1, ArrayList<ProjectTeam> list2, ArrayList<Date> date1) {
        ArrayList<ActiveProgrammers> list3 = new ArrayList<>();
        ArrayList<ProjectTeam> list4 = new ArrayList<>();
        ArrayList<Integer> memberID1 = new ArrayList<>();
        ArrayList<String> memberActivity1 = new ArrayList<>();
        ArrayList<Integer> memberID2 = new ArrayList<>();
        ArrayList<String> memberActivity2 = new ArrayList<>();
        ArrayList<Date> date = new ArrayList<>();

        Date systemDate = new Date(1 / 11 / 2019);
        date.add(systemDate);

        ActiveProgrammers programmer1 = new ActiveProgrammers(1, "Kaladin", "Windrunner", "24/10/2019", 0, 20, true, 100);
        ActiveProgrammers programmer2 = new ActiveProgrammers(2, "Shallan", "Lightweaver", "3/11/2019", 0, 15, true, 50);
        ActiveProgrammers programmer3 = new ActiveProgrammers(3, "Renarin", "Truthwatcher", "24/10/2019", 0, 10, true, 100);
        ActiveProgrammers programmer4 = new ActiveProgrammers(4, "Jasnah", "Elsecaller", "24/10/2019", 0, 25, true, 50);
        list3.add(programmer1);
        list3.add(programmer2);
        list3.add(programmer3);
        list3.add(programmer4);

        memberID1.add(programmer1.getId());
        memberID1.add(programmer2.getId());
        memberActivity1.add("Tester");
        memberActivity1.add("Analyst");
        ProjectTeam project1 = new ProjectTeam(1, "01/11/2019", "18/11/2019", 1, "Tester", 2, "Analyst");
        memberID2.add(programmer3.getId());
        memberID2.add(programmer4.getId());
        memberActivity2.add("Cookie Hunter");
        memberActivity2.add("Gatherer of Pizza");
        ProjectTeam project2 = new ProjectTeam(2, "15/11/2019", "30/11/2019", 3, "Cookie Hunter", 4, "Gatherer of Pizza");

        list4.add(project1);
        list4.add(project2);

        save(list1, list2, date1);

    }

    //    update the system according to the specifications
    public static void update(ArrayList<ActiveProgrammers> list1, ArrayList<ProjectTeam> list2, ArrayList<Date> date1) {
        Calendar time = Calendar.getInstance();
        time.setTime(date1.get(0));
        time.add(Calendar.DAY_OF_MONTH, 1);
        date1.set(0, time.getTime());
        save(list1, list2, date1);
    }
}
