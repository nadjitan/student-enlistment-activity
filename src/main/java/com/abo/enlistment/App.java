package com.abo.enlistment;

import static java.lang.System.out;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class App {
    public static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        int userInput = 1;

        while (userInput != 0)
        {
            Enlistment();

            out.print("Would you like to enlist in another Section? 0 = No, 1 = Yes: ");
            userInput = scanner.nextInt();
        }
    }

    public static void Enlistment() {
        Section section1 = new Section("A", new Schedule("MTH", "0830", "1300"), new Room("Room1", 5));
        Section section2 = new Section("B", new Schedule("TF", "1000", "1430"), new Room("Room2", 5));
        Section section3 = new Section("C", new Schedule("WS", "1300", "1700"), new Room("Room3", 5));
        Section section4 = new Section("C", new Schedule("WS", "1300", "1700"), new Room("Room3", 5));

        HashMap<Integer, Section> sections = new HashMap<Integer, Section>();

        sections.put(1, section1);
        sections.put(2, section2);
        sections.put(3, section3);

        out.println("----------- STUDENT ENLISTMENT ----------- ");
        out.println("(1) Section 1: " + section1.getSchedule().getDay() + " " + section1.getSchedule().getPeriod() + " "
                + section1.getRoom());
        out.println("(2) Section 2: " + section2.getSchedule().getDay() + " " + section2.getSchedule().getPeriod() + " "
                + section2.getRoom());
        out.println("(3) Section 3: " + section3.getSchedule().getDay() + " " + section3.getSchedule().getPeriod() + " "
                + section3.getRoom());

        try {
            Student student = new Student(1);

            out.println("Select a section (enter the corresponding number of section): ");
            int sectionNum = scanner.nextInt();

            if (sections.containsKey(sectionNum)) {
                student.enlist(sections.get(sectionNum));

                out.println("You are now enlisted in section " + sectionNum);
            } else {
                throw new NoSuchElementException();
            }
        } catch (InputMismatchException e) {
            out.println("Please enter an integer");
        } catch (NoSuchElementException e) {
            out.println("Please pick an existing section");
        }
        
    }
}
