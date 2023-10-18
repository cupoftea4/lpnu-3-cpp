import java.io.IOException;
import java.util.*;
import Employee.*;

public class Menu {
    private final EmployeeCollectionsController controller;
    private List<Employee> employees;

    public Menu() {
        controller = new EmployeeCollectionsController();
        employees = new ArrayList<>();
    }

    public void displayMenu() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Employee Utility!");
        System.out.println("Do you want to use one or two input files?");
        System.out.println("1. One Input File");
        System.out.println("2. Two Input Files");
        System.out.print("Enter your choice: ");

        int fileChoice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        if (fileChoice == 1) {
            // Use one input file
            loadEmployeesFromSingleFile(scanner);
        } else if (fileChoice == 2) {
            // Use two input files
            loadEmployeesFromTwoFiles(scanner);
        } else {
            System.out.println("Invalid choice. Exiting the program.");
            scanner.close();
            return;
        }

        while (true) {
            System.out.println("Menu:");
            System.out.println("1. Print employees list");
            System.out.println("2. Print a map of employees by position");
            System.out.println("3. Find the most and least senior employees for each position");
            System.out.println("4. Print a map of employees by salary category");
            System.out.println("5. Delete employees by minimal birth year and print");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    EmployeeCollectionsController.printEmployeeList(employees);
                    break;
                case 2:
                    controller.printEmployeesByPositions(employees);
                    break;
                case 3:
                    controller.printYoungestAndOldestEmployeeByEveryPosition(employees);
                    break;
                case 4:
                    controller.printEmployeesBySalaryCategories(employees);
                    break;
                case 5:
                    EmployeeCollectionsController.printEmployeeList(
                            controller.deleteFromListByMinYear(employees, getYearFromUser(scanner)));
                    break;
                case 6:
                    System.out.println("Exiting the program.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
                    break;
            }
        }
    }

    private int getYearFromUser(Scanner scanner){
        System.out.print("Enter the minimal birth year for delete:");
        while (true){
            int year = scanner.nextInt();
            if(year<0){
                System.out.println("Invalid year, try again");
                scanner.nextLine();
            }
            else{
                return year;
            }
        }
    }
    private void loadEmployeesFromSingleFile(Scanner scanner) {
        System.out.print("Enter the file path for the input file: ");
        String filePath = scanner.nextLine();
        try{
            employees = FileReader.readEmployeeData(filePath);
        }catch (IOException e){
            System.out.println(e.getMessage());
        }

    }

    private void loadEmployeesFromTwoFiles(Scanner scanner) {
        System.out.print("Enter the file path for the first input file: ");
        String filePath1 = scanner.nextLine();
        System.out.print("Enter the file path for the second input file: ");
        String filePath2 = scanner.nextLine();
        try{
            employees = FileReader.readEmployeeData(filePath1);
            var tempEmployees = FileReader.readEmployeeData(filePath2);
            employees = EmployeeCollectionsController.mergeTwoLists(employees,tempEmployees);
        }catch (IOException e){
            System.out.println(e.getMessage());
        }

    }
}
