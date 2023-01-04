package driver.testruns;

import module.CustomerImplement;
import module.LoanImplement;

import java.sql.SQLException;
import java.util.Scanner;

public class LoanRun {

    public static void main(String[] args) throws SQLException {

        System.out.println("Welcome to Loan Portal");

        CustomerImplement c = new CustomerImplement();
        LoanImplement l = new LoanImplement();
        Scanner input = new Scanner(System.in).useDelimiter("\n");

        while (true){

            System.out.println("\nPlease choose from the following options: ");
            System.out.println("[0] Exit Loan Portal");
            System.out.println("[1] Check Loan Info By ID");
            System.out.println("[2] Check All Loans");
            System.out.println("[3] Check All Loans In Date Range");
            System.out.println("[4] Check All Active Loans");
            System.out.println("[5] Check All Active Loans In Date Range");
            System.out.println("[6] Check Loans Under Card Number");
            System.out.println("[7] Check Loans Under Card Number In Date Range");
            System.out.println("[8] Check Active Loans Under Card Number");
            System.out.println("[9] Check Active Loans Under Card Number In Date Range");
            System.out.println("[10] Add Loan");
            System.out.println("[11] Delete Loan");

            int userInput = input.nextInt();

            if (userInput == 0) {
                System.out.println("\nThank you for using the Customer Portal");
                TestRun.main(null);
                break;
            }

            if (userInput == 1) {
                System.out.println("\nPlease enter loan ID to view: ");
                long loan_id = input.nextLong();

                if (!l.checkLoanExists(loan_id)) {
                    System.out.println("\nThe loan ID provided does not exist in the database");
                } else {
                    System.out.println(l.getLoanInfoString(loan_id));
                }
            }

            if (userInput == 2) {
                System.out.println(l.getAllLoansString());
            }

            if (userInput == 3) {
                System.out.println("\nPlease enter start date (YYYY-MM-DD): ");
                String start_date = input.next();
                System.out.println("\nPlease enter end date (YYYY-MM-DD): ");
                String end_date = input.next();

                System.out.println(l.getAllLoansInDateRangeString(start_date, end_date));
            }

            if (userInput == 4) {
                System.out.println(l.getAllActiveLoansString());
            }

            if (userInput == 5) {
                System.out.println("\nPlease enter start date (YYYY-MM-DD): ");
                String start_date = input.next();
                System.out.println("\nPlease enter end date (YYYY-MM-DD): ");
                String end_date = input.next();

                System.out.println(l.getAllActiveLoansInDateRangeString(start_date, end_date));
            }

            if (userInput == 6) {
                System.out.println("\nPlease enter card number to view: ");
                long card_no = input.nextLong();

                if (!c.checkCustomerExists(card_no)) {
                    System.out.println("\nThe card number provided does not exist in the database");
                } else {
                    System.out.println(l.getLoansByCardNoString(card_no));
                }
            }

            if (userInput == 7) {
                System.out.println("\nPlease enter card number to view: ");
                long card_no = input.nextLong();
                System.out.println("\nPlease enter start date (YYYY-MM-DD): ");
                String start_date = input.next();
                System.out.println("\nPlease enter end date (YYYY-MM-DD): ");
                String end_date = input.next();

                if (!c.checkCustomerExists(card_no)) {
                    System.out.println("\nThe card number provided does not exist in the database");
                } else {
                    System.out.println(l.getLoansInDateRangeByCardNoString(card_no, start_date, end_date));
                }
            }

            if (userInput == 8) {
                System.out.println("\nPlease enter card number to view: ");
                long card_no = input.nextLong();

                if (!c.checkCustomerExists(card_no)) {
                    System.out.println("\nThe card number provided does not exist in the database");
                } else {
                    System.out.println(l.getActiveLoansByCardNoString(card_no));
                }
            }

            if (userInput == 9) {
                System.out.println("\nPlease enter card number to view: ");
                long card_no = input.nextLong();
                System.out.println("\nPlease enter start date (YYYY-MM-DD): ");
                String start_date = input.next();
                System.out.println("\nPlease enter end date (YYYY-MM-DD): ");
                String end_date = input.next();

                if (!c.checkCustomerExists(card_no)) {
                    System.out.println("\nThe card number provided does not exist in the database");
                } else {
                    System.out.println(l.getActiveLoansInDateRangeByCardNoString(card_no, start_date, end_date));
                }
            }

            if (userInput == 10) {
                handleLoanAdd();
            }

            if (userInput == 11) {
                System.out.println("\nPlease enter loan ID to delete: ");
                long loan_id = input.nextLong();

                if (!l.checkLoanExists(loan_id)) {
                    System.out.println("\nThe loan ID provided does not exist in the database");
                } else {
                    l.deleteLoan(loan_id);
                }
            }

        }

    }

    private static void handleLoanAdd() throws SQLException {

        LoanImplement l = new LoanImplement();
        CustomerImplement c = new CustomerImplement();

        Scanner loan_details = new Scanner(System.in).useDelimiter("\n");

        System.out.println("\nPlease enter the following details: ");

        System.out.println("\nCard Number: ");
        long card_no = loan_details.nextLong();

        System.out.println("\nLoan Amount: ");
        float loan_value = loan_details.nextFloat();

        System.out.println("\nInterest Rate (Integer): ");
        float interest_rate = loan_details.nextFloat();

        if (!c.checkCustomerExists(card_no)) {
            System.out.println("\nThe card number provided does not exist in the database");
        } else {
            l.addLoan(card_no, loan_value, interest_rate);
        }

    }

}
