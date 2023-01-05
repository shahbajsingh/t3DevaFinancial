package driver.testruns;

import module.EmployeeImplement;

import java.sql.SQLException;
import java.util.Scanner;

public class EmployeeRun {

    public static void main(String[] args) throws SQLException {

        System.out.println("Welcome to Employee Portal");

        EmployeeImplement e = new EmployeeImplement();
        Scanner input = new Scanner(System.in).useDelimiter("\n");

        while (true) {

            System.out.println("\nPlease choose from the following options: ");
            System.out.println("[0] Exit Employee Portal");
            System.out.println("[1] Check Employee Info");
            System.out.println("[2] Check All Employees Info");
            System.out.println("[3] Modify Employee Info");
            System.out.println("[4] Add Employee");
            System.out.println("[5] Delete Employee");


            int userInput = input.nextInt();

            if (userInput == 0){
                System.out.println("\nThank you for using Employee Portal");
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
                System.out.println(e.getAllEmployeesInfoString());
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

        EmployeeImplement e = new EmployeeImplement();
        Scanner employee_details = new Scanner(System.in).useDelimiter("\n");

        System.out.println("\nPlease choose what you want to modify:");
        System.out.println("Enter [1] to modify Name");
        System.out.println("Enter [2] to modify Aadhar");
        System.out.println("Enter [3] to modify Address");
        System.out.println("Enter [4] to modify Phone Number");
        System.out.println("Enter [5] to modify Email Address");
        System.out.println("Enter [6] to modify Password");

        int choose_number = employee_details.nextInt();

        if (choose_number == 1) {
            System.out.println("Please enter new first name: ");
            String first_name = employee_details.next();

            System.out.println("Please enter new middle name:");
            String middle_name = employee_details.next();

            System.out.println("Please enter new last name: ");
            String last_name = employee_details.next();

            e.setEmployeeName(first_name, middle_name, last_name, employee_id);
        }

        if (choose_number == 2) {
            System.out.println("Please enter new Aadhar Number: ");
            long aadhaar = employee_details.nextLong();

            e.setEmployeeAadhaar(aadhaar, employee_id);
        }

        if (choose_number == 3) {
            System.out.println("Please enter house number: ");
            String house_no = employee_details.next();

            System.out.println("Please enter street name: ");
            String street = employee_details.next();

            System.out.println("Please enter city name: ");
            String city = employee_details.next();

            System.out.println("Please enter state name: ");
            String state = employee_details.next();

            System.out.println("Please enter country: ");
            String country = employee_details.next();

            System.out.println("Please enter zip code: ");
            String zip_code = employee_details.next();

            e.setEmployeeAddress(house_no, street, city, state, country, zip_code, employee_id);
        }

        if (choose_number == 4) {
            System.out.println("Please enter new phone number: ");
            String phone = Long.toString(employee_details.nextLong());

            e.setEmployeePhone(phone, employee_id);
        }

        if (choose_number == 5) {
            System.out.println("Please enter new email address: ");
            String email = employee_details.next();

            e.setEmployeeEmail(email, employee_id);
        }

        if (choose_number == 6) {
            System.out.println("Please enter new password: ");
            String password = employee_details.next();

            e.setEmployeePassword(password, employee_id);
        }

    }

    private static void handleEmployeeAdd() throws SQLException { // TO-DO: handle input mismatch exceptions

        EmployeeImplement e = new EmployeeImplement();
        Scanner employee_details = new Scanner(System.in).useDelimiter("\n");

        System.out.println("\nNAME DETAILS");

        System.out.println("First Name: ");
        String first_name = employee_details.next();

        System.out.println("Middle Name: ");
        String middle_name = employee_details.next();

        System.out.println("Last Name: ");
        String last_name = employee_details.next();

        System.out.println("\nIf applicable, please enter Aadhaar Number: ");
        long aadhaar = employee_details.nextLong();

        System.out.println("\nADDRESS DETAILS");

        System.out.println("House Number: ");
        String house_no = employee_details.next();

        System.out.println("Street Name: ");
        String street_name = employee_details.next();

        System.out.println("City: ");
        String city = employee_details.next();

        System.out.println("State: ");
        String state = employee_details.next();

        System.out.println("Country: ");
        String country = employee_details.next();

        System.out.println("Zip Code: ");
        String zip_code = employee_details.next();

        System.out.println("\nCONTACT DETAILS");

        System.out.println("Phone Number: ");
        String phone = employee_details.next();

        System.out.println("Email Address: ");
        String email = employee_details.next();

        System.out.println("\nPlease enter a password for the employee: ");
        String password = employee_details.next();

        e.addEmployee(password, first_name, middle_name, last_name, aadhaar, house_no,
                street_name, city, state, country, zip_code, phone, email);

    }

}
