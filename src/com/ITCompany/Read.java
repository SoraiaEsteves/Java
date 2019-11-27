package com.ITCompany;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Read {

    public static void readFile(ArrayList<ActiveProgrammers> list1, ArrayList<ProjectTeam> list2, ArrayList<Date> date1) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

//            check if the storage file exists, if it doesn't, create a new one
            try{
                File file = new File(".\\src\\programmers.xml");
                if(!file.exists()) {
                    Menu.initializeFile(list1, list2, date1);
                }

                //                read the xml file and save the information in the corresponding Array List
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(".\\src\\programmers.xml");

//                read the date node
                NodeList dateList = doc.getElementsByTagName("currentDate");
                for (int i = 0; i < dateList.getLength(); i++) {
                    Node d = dateList.item(i);
                    if(d.getNodeType()== Node.ELEMENT_NODE) {
                        Element date = (Element) d;
                        Date savedDate = dateFormat.parse(date.getElementsByTagName("date").item(0).getTextContent());

                        date1.add(savedDate);
                    }

                }

//                read the programmer node
                NodeList programmerList = doc.getElementsByTagName("programmer");
                for(int i=0; i<programmerList.getLength(); i++) {
                    Node p = programmerList.item(i);
                    if(p.getNodeType()==Node.ELEMENT_NODE) {
                        Element programmer = (Element) p;
                        int id = Integer.parseInt(programmer.getElementsByTagName("id").item(0).getTextContent());
                        String firstName = programmer.getElementsByTagName("firstName").item(0).getTextContent();
                        String lastName = programmer.getElementsByTagName("lastName").item(0).getTextContent();
                        Date startDate = dateFormat.parse(programmer.getElementsByTagName("startDate").item(0).getTextContent());
                        int daysWorked = Integer.parseInt(programmer.getElementsByTagName("daysWorked").item(0).getTextContent());
                        int salary = Integer.parseInt(programmer.getElementsByTagName("salary").item(0).getTextContent());
                        boolean active = Boolean.parseBoolean(programmer.getElementsByTagName("active").item(0).getTextContent());
                        int percentage = Integer.parseInt(programmer.getElementsByTagName("percentage").item(0).getTextContent());

                        ActiveProgrammers member = new ActiveProgrammers(id, firstName, lastName, startDate, daysWorked, salary, active, percentage);
                        list1.add(member);

                    }
                }

//                read the project node
                NodeList projectList = doc.getElementsByTagName("project");
                for(int k=0; k<projectList.getLength(); k++) {
                    Node t = projectList.item(k);
                    if(t.getNodeType()==Node.ELEMENT_NODE) {
                        Element project = (Element) t;
                        int count = project.getElementsByTagName("programmerID").getLength();
                        int id = Integer.parseInt(project.getElementsByTagName("id").item(0).getTextContent());
                        Date startDate = dateFormat.parse(project.getElementsByTagName("startDate").item(0).getTextContent());
                        Date endDate = dateFormat.parse(project.getElementsByTagName("endDate").item(0).getTextContent());
                        ArrayList<Integer> programmers = new ArrayList<>();
                        ArrayList<String> activity = new ArrayList<>();
                        for (int j = 0; j <count; j++) {
                            int programmerID = Integer.parseInt(project.getElementsByTagName("programmerID").item(j).getTextContent());
                            programmers.add(programmerID);
                        }
                        for (int m = 0; m <count ; m++) {
                            String programmerActivity = project.getElementsByTagName("programmerActivity").item(m).getTextContent();
                            activity.add(programmerActivity);
                        }

                        ProjectTeam team = new ProjectTeam(id,startDate, endDate, programmers, activity);
                        list2.add(team);
                    }
                }
//                update the system
                Menu.update(list1, list2, date1);
            } catch (Exception e) {
            System.out.println(e.getMessage());
            }

    }
}
