package gui;

import module.CustomerImplement;
import module.EmployeeImplement;
import module.LoanImplement;
import module.PaymentImplement;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PortalSelect extends JFrame {

    public PortalSelect() {

        super("Portal Select");

        try {
            UIManager.setLookAndFeel(
                    UIManager.getCrossPlatformLookAndFeelClassName()
            );
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        handleLoanPortal();
        handlePaymentPortal();
        handleCustomerPortal();
        handleEmployeePortal();

        handleLogOut();

        this.setPreferredSize(new Dimension(800, 600));
        this.setContentPane(portalSelectPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);

    }

    private void handleLoanPortal(){

        LoanImplement tempLoan = new LoanImplement();

        // VIEW TAB

        viewLoansComboBox.addItem("LOAN ID");
        viewLoansComboBox.addItem("CARD NUMBER");

        btnViewLoansSubmit.addActionListener(e -> {
            String selected = (String) viewLoansComboBox.getSelectedItem();

            try {

                if (selected.equals("LOAN ID")) {

                    if (!viewLoansTextField1.getText().isEmpty()){

                    } else { // Display all

                    }


                } else if (selected.equals("CARD NUMBER")) {

                    if (!viewLoansTextField1.getText().isEmpty()){

                    } else { // Display all

                    }

                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }

        });

        // ADD TAB

        // MODIFY TAB

        // DELETE TAB

    }

    private void handlePaymentPortal(){

        PaymentImplement tempPayment = new PaymentImplement();

        // VIEW TAB

        btnViewPaymentsSubmit.addActionListener(e -> {

        });

        // ADD TAB

        // MODIFY TAB

        // DELETE TAB

    }

    private void handleCustomerPortal(){

        CustomerImplement tempCustomer = new CustomerImplement();

        // VIEW TAB

        btnViewCustomersSubmit.addActionListener(e -> {

        });

        // ADD TAB

        // MODIFY TAB

        // DELETE TAB

    }

    private void handleEmployeePortal(){

        EmployeeImplement tempEmployee = new EmployeeImplement();

        // VIEW TAB

        viewEmployeesComboBox.addItem("EMPLOYEE ID");
        viewEmployeesComboBox.addItem("LAST NAME");

        btnViewEmployeesSubmit.addActionListener(e -> {
            String selected = (String) viewEmployeesComboBox.getSelectedItem();

            try {

                if (selected.equals("EMPLOYEE ID")) {

                    if (!viewLoansTextField1.getText().isEmpty()){

                    } else { // Display all

                    }


                } else if (selected.equals("LAST NAME")) {

                    if (!viewLoansTextField1.getText().isEmpty()){

                    } else { // Display all

                    }

                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }

        });

        // ADD TAB

        // MODIFY TAB

        // DELETE TAB

    }

    private void handleLogOut(){

        btnLogOut.addActionListener(e -> {
            Login login = new Login();
            this.dispose();
        });

    }




    // COMPONENT DECLARATION

    private JPanel portalSelectPanel;
    private JButton btnLogOut;
    private JTabbedPane portalsTabbedPane;
    private JPanel customerPanel;
    private JPanel employeePanel;
    private JPanel loanPanel;
    private JPanel paymentPanel;
    private JTabbedPane customerTabbedPane;
    private JTabbedPane employeeTabbedPane;
    private JTabbedPane loanTabbedPane;
    private JTabbedPane paymentTabbedPane;
    private JTable viewCustomersTable;
    private JButton btnViewCustomersSubmit;
    private JTextField viewCustomersTextField;
    private JTable viewEmployeesTable;
    private JTextField viewEmployeesTextField;
    private JTable viewLoansTable;
    private JTextField viewLoansTextField1;
    private JFormattedTextField viewLoansTextField2;
    private JFormattedTextField viewLoansTextField3;
    private JRadioButton radioBtnAccountInfo;
    private JTable viewPaymentsTable;
    private JTextField viewPaymentsTextField2;
    private JTextField viewPaymentsTextField3;
    private JFormattedTextField viewPaymentsTextField4;
    private JFormattedTextField viewPaymentsTextField5;
    private JComboBox viewEmployeesComboBox;
    private JButton btnViewEmployeesSubmit;
    private JButton btnViewLoansSubmit;
    private JRadioButton radioBtnIsActive;
    private JComboBox viewLoansComboBox;
    private JButton btnViewPaymentsSubmit;
    private JTextField viewPaymentsTextField1;

}

