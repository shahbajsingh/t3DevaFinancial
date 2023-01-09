package gui;

import module.*;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.InputMismatchException;

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
        CardImplement tempCard = new CardImplement();

        // VIEW TAB

        viewLoansComboBox.addItem("LOAN ID");
        viewLoansComboBox.addItem("CARD NUMBER");

        btnViewLoansSubmit.addActionListener(e -> {

            String boxSelected = (String) viewLoansComboBox.getSelectedItem();

            try {

                if (boxSelected.equals("LOAN ID")) {

                    if (!viewLoansTextField.getText().isEmpty()) { // If loan ID entered
                        long loan_id = Long.parseLong(viewLoansTextField.getText());

                        if (tempLoan.checkLoanExists(loan_id)) { // If loan exists
                            viewLoansTable.setModel(tempLoan.getLoanInfoTableModel(loan_id));
                        } else {
                            JOptionPane.showMessageDialog(null, "INVALID LOAN ID");
                        }
                    } else { // If loan ID not entered
                        viewSortedLoans(tempLoan);
                    }

                } else if (boxSelected.equals("CARD NUMBER")) {

                    if (!viewLoansTextField.getText().isEmpty()) { // If card number entered
                        long card_no = Long.parseLong(viewLoansTextField.getText());

                        if (tempCard.checkCardExists(card_no)) { // If card exists
                            if (radioBtnIsActive.isSelected()) { // If active loans selected
                                if (!viewLoansDateField1.getText().isEmpty()
                                        && !viewLoansDateField2.getText().isEmpty()) {
                                    viewLoansTable.setModel(tempLoan.getActiveLoansInDateRangeByCardNoTableModel(
                                            card_no, viewLoansDateField1.getText(), viewLoansDateField2.getText()));
                                } else if (!viewLoansDateField1.getText().isEmpty()) {
                                    viewLoansTable.setModel(tempLoan.getActiveLoansAfterDateByCardNoTableModel(
                                            card_no, viewLoansDateField1.getText()));
                                } else if (!viewLoansDateField2.getText().isEmpty()) {
                                    viewLoansTable.setModel(tempLoan.getActiveLoansBeforeDateByCardNoTableModel(
                                            card_no, viewLoansDateField2.getText()));
                                } else {
                                    viewLoansTable.setModel(tempLoan.getActiveLoansByCardNoTableModel(card_no));
                                }
                            } else { // If all loans selected
                                if (!viewLoansDateField1.getText().isEmpty()
                                        && !viewLoansDateField2.getText().isEmpty()) {
                                    viewLoansTable.setModel(tempLoan.getLoansInDateRangeByCardNoTableModel(
                                            card_no, viewLoansDateField1.getText(), viewLoansDateField2.getText()));
                                } else if (!viewLoansDateField1.getText().isEmpty()) {
                                    viewLoansTable.setModel(tempLoan.getLoansAfterDateByCardNoTableModel(
                                            card_no, viewLoansDateField1.getText()));
                                } else if (!viewLoansDateField2.getText().isEmpty()) {
                                    viewLoansTable.setModel(tempLoan.getLoansBeforeDateByCardNoTableModel(
                                            card_no, viewLoansDateField2.getText()));
                                } else {
                                    viewLoansTable.setModel(tempLoan.getLoansByCardNoTableModel(card_no));
                                }
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "INVALID CARD NUMBER");
                        }
                    } else { // If card number not entered
                        viewSortedLoans(tempLoan);
                    }

                }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "INVALID INPUT");
                ex.printStackTrace();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "SQL ERROR");
                ex.printStackTrace();
            }

        });

        // ADD TAB

        // MODIFY TAB

        // DELETE TAB

    }

    private void handlePaymentPortal(){

        PaymentImplement tempPayment = new PaymentImplement();
        LoanImplement tempLoan = new LoanImplement();
        CardImplement tempCard = new CardImplement();

        // VIEW TAB

        viewPaymentsComboBox.addItem("PAYMENT ID");
        viewPaymentsComboBox.addItem("CARD NUMBER");
        viewPaymentsComboBox.addItem("LOAN ID");

        btnViewPaymentsSubmit.addActionListener(e -> {

            String boxSelected = (String) viewPaymentsComboBox.getSelectedItem();

            try {

                if (boxSelected.equals("PAYMENT ID")) {

                    if (!viewPaymentsTextField.getText().isEmpty()) { // If payment ID entered
                        long payment_id = Long.parseLong(viewPaymentsTextField.getText());

                        if (tempPayment.checkPaymentExists(payment_id)) { // If payment exists
                            viewPaymentsTable.setModel(tempPayment.getPaymentInfoTableModel(payment_id));
                        } else {
                            JOptionPane.showMessageDialog(null, "INVALID PAYMENT ID");
                        }
                    } else { // If payment ID not entered
                        viewSortedPayments(tempPayment);
                    }

                } else if (boxSelected.equals("CARD NUMBER")) {

                    if (!viewPaymentsTextField.getText().isEmpty()) { // If card number entered
                        long card_no = Long.parseLong(viewPaymentsTextField.getText());

                        if (tempCard.checkCardExists(card_no)) { // If card exists
                            if (!viewPaymentsDateField1.getText().isEmpty()
                                    && !viewPaymentsDateField2.getText().isEmpty()) {
                                viewPaymentsTable.setModel(tempPayment.getPaymentsInDateRangeByCardNoTableModel(
                                        card_no, viewPaymentsDateField1.getText(), viewPaymentsDateField2.getText()));
                            } else if (!viewPaymentsDateField1.getText().isEmpty()) {
                                viewPaymentsTable.setModel(tempPayment.getPaymentsAfterDateByCardNoTableModel(
                                        card_no, viewPaymentsDateField1.getText()));
                            } else if (!viewPaymentsDateField2.getText().isEmpty()) {
                                viewPaymentsTable.setModel(tempPayment.getPaymentsBeforeDateByCardNoTableModel(
                                        card_no, viewPaymentsDateField2.getText()));
                            } else {
                                viewPaymentsTable.setModel(tempPayment.getPaymentsByCardNoTableModel(card_no));
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "INVALID CARD NUMBER");
                        }
                    } else { // If card number not entered
                        viewSortedPayments(tempPayment);
                    }

                } else if (boxSelected.equals("LOAN ID")) {

                    if (!viewPaymentsTextField.getText().isEmpty()) { // If loan ID entered
                        long loan_id = Long.parseLong(viewPaymentsTextField.getText());

                        if (tempLoan.checkLoanExists(loan_id)) { // If loan exists
                            if (!viewPaymentsDateField1.getText().isEmpty()
                                    && !viewPaymentsDateField2.getText().isEmpty()) {
                                viewPaymentsTable.setModel(tempPayment.getPaymentsInDateRangeByLoanIDTableModel(
                                        loan_id, viewPaymentsDateField1.getText(), viewPaymentsDateField2.getText()));
                            } else if (!viewPaymentsDateField1.getText().isEmpty()) {
                                viewPaymentsTable.setModel(tempPayment.getPaymentsAfterDateByLoanIDTableModel(
                                        loan_id, viewPaymentsDateField1.getText()));
                            } else if (!viewPaymentsDateField2.getText().isEmpty()) {
                                viewPaymentsTable.setModel(tempPayment.getPaymentsBeforeDateByLoanIDTableModel(
                                        loan_id, viewPaymentsDateField2.getText()));
                            } else {
                                viewPaymentsTable.setModel(tempPayment.getPaymentsByLoanIDTableModel(loan_id));
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "INVALID LOAN ID");
                        }
                    } else { // If loan ID not entered
                        viewSortedPayments(tempPayment);
                    }

                }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "INVALID INPUT");
                ex.printStackTrace();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "SQL ERROR");
                ex.printStackTrace();
            } catch (InputMismatchException ex) {
                JOptionPane.showMessageDialog(null, "INVALID INPUT");
                ex.printStackTrace();
            }

        });

        // ADD TAB

        // MODIFY TAB

        // DELETE TAB

    }

    private void handleCustomerPortal(){

        CustomerImplement tempCustomer = new CustomerImplement();
        CardImplement tempCard = new CardImplement();

        // VIEW TAB

        viewCustomersComboBox.addItem("CARD NUMBER");
        viewCustomersComboBox.addItem("AADHAAR NUMBER");

        btnViewCustomersSubmit.addActionListener(e -> {

            String boxSelected = (String) viewCustomersComboBox.getSelectedItem();

            try {

                if (boxSelected.equals("CARD NUMBER")) {

                    if (!viewCustomersTextField.getText().isEmpty()) { // If card number entered
                        long card_no = Long.parseLong(viewCustomersTextField.getText());

                        if (tempCard.checkCardExists(card_no)) { // If card exists
                            if (radioBtnAccountInfo.isSelected()) {
                                viewCustomersTable.setModel(tempCustomer.getCustomerAccountInfoTableModel(card_no));
                            } else {
                                viewCustomersTable.setModel(tempCustomer.getCustomerInfoTableModel(card_no));
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "INVALID CARD NUMBER");
                        }
                    } else { // If card number not entered
                        if (radioBtnAccountInfo.isSelected()) {
                            viewCustomersTable.setModel(tempCustomer.getAllCustomersAccountInfoTableModel());
                        } else {
                            viewCustomersTable.setModel(tempCustomer.getAllCustomersInfoTableModel());
                        }
                    }

                } else if (boxSelected.equals("AADHAAR NUMBER")) {

                    if (!viewCustomersTextField.getText().isEmpty()) { // If aadhaar number entered
                     long aadhaar = Long.parseLong(viewCustomersTextField.getText());

                     if (tempCustomer.checkCustomerExistsByAadhaar(aadhaar)) { // If aadhaar number exists
                         if (radioBtnAccountInfo.isSelected()) {
                             viewCustomersTable.setModel(
                                     tempCustomer.getCustomerAccountInfoByAadhaarTableModel(aadhaar));
                         } else {
                             viewCustomersTable.setModel(tempCustomer.getCustomerInfoByAadhaarTableModel(aadhaar));
                         }
                     } else {
                         JOptionPane.showMessageDialog(null, "INVALID AADHAAR NUMBER");
                        }
                    } else { // If aadhaar number not entered
                        if (radioBtnAccountInfo.isSelected()) {
                            viewCustomersTable.setModel(tempCustomer.getAllCustomersAccountInfoTableModel());
                        } else {
                            viewCustomersTable.setModel(tempCustomer.getAllCustomersInfoTableModel());
                        }
                    }

                }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "INVALID INPUT");
                ex.printStackTrace();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "SQL ERROR");
                ex.printStackTrace();
            } catch (InputMismatchException ex) {
                JOptionPane.showMessageDialog(null, "INVALID INPUT");
                ex.printStackTrace();
            }


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

            String boxSelected = (String) viewEmployeesComboBox.getSelectedItem();

            try {

                if (boxSelected.equals("EMPLOYEE ID")) {

                    if (!viewEmployeesTextField.getText().isEmpty()) {
                        int employee_id = Integer.parseInt(viewEmployeesTextField.getText());

                        if (tempEmployee.checkEmployeeExists(employee_id)) {
                            viewEmployeesTable.setModel(tempEmployee.getEmployeeInfoTableModel(employee_id));
                        } else {
                            JOptionPane.showMessageDialog(null, "INVALID EMPLOYEE ID");
                        }
                    } else {
                        viewEmployeesTable.setModel(tempEmployee.getAllEmployeesInfoTableModel());
                    }

                } else if (boxSelected.equals("LAST NAME")) {

                    if (!viewEmployeesTextField.getText().isEmpty()) {
                        String last_name = viewEmployeesTextField.getText();

                        if (tempEmployee.checkEmployeeExistsByLastName(last_name)) {
                            viewEmployeesTable.setModel(tempEmployee.getEmployeeInfoByLastNameTableModel(last_name));
                        } else {
                            JOptionPane.showMessageDialog(null, "INVALID LAST NAME");
                        }
                    } else {
                        viewEmployeesTable.setModel(tempEmployee.getAllEmployeesInfoTableModel());
                    }

                }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "INVALID INPUT");
                ex.printStackTrace();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "SQL ERROR");
                ex.printStackTrace();
            } catch (InputMismatchException ex) {
                JOptionPane.showMessageDialog(null, "INVALID INPUT");
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




    // HELPER METHODS

    private void viewSortedLoans(LoanImplement tempLoan) throws SQLException {
        if (radioBtnIsActive.isSelected()) { // If active loans selected
            if (!viewLoansDateField1.getText().isEmpty() && !viewLoansDateField2.getText().isEmpty()) {
                viewLoansTable.setModel(tempLoan.getAllActiveLoansInDateRangeTableModel(
                        viewLoansDateField1.getText(), viewLoansDateField2.getText()));
            } else if (!viewLoansDateField1.getText().isEmpty()) {
                viewLoansTable.setModel(tempLoan.getAllActiveLoansAfterDateTableModel(
                        viewLoansDateField1.getText()));
            } else if (!viewLoansDateField2.getText().isEmpty()) {
                viewLoansTable.setModel(tempLoan.getAllActiveLoansBeforeDateTableModel(
                        viewLoansDateField2.getText()));
            } else {
                viewLoansTable.setModel(tempLoan.getAllActiveLoansTableModel());
            }
        } else { // If all loans selected
            if (!viewLoansDateField1.getText().isEmpty() && !viewLoansDateField2.getText().isEmpty()) {
                viewLoansTable.setModel(tempLoan.getAllLoansInDateRangeTableModel(
                        viewLoansDateField1.getText(), viewLoansDateField2.getText()));
            } else if (!viewLoansDateField1.getText().isEmpty()) {
                viewLoansTable.setModel(tempLoan.getAllLoansAfterDateTableModel(
                        viewLoansDateField1.getText()));
            } else if (!viewLoansDateField2.getText().isEmpty()) {
                viewLoansTable.setModel(tempLoan.getAllLoansBeforeDateTableModel(
                        viewLoansDateField2.getText()));
            } else {
                viewLoansTable.setModel(tempLoan.getAllLoansTableModel());
            }
        }
    }

    private void viewSortedPayments(PaymentImplement tempPayment) throws SQLException {
        if (!viewPaymentsDateField1.getText().isEmpty()
                && !viewPaymentsDateField2.getText().isEmpty()) {
            viewPaymentsTable.setModel(tempPayment.getAllPaymentsInDateRangeTableModel(
                    viewPaymentsDateField1.getText(), viewPaymentsDateField2.getText()));
        } else if (!viewPaymentsDateField1.getText().isEmpty()) {
            viewPaymentsTable.setModel(tempPayment.getAllPaymentsAfterDateTableModel(
                    viewPaymentsDateField1.getText()));
        } else if (!viewPaymentsDateField2.getText().isEmpty()) {
            viewPaymentsTable.setModel(tempPayment.getAllPaymentsBeforeDateTableModel(
                    viewPaymentsDateField2.getText()));
        } else {
            viewPaymentsTable.setModel(tempPayment.getAllPaymentsTableModel());
        }
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
    private JButton btnViewCustomersSubmit;
    private JTextField viewCustomersTextField;
    private JTable viewLoansTable;
    private JTextField viewLoansTextField;
    private JRadioButton radioBtnAccountInfo;
    private JComboBox viewEmployeesComboBox;
    private JButton btnViewEmployeesSubmit;
    private JButton btnViewLoansSubmit;
    private JComboBox viewLoansComboBox;
    private JFormattedTextField viewLoansDateField1;
    private JFormattedTextField viewLoansDateField2;
    private JRadioButton radioBtnIsActive;
    private JTable viewCustomersTable;
    private JTable viewEmployeesTable;
    private JButton btnViewPaymentsSubmit;
    private JTextField viewEmployeesTextField;
    private JComboBox viewCustomersComboBox;
    private JTable viewPaymentsTable;
    private JFormattedTextField viewPaymentsDateField1;
    private JFormattedTextField viewPaymentsDateField2;
    private JComboBox viewPaymentsComboBox;
    private JTextField viewPaymentsTextField;

}

