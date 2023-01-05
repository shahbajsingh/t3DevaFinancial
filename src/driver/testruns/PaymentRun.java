package driver.testruns;

import module.CustomerImplement;
import module.LoanImplement;
import module.PaymentImplement;

import java.sql.SQLException;
import java.util.Scanner;

public class PaymentRun {

    public static void main(String[] args) throws SQLException {

        System.out.println("Welcome to Payment Portal");

        CustomerImplement c = new CustomerImplement();
        LoanImplement l = new LoanImplement();
        PaymentImplement p = new PaymentImplement();

        Scanner input = new Scanner(System.in).useDelimiter("\n");

        while (true) {

            System.out.println("\nPlease choose from the following options: ");
            System.out.println("[0] Exit Payment Portal");
            System.out.println("[1] Check Payment Info By ID");
            System.out.println("[2] Check All Payments");
            System.out.println("[3] Check All Payments In Date Range");
            System.out.println("[4] Check Payments Under Card Number");
            System.out.println("[5] Check Payments Under Card Number In Date Range");
            System.out.println("[6] Check Payments On Loan ID");
            System.out.println("[7] Check Payments On Loan ID In Date Range");
            System.out.println("[8] Add Payment");
            System.out.println("[9] Delete Payment");

            int userInput = input.nextInt();

            if (userInput == 0) {
                System.out.println("\nThank you for using Payment Portal");
                TestRun.main(null);
                break;
            }

            if (userInput == 1) {
                System.out.println("\nPlease enter payment ID to view: ");
                long payment_id = input.nextLong();

                if (!p.checkPaymentExists(payment_id)) {
                    System.out.println("\nThe payment ID provided does not exist in the database");
                } else {
                    System.out.println(p.getPaymentInfoString(payment_id));
                }
            }

            if (userInput == 2) {
                System.out.println(p.getAllPaymentsString());
            }

            if (userInput == 3) {
                System.out.println("\nPlease enter start date (YYYY-MM-DD): ");
                String start_date = input.next();
                System.out.println("\nPlease enter end date (YYYY-MM-DD): ");
                String end_date = input.next();

                System.out.println(p.getAllPaymentsInDateRangeString(start_date, end_date));
            }

            if (userInput == 4) {
                System.out.println("\nPlease enter card number to view: ");
                long card_number = input.nextLong();

                if (!c.checkCustomerExists(card_number)) {
                    System.out.println("\nThe card number provided does not exist in the database");
                } else {
                    System.out.println(p.getPaymentsByCardNoString(card_number));
                }
            }

            if (userInput == 5) {
                System.out.println("\nPlease enter card number to view: ");
                long card_number = input.nextLong();
                System.out.println("\nPlease enter start date (YYYY-MM-DD): ");
                String start_date = input.next();
                System.out.println("\nPlease enter end date (YYYY-MM-DD): ");
                String end_date = input.next();

                if (!c.checkCustomerExists(card_number)) {
                    System.out.println("\nThe card number provided does not exist in the database");
                } else {
                    System.out.println(p.getPaymentsInDateRangeByCardNoString(card_number, start_date, end_date));
                }
            }

            if (userInput == 6) {
                System.out.println("\nPlease enter loan ID to view: ");
                long loan_id = input.nextLong();

                if (!l.checkLoanExists(loan_id)) {
                    System.out.println("\nThe loan ID provided does not exist in the database");
                } else {
                    System.out.println(p.getPaymentsByLoanIDString(loan_id));
                }
            }

            if (userInput == 7) {
                System.out.println("\nPlease enter loan ID to view: ");
                long loan_id = input.nextLong();
                System.out.println("\nPlease enter start date (YYYY-MM-DD): ");
                String start_date = input.next();
                System.out.println("\nPlease enter end date (YYYY-MM-DD): ");
                String end_date = input.next();

                if (!l.checkLoanExists(loan_id)) {
                    System.out.println("\nThe loan ID provided does not exist in the database");
                } else {
                    System.out.println(p.getPaymentsInDateRangeByLoanIDString(loan_id, start_date, end_date));
                }
            }

            if (userInput == 8) {
                handlePaymentAdd();
            }

            if (userInput == 9) {
                System.out.println("\nPlease enter payment ID to delete: ");
                long payment_id = input.nextLong();

                if (!p.checkPaymentExists(payment_id)) {
                    System.out.println("\nThe payment ID provided does not exist in the database");
                } else {
                    p.deletePayment(payment_id);
                }
            }

        }

    }

    private static void handlePaymentAdd() throws SQLException{

        PaymentImplement p = new PaymentImplement();
        CustomerImplement c = new CustomerImplement();

        Scanner payment_details = new Scanner(System.in).useDelimiter("\n");

        System.out.println("Please enter the following details: ");

        System.out.println("Card Number: ");
        long card_no = payment_details.nextLong();

        System.out.println("\nPayment Amount: ");
        float payment_amount = payment_details.nextFloat();

        if (c.checkCustomerExists(card_no)) {
            p.addPayment(card_no, payment_amount);
        } else {
            System.out.println("\nThe card number provided does not exist in the database");
        }

    }

}
