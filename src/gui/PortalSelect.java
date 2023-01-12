package gui;

import module.*;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InputMismatchException;

public class PortalSelect extends JFrame {

    public PortalSelect() {

        super("DFS PORTALS");

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

        initializeComponents();

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

            if (textFieldsFilled(addLoanTextFields)) {

                long card_no = Long.parseLong(addLoanCardNoTextField.getText());
                float loan_value = Float.parseFloat(addLoanPrincipleTextField.getText());
                float interest_rate = Float.parseFloat(addLoanInterestRateTextField.getText());

                if (tempCard.checkCardExists(card_no)) {
                    tempLoan.addLoan(card_no, loan_value, interest_rate);
                    JOptionPane.showMessageDialog(null,
                            String.format("LOAN OF ₹%.2f ADDED TO CARD NO. %d AT %.2f%% INTEREST RATE",
                                    loan_value, card_no, interest_rate)
                            );
                    clearTextFields(addLoanTextFields);
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

            if (textFieldsFilled(addPaymentTextFields)) {

                long card_no = Long.parseLong(addPaymentCardNoTextField.getText());
                float payment_value = Float.parseFloat(addPaymentAmountTextField.getText());

                if (tempCard.checkCardExists(card_no)) {
                    float leftover = tempPayment.addPayment(card_no, payment_value);
                    float payment_used = payment_value - leftover;
                    JOptionPane.showMessageDialog(null,
                            String.format("PAYMENT OF ₹%.2f ADDED TO CARD NO. %d WITH ₹%.2f LEFT OVER",
                                    payment_used, card_no, leftover)
                            );
                    clearTextFields(addPaymentTextFields);
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
                            viewCustomersTable.setModel(tempCard.getCardInfoTableModel(card_no));
                        } else {
                            viewCustomersTable.setModel(tempCustomer.getCustomerInfoTableModel(card_no));
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "INVALID CARD NUMBER");
                    }
                } else { // If card number not entered
                    if (radioBtnAccountInfo.isSelected()) {
                        viewCustomersTable.setModel(tempCard.getAllCardsInfoTableModel());
                    } else {
                        viewCustomersTable.setModel(tempCustomer.getAllCustomersInfoTableModel());
                    }
                }

            } else if (boxSelected.equals("AADHAAR NUMBER")) {

                if (!viewCustomersTextField.getText().isEmpty()) { // If aadhaar number entered
                    long aadhaar = Long.parseLong(viewCustomersTextField.getText());

                    if (tempCustomer.checkCustomerExistsByAadhaar(aadhaar)) { // If aadhaar number exists
                        if (radioBtnAccountInfo.isSelected()) {
                            long card_no = tempCustomer.getCustomerCardNoByAadhaar(aadhaar);
                            viewCustomersTable.setModel(tempCard.getCardInfoTableModel(card_no));
                        } else {
                            viewCustomersTable.setModel(tempCustomer.getCustomerInfoByAadhaarTableModel(aadhaar));
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "INVALID AADHAAR NUMBER");
                    }
                } else { // If aadhaar number not entered
                    if (radioBtnAccountInfo.isSelected()) {
                        viewCustomersTable.setModel(tempCard.getAllCardsInfoTableModel());
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

            if (textFieldsFilled(addCustomerTextFields)) {

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
                    clearTextFields(addCustomerTextFields);
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

                if (!viewEmployeesTextField.getText().isEmpty()) { // If employee ID entered
                    int employee_id = Integer.parseInt(viewEmployeesTextField.getText());

                    if (tempEmployee.checkEmployeeExists(employee_id)) { // If employee with ID exists
                        viewEmployeesTable.setModel(tempEmployee.getEmployeeInfoTableModel(employee_id));
                    } else {
                        JOptionPane.showMessageDialog(null, "INVALID EMPLOYEE ID");
                    }
                } else {
                    viewEmployeesTable.setModel(tempEmployee.getAllEmployeesInfoTableModel());
                }

            } else if (boxSelected.equals("LAST NAME")) {

                if (!viewEmployeesTextField.getText().isEmpty()) { // If last name entered
                    String last_name = viewEmployeesTextField.getText();

                    if (tempEmployee.checkEmployeeExistsByLastName(last_name)) { // If employee with last name exists
                        viewEmployeesTable.setModel(tempEmployee.getEmployeeInfoByLastNameTableModel(last_name));
                    } else {
                        JOptionPane.showMessageDialog(null, "INVALID LAST NAME");
                    }
                } else {
                    viewEmployeesTable.setModel(tempEmployee.getAllEmployeesInfoTableModel());
                }

            } else if (boxSelected.equals("AADHAAR NUMBER")) {

                if (!viewEmployeesTextField.getText().isEmpty()) { // If aadhaar number entered
                    long aadhaar = Long.parseLong(viewEmployeesTextField.getText());

                    if (tempEmployee.checkEmployeeExistsByAadhaar(aadhaar)) { // If employee with aadhaar number exists
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

            if (textFieldsFilled(addEmployeeTextFields)) {

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
                    clearTextFields(addEmployeeTextFields);
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

    private void initializeComponents(){
        initializeTextFields();
    }

    private void initializeTextFields() {

        // ADD PANELS

        for (Component c : addLoanPanel.getComponents()) {
            if (c instanceof JTextField) {
                addLoanTextFields.add((JTextField) c);
            }
        }

        for (Component c : addPaymentPanel.getComponents()) {
            if (c instanceof JTextField) {
                addPaymentTextFields.add((JTextField) c);
            }
        }

        for (Component c : addCustomerPanel.getComponents()) {
            if (c instanceof JTextField) {
                addCustomerTextFields.add((JTextField) c);
            }
        }

        for (Component c : addEmployeePanel.getComponents()) {
            if (c instanceof JTextField || c instanceof JPasswordField) {
                addEmployeeTextFields.add((JTextField) c);
            }
        }

        // MODIFY PANELS

        for (Component c : modifyLoanPanel.getComponents()) {
            if (c instanceof JTextField) {
                modifyLoanTextFields.add((JTextField) c);
            }
        }

        for (Component c : modifyPaymentPanel.getComponents()) {
            if (c instanceof JTextField) {
                modifyPaymentTextFields.add((JTextField) c);
            }
        }

        for (Component c : modifyCustomerPanel.getComponents()) {
            if (c instanceof JTextField) {
                modifyCustomerTextFields.add((JTextField) c);
            }
        }

        for (Component c : modifyEmployeePanel.getComponents()) {
            if (c instanceof JTextField || c instanceof JPasswordField) {
                modifyEmployeeTextFields.add((JTextField) c);
            }
        }

    }

    private void clearTextField(JTextField textField) {

        textField.setText("");

    }

    private void clearTextFields(ArrayList<JTextField> textFields) {

        for (JTextField textField : textFields) {
            textField.setText("");
        }

    }

    private boolean textFieldsFilled(ArrayList<JTextField> textFields) {

        for (JTextField textField : textFields) {
            if (textField.getText().isEmpty()) {
                return false;
            }
        }

        return true;

    }

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

    private final ArrayList<JTextField> addLoanTextFields = new ArrayList<>();
    private final ArrayList<JTextField> addPaymentTextFields = new ArrayList<>();
    private final ArrayList<JTextField> addCustomerTextFields = new ArrayList<>();
    private final ArrayList<JTextField> addEmployeeTextFields = new ArrayList<>();

    private final ArrayList<JTextField> modifyLoanTextFields = new ArrayList<>();
    private final ArrayList<JTextField> modifyPaymentTextFields = new ArrayList<>();
    private final ArrayList<JTextField> modifyCustomerTextFields = new ArrayList<>();
    private final ArrayList<JTextField> modifyEmployeeTextFields = new ArrayList<>();

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
    private JPanel addLoanPanel;
    private JPanel viewLoansPanel;
    private JPanel modifyLoanPanel;
    private JPanel deleteLoanPanel;
    private JPanel viewPaymentsPanel;
    private JPanel addPaymentPanel;
    private JPanel modifyPaymentPanel;
    private JPanel deletePaymentPanel;
    private JPanel viewCustomersPanel;
    private JPanel addCustomerPanel;
    private JPanel deleteCustomerPanel;
    private JPanel viewEmployeesPanel;
    private JPanel addEmployeePanel;
    private JPanel deleteEmployeePanel;
    private JTextField modifyLoanIDTextField;
    private JTextField modifyLoanDateTextField;
    private JTextField modifyLoanCardNoTextField;
    private JTextField modifyLoanPrincipleTextField;
    private JTextField modifyLoanInterestRateTextField;
    private JTextField modifyLoanIsActiveTextField;
    private JTextField modifyLoanAmtRemainingTextField;
    private JTextField modifyLoanInterestAccruedTextField;
    private JButton btnModifyLoanSubmit;
    private JButton btnModifyLoanDate;
    private JButton btnModifyLoanCardNo;
    private JButton btnModifyLoanPrinciple;
    private JButton btnModifyLoanInterestRate;
    private JButton btnModifyLoanIsActive;
    private JTextField modifyPaymentIDTextField;
    private JButton btnModifyPaymentSubmit;
    private JTextField modifyPaymentDateTextField;
    private JTextField modifyPaymentCardNoTextField;
    private JTextField modifyPaymentValueTextField;
    private JTextField modifyPaymentLoanIDTextField;
    private JButton btnModifyPaymentDate;
    private JButton btnModifyPaymentCardNo;
    private JButton btnModifyPaymentValue;
    private JButton btnModifyPaymentLoanID;
    private JPanel modifyCustomerPanel;
    private JTextField modifyCustomerCardNoTextField;
    private JButton btnModifyCustomerSubmit;
    private JButton btnModifyCustomerDetails;
    private JButton btnModifyCustomerAddressDetails;
    private JButton btnModifyCustomerContactDetails;
    private JTextField modifyCustomerFirstNameTextField;
    private JTextField modifyCustomerMiddleNameTextField;
    private JTextField modifyCustomerLastNameTextField;
    private JTextField modifyCustomerAadhaarTextField;
    private JTextField modifyCustomerHouseNoTextField;
    private JTextField modifyCustomerStreetNameTextField;
    private JTextField modifyCustomerCityTextField;
    private JTextField modifyCustomerStateTextField;
    private JTextField modifyCustomerCountryTextField;
    private JTextField modifyCustomerZipCodeTextField;
    private JTextField modifyCustomerPhoneTextField;
    private JTextField modifyCustomerEmailTextField;
    private JTextField modifyEmployeeIDTextField;
    private JButton btnModifyEmployeeSubmit;
    private JButton btnModifyEmployeeDetails;
    private JButton btnModifyEmployeeAddressDetails;
    private JButton btnModifyEmployeeContactDetails;
    private JTextField modifyEmployeeFirstNameTextField;
    private JTextField modifyEmployeeMiddleNameTextField;
    private JTextField modifyEmployeeLastNameTextField;
    private JTextField modifyEmployeeAadhaarTextField;
    private JPasswordField modifyEmployeePasswordTextField;
    private JTextField modifyEmployeeHouseNoTextField;
    private JTextField modifyEmployeeStreetNameTextField;
    private JTextField modifyEmployeeCityTextField;
    private JTextField modifyEmployeeStateTextField;
    private JTextField modifyEmployeeCountryTextField;
    private JTextField modifyEmployeeZipCodeTextField;
    private JTextField modifyEmployeePhoneTextField;
    private JTextField modifyEmployeeEmailTextField;
    private JPanel modifyEmployeePanel;

}

