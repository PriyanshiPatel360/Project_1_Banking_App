import java.util.Scanner;

// Custom Exception Class
class InvalidMarksException extends Exception {
    public InvalidMarksException(String msg) {
        super(msg);
    }
}

// Student Class
class Student {

    private int rollNumber;
    private String studentName;
    private int[] marks = new int[3];

    public Student(int roll, String name, int[] marksArr) {
        this.rollNumber = roll;
        this.studentName = name;
        this.marks = marksArr;
    }

    public void validateMarks() throws InvalidMarksException {
        for (int i = 0; i < marks.length; i++) {
            if (marks[i] < 0 || marks[i] > 100) {
                throw new InvalidMarksException("Invalid marks for subject " 
                        + (i + 1) + ": " + marks[i]);
            }
        }
    }

    public double calculateAverage() {
        int total = 0;
        for (int m : marks) {
            total += m;
        }
        return total / 3.0;
    }

    public void displayResult() {
        System.out.println("Roll Number: " + rollNumber);
        System.out.println("Student Name: " + studentName);

        System.out.print("Marks: ");
        for (int m : marks) {
            System.out.print(m + " ");
        }
        System.out.println();

        double avg = calculateAverage();
        System.out.println("Average: " + avg);

        if (avg >= 40)
            System.out.println("Result: Pass");
        else
            System.out.println("Result: Fail");
    }

    public int getRollNumber() {
        return rollNumber;
    }
}

// Main Class with User Interface
public class StudentResultSystem_Assignment_3 {

    private Student[] students = new Student[50];
    private int count = 0;
    private Scanner sc = new Scanner(System.in);

    public void addStudent() {

        try {
            System.out.print("Enter Roll Number: ");
            int roll = sc.nextInt();
            sc.nextLine();

            System.out.print("Enter Student Name: ");
            String name = sc.nextLine();

            int[] marksArr = new int[3];
            for (int i = 0; i < 3; i++) {
                System.out.print("Enter marks for subject " + (i + 1) + ": ");
                marksArr[i] = sc.nextInt();
            }

            Student s = new Student(roll, name, marksArr);

            s.validateMarks();   // custom exception may be thrown

            students[count++] = s;
            System.out.println("Student added successfully.");

        } catch (InvalidMarksException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Input error occurred.");
            sc.nextLine();
        } finally {
            System.out.println("Returning to main menu...");
        }
    }

    public void showStudentDetails() {

        System.out.print("Enter Roll Number to search: ");
        int roll = sc.nextInt();

        boolean found = false;

        for (int i = 0; i < count; i++) {
            if (students[i].getRollNumber() == roll) {
                students[i].displayResult();
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("No student found with that roll number.");
        }

        System.out.println("Search completed.");
    }

    public void mainMenu() {

        int ch = 0;

        while (ch != 3) {
            System.out.println("\n===== Student Result Management System =====");
            System.out.println("1. Add Student");
            System.out.println("2. Show Student Details");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            try {
                ch = sc.nextInt();

                switch (ch) {
                    case 1:
                        addStudent();
                        break;
                    case 2:
                        showStudentDetails();
                        break;
                    case 3:
                        System.out.println("Exiting program. Thank you!");
                        break;
                    default:
                        System.out.println("Invalid choice.");
                }

            } catch (Exception ex) {
                System.out.println("Invalid input. Please enter numbers only.");
                sc.nextLine();
            }
        }

        sc.close();
    }

    public static void main(String[] args) {
        StudentResultSystem_Assignment_3 rms = new StudentResultSystem_Assignment_3();
        rms.mainMenu();
    }
}
