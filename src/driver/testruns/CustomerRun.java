package driver.testruns;

import module.CustomerImplement;

import java.sql.SQLException;
import java.util.Scanner;

public class CustomerRun {

    public static void main(String[] args) throws SQLException {

        System.out.println("Welcome to Customer Portal");

        CustomerImplement c = new CustomerImplement();
        Scanner input = new Scanner(System.in).useDelimiter("\n");

        while (true) {

            System.out.println("\nPlease choose from the following options: ");
            System.out.println("[0] Exit Customer Portal");
            System.out.println("[1] Check Customer Info");
            System.out.println("[2] Check Customer Account Info");
            System.out.println("[3] Check All Customer Info");
            System.out.println("[4] check All Customer Account Info");
            System.out.println("[5] Modify Customer Info");
            System.out.println("[6] Add New Customer");
            System.out.println("[7] Delete Customer");

            int userInput = input.nextInt();

            if (userInput == 0) {
                System.out.println("\nThank you for using the Customer Portal");
                TestRun.main(null);
                break;
            }

            if (userInput == 1) {
                System.out.println("\nPlease enter card number to view: ");
                long card_no = input.nextLong();

                if (!c.checkCustomerExists(card_no)) {
                    System.out.println("\nThe card number provided does not exist in the database");
                } else {
                    System.out.println(c.getCustomerInfoString(card_no));
                }
            }

            if (userInput == 2) {
                System.out.println("\nPlease enter card number to view: ");
                long card_no = input.nextLong();

                if (!c.checkCustomerExists(card_no)) {
                    System.out.println("\nThe card number provided does not exist in the database");
                } else {
                    System.out.println(c.getCustomerAccountInfoString(card_no));
                }
            }

            if (userInput == 3) {
                System.out.println(c.getAllCustomerInfoString());
            }

            if (userInput == 4) {
                System.out.println(c.getAllCustomerAccountInfoString());
            }

            if (userInput == 5){
                System.out.println("\nPlease enter card number for the customer you wish to modify: ");
                long card_no = input.nextLong();

                if (!c.checkCustomerExists(card_no)) {
                    System.out.println("\nThe card number provided does not exist in the database");
                } else {
                    handleCustomerModify(card_no);
                }
            }

            if (userInput == 6){
                handleCustomerAdd();
            }

            if (userInput == 7){
                System.out.println("\nPlease enter card number for the customer you wish to delete: ");
                long card_no = input.nextLong();

                if (!c.checkCustomerExists(card_no)) {
                    System.out.println("\nThe card number provided does not exist in the database");
                } else {
                    c.deleteCustomer(card_no);
                }
            }

        }

    }

    private static void handleCustomerModify(long card_no) throws SQLException {

        CustomerImplement c = new CustomerImplement();
        Scanner customer_details = new Scanner(System.in).useDelimiter("\n");

        System.out.println("\nPlease choose what you want to modify:");
        System.out.println("Enter [1] to modify Name");
        System.out.println("Enter [2] to modify Aadhar");
        System.out.println("Enter [3] to modify Address");
        System.out.println("Enter [4] to modify Phone Number");
        System.out.println("Enter [5] to modify Email Address");

        int choose_number = customer_details.nextInt();

        if (choose_number == 1) {
            System.out.println("Please enter new first name: ");
            String first_name = customer_details.next();

            System.out.println("Please enter new middle name:");
            String middle_name = customer_details.next();

            System.out.println("Please enter new last name: ");
            String last_name = customer_details.next();

            c.changeCustomerName(first_name, middle_name, last_name, card_no);
        }

        if (choose_number == 2) {
            System.out.println("Please enter new Aadhar Number: ");
            long aadhaar = customer_details.nextLong();

            c.changeCustomerAadhaar(aadhaar, card_no);
        }

        if (choose_number == 3) {
            System.out.println("Please enter house number: ");
            String house_no = customer_details.next();

            System.out.println("Please enter street name: ");
            String street = customer_details.next();

            System.out.println("Please enter city name: ");
            String city = customer_details.next();

            System.out.println("Please enter state name: ");
            String state = customer_details.next();

            System.out.println("Please enter country: ");
            String country = customer_details.next();

            System.out.println("Please enter zip code: ");
            String zip_code = customer_details.next();

            c.changeCustomerAddress(house_no, street, city, state, country, zip_code, card_no);
        }

        if (choose_number == 4) {
            System.out.println("Please enter new phone number: ");
            String phone = Long.toString(customer_details.nextLong());

            c.changeCustomerPhone(phone, card_no);
        }

        if (choose_number == 5) {
            System.out.println("Please enter new email address: ");
            String email = customer_details.next();

            c.changeCustomerEmail(email, card_no);
        }

    }

    private static void handleCustomerAdd() throws SQLException { // TO-DO: handle input mismatch exceptions

        CustomerImplement c = new CustomerImplement();
        Scanner customer_details = new Scanner(System.in).useDelimiter("\n");

        System.out.println("\nNAME DETAILS");

        System.out.println("First Name: ");
        String first_name = customer_details.next();

        System.out.println("Middle Name: ");
        String middle_name = customer_details.next();

        System.out.println("Last Name: ");
        String last_name = customer_details.next();

        System.out.println("\nIf applicable, please enter Aadhaar Number: ");
        long aadhaar = customer_details.nextLong();

        System.out.println("\nADDRESS DETAILS");

        System.out.println("House Number: ");
        String house_no = customer_details.next();

        System.out.println("Street Name: ");
        String street_name = customer_details.next();

        System.out.println("City: ");
        String city = customer_details.next();

        System.out.println("State: ");
        String state = customer_details.next();

        System.out.println("Country: ");
        String country = customer_details.next();

        System.out.println("Zip Code: ");
        String zip_code = customer_details.next();

        System.out.println("\nCONTACT DETAILS");

        System.out.println("Phone Number: ");
        String phone = customer_details.next();

        System.out.println("Email Address: ");
        String email = customer_details.next();

        c.addCustomer(first_name, middle_name, last_name, aadhaar, house_no, street_name,
                city, state, country, zip_code, phone, email);

    }

}
