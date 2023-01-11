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

        this.setPreferredSize(new Dimension(850, 600));
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

            handleLoanView(tempLoan, tempCard);

        });

        // ADD TAB

        btnAddLoanSubmit.addActionListener(e -> {

            handleLoanAdd(tempLoan, tempCard);

        });

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

                handlePaymentView(tempPayment, tempLoan, tempCard);

        });

        // ADD TAB

        btnAddPaymentSubmit.addActionListener(e -> {

            handlePaymentAdd(tempPayment, tempCard);

        });

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

            handleCustomerView(tempCustomer, tempCard);

        });

        // ADD TAB

        btnAddCustomerSubmit.addActionListener(e -> {

            handleCustomerAdd(tempCustomer);

        });

        // MODIFY TAB

        // DELETE TAB

    }

    private void handleEmployeePortal(){

        EmployeeImplement tempEmployee = new EmployeeImplement();

        // VIEW TAB

        viewEmployeesComboBox.addItem("EMPLOYEE ID");
        viewEmployeesComboBox.addItem("LAST NAME");
        viewEmployeesComboBox.addItem("AADHAAR NUMBER");

        btnViewEmployeesSubmit.addActionListener(e -> {

            handleEmployeeView(tempEmployee);

        });

        // ADD TAB

        btnAddEmployeeSubmit.addActionListener(e -> {

            handleEmployeeAdd(tempEmployee);

        });

        // MODIFY TAB

        // DELETE TAB

    }

    private void handleLogOut(){

        btnLogOut.addActionListener(e -> {
            Login login = new Login();
            this.dispose();
        });

    }




    // LOAN PORTAL METHODS

    private void handleLoanView(LoanImplement tempLoan, CardImplement tempCard) {

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
                            if (!viewLoansStartDateField.getText().isEmpty()
                                    && !viewLoansEndDateField.getText().isEmpty()) {
                                viewLoansTable.setModel(tempLoan.getActiveLoansInDateRangeByCardNoTableModel(
                                        card_no, viewLoansStartDateField.getText(), viewLoansEndDateField.getText()));
                            } else if (!viewLoansStartDateField.getText().isEmpty()) {
                                viewLoansTable.setModel(tempLoan.getActiveLoansAfterDateByCardNoTableModel(
                                        card_no, viewLoansStartDateField.getText()));
                            } else if (!viewLoansEndDateField.getText().isEmpty()) {
                                viewLoansTable.setModel(tempLoan.getActiveLoansBeforeDateByCardNoTableModel(
                                        card_no, viewLoansEndDateField.getText()));
                            } else {
                                viewLoansTable.setModel(tempLoan.getActiveLoansByCardNoTableModel(card_no));
                            }
                        } else { // If all loans selected
                            if (!viewLoansStartDateField.getText().isEmpty()
                                    && !viewLoansEndDateField.getText().isEmpty()) {
                                viewLoansTable.setModel(tempLoan.getLoansInDateRangeByCardNoTableModel(
                                        card_no, viewLoansStartDateField.getText(), viewLoansEndDateField.getText()));
                            } else if (!viewLoansStartDateField.getText().isEmpty()) {
                                viewLoansTable.setModel(tempLoan.getLoansAfterDateByCardNoTableModel(
                                        card_no, viewLoansStartDateField.getText()));
                            } else if (!viewLoansEndDateField.getText().isEmpty()) {
                                viewLoansTable.setModel(tempLoan.getLoansBeforeDateByCardNoTableModel(
                                        card_no, viewLoansEndDateField.getText()));
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

    }

    private void handleLoanAdd(LoanImplement tempLoan, CardImplement tempCard) {

        try {

            if (!addLoanCardNoTextField.getText().isEmpty() && !addLoanPrincipleTextField.getText().isEmpty()
                    && !addLoanInterestRateTextField.getText().isEmpty()) {

                long card_no = Long.parseLong(addLoanCardNoTextField.getText());
                float loan_value = Float.parseFloat(addLoanPrincipleTextField.getText());
                float interest_rate = Float.parseFloat(addLoanInterestRateTextField.getText());

                if (tempCard.checkCardExists(card_no)) {
                    tempLoan.addLoan(card_no, loan_value, interest_rate);
                    JOptionPane.showMessageDialog(null,
                            String.format("LOAN OF ₹%.2f ADDED TO CARD NO. %d AT %.2f%% INTEREST RATE",
                                    loan_value, card_no, interest_rate)
                            );
                } else {
                    JOptionPane.showMessageDialog(null, "INVALID CARD NUMBER");
                }
            } else {
                JOptionPane.showMessageDialog(null, "ENTER ALL FIELDS");
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "INVALID INPUT");
            ex.printStackTrace();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "SQL ERROR");
            ex.printStackTrace();
        }

    }




    // PAYMENT PORTAL METHODS

    private void handlePaymentView(PaymentImplement tempPayment,
                                   LoanImplement tempLoan, CardImplement tempCard) {

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
                        if (!viewPaymentsStartDateField.getText().isEmpty()
                                && !viewPaymentsEndDateField.getText().isEmpty()) {
                            viewPaymentsTable.setModel(tempPayment.getPaymentsInDateRangeByCardNoTableModel(
                                    card_no, viewPaymentsStartDateField.getText(), viewPaymentsEndDateField.getText()));
                        } else if (!viewPaymentsStartDateField.getText().isEmpty()) {
                            viewPaymentsTable.setModel(tempPayment.getPaymentsAfterDateByCardNoTableModel(
                                    card_no, viewPaymentsStartDateField.getText()));
                        } else if (!viewPaymentsEndDateField.getText().isEmpty()) {
                            viewPaymentsTable.setModel(tempPayment.getPaymentsBeforeDateByCardNoTableModel(
                                    card_no, viewPaymentsEndDateField.getText()));
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
                        if (!viewPaymentsStartDateField.getText().isEmpty()
                                && !viewPaymentsEndDateField.getText().isEmpty()) {
                            viewPaymentsTable.setModel(tempPayment.getPaymentsInDateRangeByLoanIDTableModel(
                                    loan_id, viewPaymentsStartDateField.getText(), viewPaymentsEndDateField.getText()));
                        } else if (!viewPaymentsStartDateField.getText().isEmpty()) {
                            viewPaymentsTable.setModel(tempPayment.getPaymentsAfterDateByLoanIDTableModel(
                                    loan_id, viewPaymentsStartDateField.getText()));
                        } else if (!viewPaymentsEndDateField.getText().isEmpty()) {
                            viewPaymentsTable.setModel(tempPayment.getPaymentsBeforeDateByLoanIDTableModel(
                                    loan_id, viewPaymentsEndDateField.getText()));
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

    }

    private void handlePaymentAdd(PaymentImplement tempPayment, CardImplement tempCard) {

        try {

            if (!addPaymentCardNoTextField.getText().isEmpty()
                    && !addPaymentAmountTextField.getText().isEmpty()) {

                long card_no = Long.parseLong(addPaymentCardNoTextField.getText());
                float payment_value = Float.parseFloat(addPaymentAmountTextField.getText());

                if (tempCard.checkCardExists(card_no)) {
                    float leftover = tempPayment.addPayment(card_no, payment_value);
                    float payment_used = payment_value - leftover;
                    JOptionPane.showMessageDialog(null,
                            String.format("PAYMENT OF ₹%.2f ADDED TO CARD NO. %d WITH ₹%.2f LEFT OVER",
                                    payment_used, card_no, leftover)
                            );
                } else {
                    JOptionPane.showMessageDialog(null, "INVALID CARD NO");
                }
            } else {
                JOptionPane.showMessageDialog(null, "ENTER ALL FIELDS");
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "INVALID INPUT");
            ex.printStackTrace();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "SQL ERROR");
            ex.printStackTrace();
        }

    }




    // CUSTOMER PORTAL METHODS

    private void handleCustomerView(CustomerImplement tempCustomer, CardImplement tempCard) {

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

    }

    private void handleCustomerAdd(CustomerImplement tempCustomer) {

        try {

            if (!addCustomerFirstNameTextField.getText().isEmpty()
                    && !addCustomerMiddleNameTextField.getText().isEmpty()
                    && !addCustomerLastNameTextField.getText().isEmpty()
                    && !addCustomerAadhaarTextField.getText().isEmpty()
                    && !addCustomerHouseNoTextField.getText().isEmpty()
                    && !addCustomerStreetNameTextField.getText().isEmpty()
                    && !addCustomerCityTextField.getText().isEmpty()
                    && !addCustomerStateTextField.getText().isEmpty()
                    && !addCustomerCountryTextField.getText().isEmpty()
                    && !addCustomerZipCodeTextField.getText().isEmpty()
                    && !addCustomerPhoneTextField.getText().isEmpty()
                    && !addCustomerEmailTextField.getText().isEmpty()) {

                String first_name = addCustomerFirstNameTextField.getText();
                String middle_name = addCustomerMiddleNameTextField.getText();
                String last_name = addCustomerLastNameTextField.getText();
                long aadhaar = Long.parseLong(addCustomerAadhaarTextField.getText());
                String house_no = addCustomerHouseNoTextField.getText();
                String street_name = addCustomerStreetNameTextField.getText();
                String city = addCustomerCityTextField.getText();
                String state = addCustomerStateTextField.getText();
                String country = addCustomerCountryTextField.getText();
                String zip_code = addCustomerZipCodeTextField.getText();
                String phone = addCustomerPhoneTextField.getText();
                String email = addCustomerEmailTextField.getText();

                if (tempCustomer.checkCustomerExistsByAadhaar(aadhaar)) {
                    JOptionPane.showMessageDialog(null,
                            String.format("CUSTOMER WITH AADHAAR NO. %d ALREADY EXISTS", aadhaar)
                            );
                } else {
                    long card_no = tempCustomer.addCustomer(first_name, middle_name, last_name, aadhaar,
                            house_no, street_name, city, state, country, zip_code, phone, email);
                    JOptionPane.showMessageDialog(null,
                            String.format("CUSTOMER '%s %s %s' ADDED TO DATABASE WITH CARD NO. %d",
                                    first_name, middle_name, last_name, card_no)
                            );
                }
            } else {
                JOptionPane.showMessageDialog(null, "ENTER ALL FIELDS");
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

    }




    // EMPLOYEE PORTAL METHODS

    private void handleEmployeeView(EmployeeImplement tempEmployee) {

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

            } else if (boxSelected.equals("AADHAAR NUMBER")) {

                if (!viewEmployeesTextField.getText().isEmpty()) {
                    long aadhaar = Long.parseLong(viewEmployeesTextField.getText());

                    if (tempEmployee.checkEmployeeExistsByAadhaar(aadhaar)) {
                        viewEmployeesTable.setModel(tempEmployee.getEmployeeInfoByAadhaarTableModel(aadhaar));
                    } else {
                        JOptionPane.showMessageDialog(null, "INVALID AADHAAR NUMBER");
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

    }

    private void handleEmployeeAdd(EmployeeImplement tempEmployee) {

        try {

            if (!addEmployeeFirstNameTextField.getText().isEmpty()
                    && !addEmployeeMiddleNameTextField.getText().isEmpty()
                    && !addEmployeeLastNameTextField.getText().isEmpty()
                    && !addEmployeeAadhaarTextField.getText().isEmpty()
                    && !addEmployeeHouseNoTextField.getText().isEmpty()
                    && !addEmployeeStreetNameTextField.getText().isEmpty()
                    && !addEmployeeCityTextField.getText().isEmpty()
                    && !addEmployeeStateTextField.getText().isEmpty()
                    && !addEmployeeCountryTextField.getText().isEmpty()
                    && !addEmployeeZipCodeTextField.getText().isEmpty()
                    && !addEmployeePhoneTextField.getText().isEmpty()
                    && !addEmployeeEmailTextField.getText().isEmpty()
                    && !String.valueOf(addEmployeePasswordTextField.getPassword()).isEmpty()) {

                String first_name = addEmployeeFirstNameTextField.getText();
                String middle_name = addEmployeeMiddleNameTextField.getText();
                String last_name = addEmployeeLastNameTextField.getText();
                long aadhaar = Long.parseLong(addEmployeeAadhaarTextField.getText());
                String password = String.valueOf(addEmployeePasswordTextField.getPassword());
                String house_no = addEmployeeHouseNoTextField.getText();
                String street_name = addEmployeeStreetNameTextField.getText();
                String city = addEmployeeCityTextField.getText();
                String state = addEmployeeStateTextField.getText();
                String country = addEmployeeCountryTextField.getText();
                String zip_code = addEmployeeZipCodeTextField.getText();
                String phone = addEmployeePhoneTextField.getText();
                String email = addEmployeeEmailTextField.getText();

                if (tempEmployee.checkEmployeeExistsByAadhaar(aadhaar)) {
                    JOptionPane.showMessageDialog(null,
                            String.format("EMPLOYEE WITH AADHAAR NO. %d ALREADY EXISTS", aadhaar)
                    );
                } else {
                    tempEmployee.addEmployee(password, first_name, middle_name, last_name, aadhaar,
                            house_no, street_name, city, state, country, zip_code, phone, email);
                    JOptionPane.showMessageDialog(null,
                            String.format("EMPLOYEE '%s %s %s' ADDED TO DATABASE",
                                    first_name, middle_name, last_name)
                    );
                }
            } else {
                JOptionPane.showMessageDialog(null, "ENTER ALL FIELDS");
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

    }




    // HELPER METHODS

    private void viewSortedLoans(LoanImplement tempLoan) throws SQLException {

        if (radioBtnIsActive.isSelected()) { // If active loans selected
            if (!viewLoansStartDateField.getText().isEmpty() && !viewLoansEndDateField.getText().isEmpty()) {
                viewLoansTable.setModel(tempLoan.getAllActiveLoansInDateRangeTableModel(
                        viewLoansStartDateField.getText(), viewLoansEndDateField.getText()));
            } else if (!viewLoansStartDateField.getText().isEmpty()) {
                viewLoansTable.setModel(tempLoan.getAllActiveLoansAfterDateTableModel(
                        viewLoansStartDateField.getText()));
            } else if (!viewLoansEndDateField.getText().isEmpty()) {
                viewLoansTable.setModel(tempLoan.getAllActiveLoansBeforeDateTableModel(
                        viewLoansEndDateField.getText()));
            } else {
                viewLoansTable.setModel(tempLoan.getAllActiveLoansTableModel());
            }
        } else { // If all loans selected
            if (!viewLoansStartDateField.getText().isEmpty() && !viewLoansEndDateField.getText().isEmpty()) {
                viewLoansTable.setModel(tempLoan.getAllLoansInDateRangeTableModel(
                        viewLoansStartDateField.getText(), viewLoansEndDateField.getText()));
            } else if (!viewLoansStartDateField.getText().isEmpty()) {
                viewLoansTable.setModel(tempLoan.getAllLoansAfterDateTableModel(
                        viewLoansStartDateField.getText()));
            } else if (!viewLoansEndDateField.getText().isEmpty()) {
                viewLoansTable.setModel(tempLoan.getAllLoansBeforeDateTableModel(
                        viewLoansEndDateField.getText()));
            } else {
                viewLoansTable.setModel(tempLoan.getAllLoansTableModel());
            }
        }

    }

    private void viewSortedPayments(PaymentImplement tempPayment) throws SQLException {

        if (!viewPaymentsStartDateField.getText().isEmpty()
                && !viewPaymentsEndDateField.getText().isEmpty()) {
            viewPaymentsTable.setModel(tempPayment.getAllPaymentsInDateRangeTableModel(
                    viewPaymentsStartDateField.getText(), viewPaymentsEndDateField.getText()));
        } else if (!viewPaymentsStartDateField.getText().isEmpty()) {
            viewPaymentsTable.setModel(tempPayment.getAllPaymentsAfterDateTableModel(
                    viewPaymentsStartDateField.getText()));
        } else if (!viewPaymentsEndDateField.getText().isEmpty()) {
            viewPaymentsTable.setModel(tempPayment.getAllPaymentsBeforeDateTableModel(
                    viewPaymentsEndDateField.getText()));
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
    private JFormattedTextField viewLoansStartDateField;
    private JFormattedTextField viewLoansEndDateField;
    private JRadioButton radioBtnIsActive;
    private JTable viewCustomersTable;
    private JTable viewEmployeesTable;
    private JButton btnViewPaymentsSubmit;
    private JTextField viewEmployeesTextField;
    private JComboBox viewCustomersComboBox;
    private JTable viewPaymentsTable;
    private JFormattedTextField viewPaymentsStartDateField;
    private JFormattedTextField viewPaymentsEndDateField;
    private JComboBox viewPaymentsComboBox;
    private JTextField viewPaymentsTextField;
    private JTextField addLoanCardNoTextField;
    private JTextField addLoanPrincipleTextField;
    private JTextField addLoanInterestRateTextField;
    private JButton btnAddLoanSubmit;
    private JTextField addPaymentCardNoTextField;
    private JTextField addPaymentAmountTextField;
    private JButton btnAddCustomerSubmit;
    private JPasswordField addEmployeePasswordTextField;
    private JButton btnAddPaymentSubmit;
    private JTextField addCustomerFirstNameTextField;
    private JTextField addCustomerMiddleNameTextField;
    private JTextField addCustomerLastNameTextField;
    private JTextField addCustomerAadhaarTextField;
    private JTextField addCustomerHouseNoTextField;
    private JTextField addCustomerStreetNameTextField;
    private JTextField addCustomerCityTextField;
    private JTextField addCustomerStateTextField;
    private JTextField addCustomerCountryTextField;
    private JTextField addCustomerZipCodeTextField;
    private JTextField addCustomerPhoneTextField;
    private JTextField addCustomerEmailTextField;
    private JTextField addEmployeeFirstNameTextField;
    private JTextField addEmployeeMiddleNameTextField;
    private JTextField addEmployeeLastNameTextField;
    private JTextField addEmployeeAadhaarTextField;
    private JTextField addEmployeeHouseNoTextField;
    private JTextField addEmployeeStreetNameTextField;
    private JTextField addEmployeeCityTextField;
    private JTextField addEmployeeStateTextField;
    private JTextField addEmployeeCountryTextField;
    private JTextField addEmployeeZipCodeTextField;
    private JTextField addEmployeePhoneTextField;
    private JTextField addEmployeeEmailTextField;
    private JButton btnAddEmployeeSubmit;

}

