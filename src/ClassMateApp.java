import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ClassMateApp {

    public static List<ClassCourse> classes = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            printMenu();
            String choice = scanner.nextLine();

            if (choice.equals("1")) {
                addClass(scanner);
            } else if (choice.equals("2")) {
                addAssignment(scanner);
            } else if (choice.equals("3")) {
                viewWeeklyPlanner();
            } else if (choice.equals("4")) {
                markAssignmentComplete(scanner);
            } else if (choice.equals("5")) {
                running = false;
            } else {
                System.out.println("Invalid choice. Try again.");
            }
        }

        scanner.close();
        System.out.println("Exiting ClassMate.");
    }

    public static void printMenu() {
        System.out.println("=== ClassMate Menu ===");
        System.out.println("1. Add Class");
        System.out.println("2. Add Assignment");
        System.out.println("3. View Weekly Planner");
        System.out.println("4. Mark Assignment Complete");
        System.out.println("5. Exit");
        System.out.print("Enter choice: ");
    }

    public static void addClass(Scanner scanner) {
        System.out.print("Class name: ");
        String name = scanner.nextLine();
        System.out.print("Meeting day (e.g., Monday): ");
        String day = scanner.nextLine();
        System.out.print("Meeting time (e.g., 10:00 AM): ");
        String time = scanner.nextLine();

        ClassCourse c = new ClassCourse(name, day, time);
        classes.add(c);
        System.out.println("Class added.\n");
    }

    public static void addAssignment(Scanner scanner) {
        if (classes.isEmpty()) {
            System.out.println("No classes yet. Please add a class first.\n");
            return;
        }

        System.out.println("Select a class:");
        for (int i = 0; i < classes.size(); i++) {
            System.out.println((i + 1) + ". " + classes.get(i).name);
        }
        String input = scanner.nextLine();
        int index;
        try {
            index = Integer.parseInt(input) - 1;
        } catch (NumberFormatException e) {
            System.out.println("Invalid input.\n");
            return;
        }

        if (index < 0 || index >= classes.size()) {
            System.out.println("Invalid class selection.\n");
            return;
        }

        ClassCourse selected = classes.get(index);

        System.out.print("Assignment title: ");
        String title = scanner.nextLine();
        System.out.print("Description (optional): ");
        String description = scanner.nextLine();
        System.out.print("Due date (e.g., 2025-12-10): ");
        String dueDate = scanner.nextLine();

        Assignment assignment = new Assignment(title, description, dueDate);
        selected.assignments.add(assignment);
        System.out.println("Assignment added.\n");
    }

    public static void viewWeeklyPlanner() {
        System.out.println("=== Weekly Planner ===");
        if (classes.isEmpty()) {
            System.out.println("No classes or assignments yet.\n");
            return;
        }

        for (ClassCourse course : classes) {
            System.out.println(course.name + " (" + course.meetingDay + " " + course.meetingTime + ")");
            if (course.assignments.isEmpty()) {
                System.out.println("  No assignments.");
            } else {
                for (Assignment a : course.assignments) {
                    String status = a.completed ? "[Done]" : "[Pending]";
                    System.out.println("  " + status + " " + a.title + " (Due: " + a.dueDate + ")");
                }
            }
            System.out.println();
        }
    }

    public static void markAssignmentComplete(Scanner scanner) {
        if (classes.isEmpty()) {
            System.out.println("No classes or assignments yet.\n");
            return;
        }

        System.out.println("Select a class:");
        for (int i = 0; i < classes.size(); i++) {
            System.out.println((i + 1) + ". " + classes.get(i).name);
        }
        String classInput = scanner.nextLine();
        int classIndex;
        try {
            classIndex = Integer.parseInt(classInput) - 1;
        } catch (NumberFormatException e) {
            System.out.println("Invalid input.\n");
            return;
        }

        if (classIndex < 0 || classIndex >= classes.size()) {
            System.out.println("Invalid class selection.\n");
            return;
        }

        ClassCourse course = classes.get(classIndex);
        if (course.assignments.isEmpty()) {
            System.out.println("No assignments for this class.\n");
            return;
        }

        System.out.println("Select an assignment to mark complete:");
        for (int i = 0; i < course.assignments.size(); i++) {
            Assignment a = course.assignments.get(i);
            System.out.println((i + 1) + ". " + a.title + " (Due: " + a.dueDate + ")");
        }
        String assignmentInput = scanner.nextLine();
        int assignmentIndex;
        try {
            assignmentIndex = Integer.parseInt(assignmentInput) - 1;
        } catch (NumberFormatException e) {
            System.out.println("Invalid input.\n");
            return;
        }

        if (assignmentIndex < 0 || assignmentIndex >= course.assignments.size()) {
            System.out.println("Invalid assignment selection.\n");
            return;
        }

        course.assignments.get(assignmentIndex).completed = true;
        System.out.println("Assignment marked as complete.\n");
    }
}
