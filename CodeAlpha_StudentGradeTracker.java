import java.util.ArrayList;
import java.util.Scanner;

public class CodeAlpha_StudentGradeTracker {

    static class Student {
        String name;
        ArrayList<Double> grades;

        Student(String name) {
            this.name = name;
            this.grades = new ArrayList<>();
        }

        double getAverage() {
            if (grades.isEmpty()) return 0;
            double sum = 0;
            for (double g : grades) {
                sum += g;
            }
            return sum / grades.size();
        }

        double getHighest() {
            if (grades.isEmpty()) return 0;
            double max = grades.get(0);
            for (double g : grades) {
                if (g > max) max = g;
            }
            return max;
        }

        double getLowest() {
            if (grades.isEmpty()) return 0;
            double min = grades.get(0);
            for (double g : grades) {
                if (g < min) min = g;
            }
            return min;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Student> students = new ArrayList<>();
        boolean running = true;

        System.out.println("=====================================");
        System.out.println("   STUDENT GRADE TRACKER - CodeAlpha");
        System.out.println("=====================================");

        while (running) {
            printMenu();
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    addStudent(scanner, students);
                    break;
                case "2":
                    addGrades(scanner, students);
                    break;
                case "3":
                    displaySummaryReport(students);
                    break;
                case "4":
                    running = false;
                    System.out.println("Exiting... Thank you for using Student Grade Tracker!");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 4.\n");
            }
        }

        scanner.close();
    }

    static void printMenu() {
        System.out.println("\nMENU:");
        System.out.println("1. Add a new student");
        System.out.println("2. Add grades for a student");
        System.out.println("3. Display summary report");
        System.out.println("4. Exit");
        System.out.print("Enter your choice: ");
    }

    static void addStudent(Scanner scanner, ArrayList<Student> students) {
        System.out.print("Enter student name: ");
        String name = scanner.nextLine().trim();

        if (name.isEmpty()) {
            System.out.println("Name cannot be empty.\n");
            return;
        }

        students.add(new Student(name));
        System.out.println(name + " added successfully!\n");
    }

    static void addGrades(Scanner scanner, ArrayList<Student> students) {
        if (students.isEmpty()) {
            System.out.println("No students added yet. Please add a student first.\n");
            return;
        }

        System.out.println("Select a student by number:");
        for (int i = 0; i < students.size(); i++) {
            System.out.println((i + 1) + ". " + students.get(i).name);
        }
        System.out.print("Enter student number: ");

        int index;
        try {
            index = Integer.parseInt(scanner.nextLine().trim()) - 1;
        } catch (NumberFormatException e) {
            System.out.println("Invalid input.\n");
            return;
        }

        if (index < 0 || index >= students.size()) {
            System.out.println("Invalid student number.\n");
            return;
        }

        Student student = students.get(index);
        System.out.print("How many grades do you want to add? ");

        int count;
        try {
            count = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input.\n");
            return;
        }

        for (int i = 0; i < count; i++) {
            System.out.print("Enter grade " + (i + 1) + " (0-100): ");
            try {
                double grade = Double.parseDouble(scanner.nextLine().trim());
                if (grade < 0 || grade > 100) {
                    System.out.println("Grade must be between 0 and 100. Skipping.");
                    continue;
                }
                student.grades.add(grade);
            } catch (NumberFormatException e) {
                System.out.println("Invalid grade. Skipping.");
            }
        }

        System.out.println("Grades updated for " + student.name + ".\n");
    }

    static void displaySummaryReport(ArrayList<Student> students) {
        if (students.isEmpty()) {
            System.out.println("No students to display.\n");
            return;
        }

        System.out.println("\n========== SUMMARY REPORT ==========");
        double classTotal = 0;
        int totalGradeCount = 0;
        double classHighest = Double.MIN_VALUE;
        double classLowest = Double.MAX_VALUE;
        String topStudent = "";
        String lowStudent = "";

        for (Student s : students) {
            System.out.println("\nStudent: " + s.name);
            if (s.grades.isEmpty()) {
                System.out.println("  No grades recorded.");
                continue;
            }
            System.out.println("  Grades: " + s.grades);
            System.out.printf("  Average: %.2f%n", s.getAverage());
            System.out.printf("  Highest: %.2f%n", s.getHighest());
            System.out.printf("  Lowest:  %.2f%n", s.getLowest());

            for (double g : s.grades) {
                classTotal += g;
                totalGradeCount++;
            }

            if (s.getHighest() > classHighest) {
                classHighest = s.getHighest();
                topStudent = s.name;
            }
            if (s.getLowest() < classLowest) {
                classLowest = s.getLowest();
                lowStudent = s.name;
            }
        }

        System.out.println("\n---------- CLASS OVERVIEW ----------");
        if (totalGradeCount > 0) {
            System.out.printf("Class Average: %.2f%n", classTotal / totalGradeCount);
            System.out.println("Highest Score: " + classHighest + " (" + topStudent + ")");
            System.out.println("Lowest Score:  " + classLowest + " (" + lowStudent + ")");
        } else {
            System.out.println("No grades recorded for any student yet.");
        }
        System.out.println("=====================================\n");
    }
}