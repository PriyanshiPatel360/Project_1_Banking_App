import java.util.Scanner;

class Calculator {

    int add(int a, int b) {
        return a + b;
    }

    double add(double a, double b) {
        return a + b;
    }

    int add(int a, int b, int c) {
        return a + b + c;
    }

    int sub(int a, int b) {
        return a - b;
    }

    double multiply(double a, double b) {
        return a * b;
    }

    double divide(int a, int b) {
        if(b == 0) {
            System.out.println("Error: Cannot divide by zero!");
            return 0;
        }
        return (double) a/b;
    }
}

class UserInterface{

    Scanner sc = new Scanner(System.in);
    Calculator calc = new Calculator();

    void performAddition() {
        System.out.println("\nChoose Addition type:");
        System.out.println("1. Two Integers");
        System.out.println("2. Two doubles");
        System.out.println("3. Three integers");
        System.out.println("Enter choice: ");
        int ch = sc.nextInt();

        if (ch == 1) {
            System.out.print("Enter first integer: ");
            int a = sc.nextInt();
            System.out.print("Enter second integer: ");
            int b = sc.nextInt();
            System.out.println("Result: " + calc.add(a,b));
        } else if (ch == 2) {
            System.out.print("Enter first double: ");
            double a = sc.nextDouble();
            System.out.print("Enter second double: ");
            double b = sc.nextDouble();
            System.out.println("Result: " + calc.add(a,b));
        } else if (ch == 3) {
            System.out.print("Enter first integer: ");
            int a = sc.nextInt();
            System.out.print("Enter second integer: ");
            int b = sc.nextInt();
            System.out.print("Enter third integre: ");
            int c = sc.nextInt();
            System.out.println("Result: " + calc.add(a, b, c));
        } else {
            System.out.println("Invalid choice!");
        }
    }

    void performSubtraction() {
        System.out.print("\nEnter first number: ");
        int a = sc.nextInt();
        System.out.print("Enter second number: ");
        int b = sc.nextInt();
        System.out.println("Result: " + calc.sub(a, b));
    }

    void performMultiplication() {
        System.out.print("\nEnter first number: ");
        int a = sc.nextInt();
        System.out.print("Enter second number: ");
        int b = sc.nextInt();
        System.out.println("Result: " + calc.multiply(a, b));
    }

    void performDivision() {
        System.out.print("\nEnter first number: ");
        int a = sc.nextInt();
        System.out.print("Enter second number: ");
        int b = sc.nextInt();
        double result = calc.divide(a, b );
        System.out.println("Result: " + result);
    }

    void mainMenu() {
        int choice;

        do{
            System.out.println("\n_________Calculator Menu _________");
            System.out.println("1. Add Numbers");
            System.out.println("2. Subtract Numbers");
            System.out.println("3. Multiply Numbers");
            System.out.println("4. Divide Numbers");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    performAddition();
                    break;
                case 2:
                    performSubtraction();
                    break;
                case 3:
                    performMultiplication();
                    break;
                case 4:
                    performDivision();
                    break;
                case 5:
                    System.out.println("Thank you for using Calculator!");
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        } while (choice != 5);
        sc.close();
    }
}

public class Calculator_Application{
    public static void main(String[] args) {
        UserInterface ui = new UserInterface();
        ui.mainMenu();
    }
}


