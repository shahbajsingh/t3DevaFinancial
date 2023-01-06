package driver.testruns;

import java.sql.SQLException;
import java.util.Scanner;

public class TestRun {

    public static void main(String[] args) throws SQLException {


        Scanner input = new Scanner(System.in);

        System.out.println("Welcome to Deva Financial Services (test) via Command Line Interface");

        while (true) {

            System.out.println("Please choose from the following options: ");
            System.out.println("[0] Exit Program");
            System.out.println("[1] Customer Portal");
            System.out.println("[2] Employee Portal");
            System.out.println("[3] Loan Portal");
            System.out.println("[4] Payment Portal");


            int userInput = input.nextInt();

            if(userInput == 0) {
                System.out.println("Thank you for using Deva Financial Services");
                break;
            }

            if(userInput == 1) {
                CustomerRun.main(null);
            }

            if(userInput == 2) {
                EmployeeRun.main(null);
            }

            if(userInput == 3) {
                LoanRun.main(null);
            }

            if(userInput == 4) {
                PaymentRun.main(null);
            }

        }

        input.close();
        System.exit(0);


    }

}
