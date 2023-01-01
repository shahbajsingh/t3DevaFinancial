package driver.testruns;

import module.EmployeeImplement;

import java.sql.SQLException;
import java.util.Scanner;

public class EmployeeRun {

    public static void main(String[] args) throws SQLException {

        System.out.println("Welcome to Customer Portal");

        EmployeeImplement e = new EmployeeImplement();
        Scanner input = new Scanner(System.in).useDelimiter("\n");

        while (true) {

            System.out.println("\nPlease choose from the following options: ");
            System.out.println("[0] Exit Employee Portal");
            System.out.println("[1] Check Employee Info");
            System.out.println("[2] Check All Employee Info");
            System.out.println("[3] Modify Employee Info");
            System.out.println("[4] Add Employee");
            System.out.println("[5] Delete Employee");


            int userInput = input.nextInt();

            if (userInput == 0){
                System.out.println("\nThank you for using the Employee Portal");
                TestRun.main(null);
                break;
            }

            if (userInput == 1){
                System.out.println("\nPlease enter employee ID to view: ");
                int employee_id = input.nextInt();

                if (!e.checkEmployeeExists(employee_id)){
                    System.out.println("\nThe employee ID provided does not exist in the database");
                } else {
                    System.out.println(e.getEmployeeInfoString(employee_id));
                }
            }

            if (userInput == 2){
                System.out.println(e.getAllEmployeeInfoString());
            }

            if (userInput == 3){
                System.out.println("Please enter employee ID to modify: ");
                int employee_id = input.nextInt();

                if (!e.checkEmployeeExists(employee_id)) {
                    System.out.println("\nThe employee ID provided does not exist in the database");
                } else {
                    handleEmployeeModify(employee_id);
                }
            }

            if (userInput == 4){
                handleEmployeeAdd();
            }

            if (userInput == 5){
                System.out.println("Please enter employee ID to delete: ");
                int employee_id = input.nextInt();

                if (!e.checkEmployeeExists(employee_id)) {
                    System.out.println("\nThe employee ID provided does not exist in the database");
                } else {
                    e.deleteEmployee(employee_id);
                }
            }

        }
    }

    private static void handleEmployeeModify(int employee_id) throws SQLException {

    }

    private static void handleEmployeeAdd() throws SQLException {

    }

}
