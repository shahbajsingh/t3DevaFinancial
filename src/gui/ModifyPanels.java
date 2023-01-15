package gui;

import module.*;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.sql.Timestamp;

public class ModifyPanels {

    // MODIFY LOAN METHODS

    public static boolean modifyLoanDatePrompt(LoanImplement tempLoan, long loan_id) throws SQLException {

        Timestamp newLoanDate = null;

        JPanel newPanel = new JPanel();
        newPanel.setLayout(new BoxLayout(newPanel, BoxLayout.Y_AXIS));
        JTextField newLoanDateTextField = new JTextField(10);

        newPanel.add(new JLabel("ENTER NEW LOAN DATE:"));
        newPanel.add(Box.createVerticalStrut(15));
        newPanel.add(newLoanDateTextField);

        int result = JOptionPane.showConfirmDialog(null, newPanel,
                "MODIFY LOAN DATE", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            newLoanDate = Timestamp.valueOf(newLoanDateTextField.getText() + " 00:00:00");

            tempLoan.setLoanDate(newLoanDate, loan_id);
            JOptionPane.showMessageDialog(null, "LOAN DATE MODIFIED");

            return true;
        } else {
            JOptionPane.showMessageDialog(null, "MODIFICATION CANCELLED");
            return false;
        }

    }

    public static boolean modifyLoanCardNoPrompt(LoanImplement tempLoan, CardImplement tempCard,
                                              long loan_id) throws SQLException {

        long newLoanCardNo = 0L;

        JPanel newPanel = new JPanel();
        newPanel.setLayout(new BoxLayout(newPanel, BoxLayout.Y_AXIS));
        JTextField newLoanCardNoTextField = new JTextField(10);

        newPanel.add(new JLabel("ENTER NEW LOAN CARD NUMBER: "));
        newPanel.add(Box.createVerticalStrut(15));
        newPanel.add(newLoanCardNoTextField);

        int result = JOptionPane.showConfirmDialog(null, newPanel,
                "MODIFY LOAN CARD NUMBER", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            newLoanCardNo = Long.parseLong(newLoanCardNoTextField.getText());

            if (tempCard.checkCardExists(newLoanCardNo)) {
                tempLoan.setLoanCardNo(newLoanCardNo, loan_id);
                JOptionPane.showMessageDialog(null, "LOAN CARD NUMBER MODIFIED");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "INVALID CARD NUMBER");
                return false;
            }
        } else {
            JOptionPane.showMessageDialog(null, "MODIFICATION CANCELLED");
            return false;
        }

    }

    public static boolean modifyLoanPrinciplePrompt(LoanImplement tempLoan, long loan_id) throws SQLException {

        float newLoanPrinciple = 0.00f;

        JPanel newPanel = new JPanel();
        newPanel.setLayout(new BoxLayout(newPanel, BoxLayout.Y_AXIS));
        JTextField newLoanPrincipleTextField = new JTextField(10);

        newPanel.add(new JLabel("ENTER NEW LOAN PRINCIPLE: ₹"));
        newPanel.add(Box.createVerticalStrut(15));
        newPanel.add(newLoanPrincipleTextField);

        int result = JOptionPane.showConfirmDialog(null, newPanel,
                "MODIFY LOAN PRINCIPLE", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            newLoanPrinciple = Float.parseFloat(newLoanPrincipleTextField.getText());
            tempLoan.setLoanValue(newLoanPrinciple, loan_id);

            JOptionPane.showMessageDialog(null, "LOAN PRINCIPLE VALUE MODIFIED");
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "MODIFICATION CANCELLED");
            return false;
        }

    }

    public static boolean modifyLoanInterestRatePrompt(LoanImplement tempLoan, long loan_id) throws SQLException {

        float newLoanInterestRate = 0.00f;

        JPanel newPanel = new JPanel();
        newPanel.setLayout(new BoxLayout(newPanel, BoxLayout.Y_AXIS));
        JTextField newLoanInterestRateTextField = new JTextField(10);

        newPanel.add(new JLabel("ENTER NEW LOAN INTEREST RATE: "));
        newPanel.add(Box.createVerticalStrut(15));
        newPanel.add(newLoanInterestRateTextField);

        int result = JOptionPane.showConfirmDialog(null, newPanel,
                "MODIFY LOAN INTEREST RATE", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            newLoanInterestRate = Float.parseFloat(newLoanInterestRateTextField.getText());
            tempLoan.setLoanInterestRate(newLoanInterestRate, loan_id);

            JOptionPane.showMessageDialog(null, "LOAN INTEREST RATE MODIFIED");
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "MODIFICATION CANCELLED");
            return false;
        }

    }

    public static boolean modifyLoanIsActivePrompt(LoanImplement tempLoan, long loan_id) throws SQLException {

        boolean newLoanIsActive = false;

        JPanel newPanel = new JPanel();
        newPanel.setLayout(new BoxLayout(newPanel, BoxLayout.Y_AXIS));
        JComboBox newLoanIsActiveComboBox = new JComboBox();

        newLoanIsActiveComboBox.addItem("ACTIVE");
        newLoanIsActiveComboBox.addItem("INACTIVE");

        newPanel.add(new JLabel("LOAN STATUS: "));
        newPanel.add(Box.createVerticalStrut(15));
        newPanel.add(newLoanIsActiveComboBox);

        int result = JOptionPane.showConfirmDialog(null, newPanel,
                "MODIFY LOAN ACTIVE STATUS", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            newLoanIsActive = newLoanIsActiveComboBox.getSelectedItem().equals("ACTIVE");
            tempLoan.setLoanIsActive(newLoanIsActive, loan_id);

            JOptionPane.showMessageDialog(null, "LOAN ACTIVE STATUS MODIFIED");
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "MODIFICATION CANCELLED");
            return false;
        }

    }




    // MODIFY PAYMENT METHODS

    public static boolean modifyPaymentDatePrompt(PaymentImplement tempPayment, long payment_id) throws SQLException {

        Timestamp newPaymentDate = null;

        JPanel newPanel = new JPanel();
        newPanel.setLayout(new BoxLayout(newPanel, BoxLayout.Y_AXIS));
        JTextField newPaymentDateField = new JTextField(10);

        newPanel.add(new JLabel("ENTER NEW PAYMENT DATE:"));
        newPanel.add(Box.createVerticalStrut(15));
        newPanel.add(newPaymentDateField);

        int result = JOptionPane.showConfirmDialog(null, newPanel,
                "MODIFY PAYMENT DATE", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            newPaymentDate = Timestamp.valueOf(newPaymentDateField.getText() + " 00:00:00");

            tempPayment.setPaymentDate(newPaymentDate, payment_id);
            JOptionPane.showMessageDialog(null, "PAYMENT DATE MODIFIED");

            return true;
        } else {
            JOptionPane.showMessageDialog(null, "MODIFICATION CANCELLED");
            return false;
        }

    }

    public static boolean modifyPaymentCardNoPrompt(PaymentImplement tempPayment, CardImplement tempCard,
                                                    long payment_id) throws SQLException {

        long newPaymentCardNo = 0L;

        JPanel newPanel = new JPanel();
        newPanel.setLayout(new BoxLayout(newPanel, BoxLayout.Y_AXIS));
        JTextField newPaymentCardNoTextField = new JTextField(10);

        newPanel.add(new JLabel("ENTER NEW PAYMENT CARD NUMBER: "));
        newPanel.add(Box.createVerticalStrut(15));
        newPanel.add(newPaymentCardNoTextField);

        int result = JOptionPane.showConfirmDialog(null, newPanel,
                "MODIFY PAYMENT CARD NUMBER", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            newPaymentCardNo = Long.parseLong(newPaymentCardNoTextField.getText());

            if (tempCard.checkCardExists(newPaymentCardNo)) {
                tempPayment.setPaymentCardNo(newPaymentCardNo, payment_id);
                JOptionPane.showMessageDialog(null, "PAYMENT CARD NUMBER MODIFIED");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "INVALID CARD NUMBER");
                return false;
            }
        } else {
            JOptionPane.showMessageDialog(null, "MODIFICATION CANCELLED");
            return false;
        }

    }

    public static boolean modifyPaymentValuePrompt(PaymentImplement tempPayment, long payment_id) throws SQLException {

        float newPaymentValue = 0.00f;

        JPanel newPanel = new JPanel();
        newPanel.setLayout(new BoxLayout(newPanel, BoxLayout.Y_AXIS));
        JTextField newPaymentValueTextField = new JTextField(10);

        newPanel.add(new JLabel("ENTER NEW PAYMENT VALUE: "));
        newPanel.add(Box.createVerticalStrut(15));
        newPanel.add(newPaymentValueTextField);

        int result = JOptionPane.showConfirmDialog(null, newPanel,
                "MODIFY PAYMENT VALUE", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            newPaymentValue = Float.parseFloat(newPaymentValueTextField.getText());
            tempPayment.setPaymentValue(newPaymentValue, payment_id);

            JOptionPane.showMessageDialog(null, "PAYMENT VALUE MODIFIED");
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "MODIFICATION CANCELLED");
            return false;
        }

    }

    public static boolean modifyPaymentLoanIDPrompt(PaymentImplement tempPayment,
                                                    LoanImplement tempLoan, long loan_id) throws SQLException {

        long newPaymentLoanID = 0L;

        JPanel newPanel = new JPanel();
        newPanel.setLayout(new BoxLayout(newPanel, BoxLayout.Y_AXIS));
        JTextField newPaymentLoanIDTextField = new JTextField(10);

        newPanel.add(new JLabel("ENTER NEW PAYMENT LOAN ID: "));
        newPanel.add(Box.createVerticalStrut(15));
        newPanel.add(newPaymentLoanIDTextField);

        int result = JOptionPane.showConfirmDialog(null, newPanel,
                "MODIFY PAYMENT LOAN ID", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            newPaymentLoanID = Long.parseLong(newPaymentLoanIDTextField.getText());

            if (tempLoan.checkLoanExists(newPaymentLoanID)) {
                tempPayment.setPaymentLoanID(newPaymentLoanID, loan_id);
                JOptionPane.showMessageDialog(null, "PAYMENT LOAN ID MODIFIED");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "INVALID LOAN ID");
                return false;
            }
        } else {
            JOptionPane.showMessageDialog(null, "MODIFICATION CANCELLED");
            return false;
        }

    }




    // MODIFY CUSTOMER METHODS

    public static boolean modifyCustomerDetailsPrompt(CustomerImplement tempCustomer,
                                                      long card_no) throws SQLException {

        String newCustomerFirstName = null;
        String newCustomerMiddleName = null;
        String newCustomerLastName = null;
        long newCustomerAadhaar = 0L;

        JPanel newPanel = new JPanel();
        newPanel.setLayout(new BoxLayout(newPanel, BoxLayout.Y_AXIS));
        JTextField newCustomerFirstNameTextField = new JTextField(10);
        JTextField newCustomerMiddleNameTextField = new JTextField(10);
        JTextField newCustomerLastNameTextField = new JTextField(10);
        JTextField newCustomerAadhaarTextField = new JTextField(10);

        newPanel.add(new JLabel("FIRST NAME: "));
        newPanel.add(Box.createVerticalStrut(10));
        newPanel.add(newCustomerFirstNameTextField);
        newPanel.add(Box.createVerticalStrut(15));

        newPanel.add(new JLabel("MIDDLE NAME: "));
        newPanel.add(Box.createVerticalStrut(10));
        newPanel.add(newCustomerMiddleNameTextField);
        newPanel.add(Box.createVerticalStrut(15));

        newPanel.add(new JLabel("LAST NAME: "));
        newPanel.add(Box.createVerticalStrut(10));
        newPanel.add(newCustomerLastNameTextField);
        newPanel.add(Box.createVerticalStrut(15));

        newPanel.add(new JLabel("AADHAAR NUMBER: "));
        newPanel.add(Box.createVerticalStrut(10));
        newPanel.add(newCustomerAadhaarTextField);
        newPanel.add(Box.createVerticalStrut(15));

        int result = JOptionPane.showConfirmDialog(null, newPanel,
                "MODIFY CUSTOMER DETAILS", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {

            if (!newCustomerFirstNameTextField.getText().isEmpty()
                    && !newCustomerLastNameTextField.getText().isEmpty()) {

                newCustomerFirstName = newCustomerFirstNameTextField.getText();
                newCustomerMiddleName = newCustomerMiddleNameTextField.getText();
                newCustomerLastName = newCustomerLastNameTextField.getText();

            }

            if (!newCustomerAadhaarTextField.getText().isEmpty()) {
                newCustomerAadhaar = Long.parseLong(newCustomerAadhaarTextField.getText());
            }

            if (newCustomerFirstName != null && newCustomerLastName != null) {
                tempCustomer.setCustomerName(newCustomerFirstName, newCustomerMiddleName,
                        newCustomerLastName, card_no);
                JOptionPane.showMessageDialog(null, "CUSTOMER NAME MODIFIED");
            }

            if (!tempCustomer.checkCustomerExistsByAadhaar(newCustomerAadhaar)) {
                if (newCustomerAadhaar != 0L) {
                    tempCustomer.setCustomerAadhaar(newCustomerAadhaar, card_no);
                    JOptionPane.showMessageDialog(null, "CUSTOMER AADHAAR MODIFIED");
                }
            } else {
                JOptionPane.showMessageDialog(null, "AADHAAR NUMBER ALREADY EXISTS IN DATABASE");
                return false;
            }
                return true;
        } else {
            JOptionPane.showMessageDialog(null, "MODIFICATION CANCELLED");
            return false;
        }

    }

    public static boolean modifyCustomerAddressDetailsPrompt(CustomerImplement tempCustomer,
                                                             long card_no) throws SQLException {

        String newCustomerHouseNo = null;
        String newCustomerStreetName = null;
        String newCustomerCity = null;
        String newCustomerState = null;
        String newCustomerCountry = null;
        String newCustomerZipCode = null;

        JPanel newPanel = new JPanel();
        newPanel.setLayout(new GridLayout(6, 2));
        JTextField newCustomerHouseNoTextField = new JTextField(5);
        JTextField newCustomerStreetNameTextField = new JTextField(10);
        JTextField newCustomerCityTextField = new JTextField(10);
        JTextField newCustomerStateTextField = new JTextField(10);
        JTextField newCustomerCountryTextField = new JTextField(10);
        JTextField newCustomerZipCodeTextField = new JTextField(10);

        newPanel.add(new JLabel("HOUSE NUMBER: "));
        newPanel.add(newCustomerHouseNoTextField);

        newPanel.add(new JLabel("STREET NAME: "));
        newPanel.add(newCustomerStreetNameTextField);

        newPanel.add(new JLabel("CITY: "));
        newPanel.add(newCustomerCityTextField);

        newPanel.add(new JLabel("STATE: "));
        newPanel.add(newCustomerStateTextField);

        newPanel.add(new JLabel("COUNTRY: "));
        newPanel.add(newCustomerCountryTextField);

        newPanel.add(new JLabel("ZIP CODE: "));
        newPanel.add(newCustomerZipCodeTextField);

        int result = JOptionPane.showConfirmDialog(null, newPanel,
                "MODIFY CUSTOMER ADDRESS DETAILS", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {

            if (!newCustomerCityTextField.getText().isEmpty()
                    && !newCustomerStateTextField.getText().isEmpty()
                    && !newCustomerCountryTextField.getText().isEmpty()) {

                newCustomerHouseNo = newCustomerHouseNoTextField.getText();
                newCustomerStreetName = newCustomerStreetNameTextField.getText();
                newCustomerCity = newCustomerCityTextField.getText();
                newCustomerState = newCustomerStateTextField.getText();
                newCustomerCountry = newCustomerCountryTextField.getText();
                newCustomerZipCode = newCustomerZipCodeTextField.getText();

            }

            if (newCustomerCity != null && newCustomerState != null && newCustomerCountry != null) {
                tempCustomer.setCustomerAddress(newCustomerHouseNo, newCustomerStreetName,
                        newCustomerCity, newCustomerState, newCustomerCountry, newCustomerZipCode, card_no);
                JOptionPane.showMessageDialog(null, "CUSTOMER ADDRESS MODIFIED");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "MODIFICATION CANCELLED");
                return false;
            }

        } else {
            JOptionPane.showMessageDialog(null, "MODIFICATION CANCELLED");
            return false;
        }

    }

    public static boolean modifyCustomerContactDetailsPrompt(CustomerImplement tempCustomer,
                                                             long card_no) throws SQLException {

        String newCustomerPhone = null;
        String newCustomerEmail = null;

        JPanel newPanel = new JPanel();
        newPanel.setLayout(new GridLayout(3, 2));
        JTextField newCustomerPhoneTextField = new JTextField(10);
        JTextField newCustomerEmailTextField = new JTextField(10);

        newPanel.add(new JLabel("PHONE NUMBER: "));
        newPanel.add(newCustomerPhoneTextField);

        newPanel.add(Box.createVerticalStrut(5));
        newPanel.add(Box.createVerticalStrut(5));

        newPanel.add(new JLabel("EMAIL: "));
        newPanel.add(newCustomerEmailTextField);

        int result = JOptionPane.showConfirmDialog(null, newPanel,
                "MODIFY CUSTOMER CONTACT DETAILS", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {

            if (newCustomerPhoneTextField.getText().isEmpty()
                    && newCustomerEmailTextField.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "MODIFICATION CANCELLED");
                return false;
            }

            if (!newCustomerPhoneTextField.getText().isEmpty()) {
                newCustomerPhone = newCustomerPhoneTextField.getText();
            }

            if (!newCustomerEmailTextField.getText().isEmpty()) {
                newCustomerEmail = newCustomerEmailTextField.getText();
            }

            if (newCustomerPhone != null) {
                tempCustomer.setCustomerPhone(newCustomerPhone, card_no);
                JOptionPane.showMessageDialog(null, "CUSTOMER PHONE MODIFIED");
            }

            if (newCustomerEmail != null) {
                tempCustomer.setCustomerEmail(newCustomerEmail, card_no);
                JOptionPane.showMessageDialog(null, "CUSTOMER EMAIL MODIFIED");
            }

            return true;

        } else {
            JOptionPane.showMessageDialog(null, "MODIFICATION CANCELLED");
            return false;
        }

    }




    // MODIFY EMPLOYEE METHODS

    public static boolean modifyEmployeeDetailsPrompt(EmployeeImplement tempEmployee,
                                                      int employee_id) throws SQLException {

        String newEmployeeFirstName = null;
        String newEmployeeMiddleName = null;
        String newEmployeeLastName = null;
        String newEmployeePassword = null;
        long newEmployeeAadhaar = 0L;

        JPanel newPanel = new JPanel();
        newPanel.setLayout(new BoxLayout(newPanel, BoxLayout.Y_AXIS));
        JTextField newEmployeeFirstNameTextField = new JTextField(10);
        JTextField newEmployeeMiddleNameTextField = new JTextField(10);
        JTextField newEmployeeLastNameTextField = new JTextField(10);
        JTextField newEmployeeAadhaarTextField = new JTextField(10);
        JPasswordField newEmployeePasswordTextField = new JPasswordField(10);

        newPanel.add(new JLabel("FIRST NAME: "));
        newPanel.add(Box.createVerticalStrut(10));
        newPanel.add(newEmployeeFirstNameTextField);
        newPanel.add(Box.createVerticalStrut(15));

        newPanel.add(new JLabel("MIDDLE NAME: "));
        newPanel.add(Box.createVerticalStrut(10));
        newPanel.add(newEmployeeMiddleNameTextField);
        newPanel.add(Box.createVerticalStrut(15));

        newPanel.add(new JLabel("LAST NAME: "));
        newPanel.add(Box.createVerticalStrut(10));
        newPanel.add(newEmployeeLastNameTextField);
        newPanel.add(Box.createVerticalStrut(15));

        newPanel.add(new JLabel("AADHAAR NUMBER: "));
        newPanel.add(Box.createVerticalStrut(10));
        newPanel.add(newEmployeeAadhaarTextField);
        newPanel.add(Box.createVerticalStrut(15));

        newPanel.add(new JLabel("PASSWORD: "));
        newPanel.add(Box.createVerticalStrut(10));
        newPanel.add(newEmployeePasswordTextField);
        newPanel.add(Box.createVerticalStrut(15));

        int result = JOptionPane.showConfirmDialog(null, newPanel,
                "MODIFY EMPLOYEE DETAILS", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {

            if (!newEmployeeFirstNameTextField.getText().isEmpty()
                    && !newEmployeeLastNameTextField.getText().isEmpty()) {

                newEmployeeFirstName = newEmployeeFirstNameTextField.getText();
                newEmployeeMiddleName = newEmployeeMiddleNameTextField.getText();
                newEmployeeLastName = newEmployeeLastNameTextField.getText();

            }

            if (!newEmployeeAadhaarTextField.getText().isEmpty()) {
                newEmployeeAadhaar = Long.parseLong(newEmployeeAadhaarTextField.getText());
            }

            if (newEmployeeFirstName != null && newEmployeeLastName != null) {
                tempEmployee.setEmployeeName(newEmployeeFirstName, newEmployeeMiddleName,
                        newEmployeeLastName, employee_id);
                JOptionPane.showMessageDialog(null, "EMPLOYEE NAME MODIFIED");
            }

            if (!tempEmployee.checkEmployeeExistsByAadhaar(newEmployeeAadhaar)) {
                if (newEmployeeAadhaar != 0L) {
                    tempEmployee.setEmployeeAadhaar(newEmployeeAadhaar, employee_id);
                    JOptionPane.showMessageDialog(null, "EMPLOYEE AADHAAR MODIFIED");
                }
            } else {
                JOptionPane.showMessageDialog(null, "AADHAAR NUMBER ALREADY EXISTS IN DATABASE");
                return false;
            }

            if (!newEmployeePasswordTextField.getText().isEmpty()) {
                newEmployeePassword = newEmployeePasswordTextField.getText();
                tempEmployee.setEmployeePassword(newEmployeePassword, employee_id);
                JOptionPane.showMessageDialog(null, "EMPLOYEE PASSWORD MODIFIED");
            }

            return true;
        } else {
            JOptionPane.showMessageDialog(null, "MODIFICATION CANCELLED");
            return false;
        }

    }

    public static boolean modifyEmployeeAddressDetailsPrompt(EmployeeImplement tempEmployee,
                                                             int employee_id) throws SQLException {

        String newEmployeeHouseNo = null;
        String newEmployeeStreetName = null;
        String newEmployeeCity = null;
        String newEmployeeState = null;
        String newEmployeeCountry = null;
        String newEmployeeZipCode = null;

        JPanel newPanel = new JPanel();
        newPanel.setLayout(new GridLayout(6, 2));
        JTextField newEmployeeHouseNoTextField = new JTextField(5);
        JTextField newEmployeeStreetNameTextField = new JTextField(10);
        JTextField newEmployeeCityTextField = new JTextField(10);
        JTextField newEmployeeStateTextField = new JTextField(10);
        JTextField newEmployeeCountryTextField = new JTextField(10);
        JTextField newEmployeeZipCodeTextField = new JTextField(10);

        newPanel.add(new JLabel("HOUSE NUMBER: "));
        newPanel.add(newEmployeeHouseNoTextField);

        newPanel.add(new JLabel("STREET NAME: "));
        newPanel.add(newEmployeeStreetNameTextField);

        newPanel.add(new JLabel("CITY: "));
        newPanel.add(newEmployeeCityTextField);

        newPanel.add(new JLabel("STATE: "));
        newPanel.add(newEmployeeStateTextField);

        newPanel.add(new JLabel("COUNTRY: "));
        newPanel.add(newEmployeeCountryTextField);

        newPanel.add(new JLabel("ZIP CODE: "));
        newPanel.add(newEmployeeZipCodeTextField);

        int result = JOptionPane.showConfirmDialog(null, newPanel,
                "MODIFY EMPLOYEE ADDRESS DETAILS", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {

            if (!newEmployeeCityTextField.getText().isEmpty()
                    && !newEmployeeStateTextField.getText().isEmpty()
                    && !newEmployeeCountryTextField.getText().isEmpty()) {

                newEmployeeHouseNo = newEmployeeHouseNoTextField.getText();
                newEmployeeStreetName = newEmployeeStreetNameTextField.getText();
                newEmployeeCity = newEmployeeCityTextField.getText();
                newEmployeeState = newEmployeeStateTextField.getText();
                newEmployeeCountry = newEmployeeCountryTextField.getText();
                newEmployeeZipCode = newEmployeeZipCodeTextField.getText();

            }

            if (newEmployeeCity != null && newEmployeeState != null && newEmployeeCountry != null) {
                tempEmployee.setEmployeeAddress(newEmployeeHouseNo, newEmployeeStreetName,
                        newEmployeeCity, newEmployeeState, newEmployeeCountry, newEmployeeZipCode,
                        employee_id);
                JOptionPane.showMessageDialog(null, "EMPLOYEE ADDRESS MODIFIED");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "MODIFICATION CANCELLED");
                return false;
            }

        } else {
            JOptionPane.showMessageDialog(null, "MODIFICATION CANCELLED");
            return false;
        }

    }

    public static boolean modifyEmployeeContactDetailsPrompt(EmployeeImplement tempEmployee,
                                                             int employee_id) throws SQLException {

        String newEmployeePhone = null;
        String newEmployeeEmail = null;

        JPanel newPanel = new JPanel();
        newPanel.setLayout(new GridLayout(3, 2));
        JTextField newEmployeePhoneTextField = new JTextField(10);
        JTextField newEmployeeEmailTextField = new JTextField(10);

        newPanel.add(new JLabel("PHONE NUMBER: "));
        newPanel.add(newEmployeePhoneTextField);

        newPanel.add(Box.createVerticalStrut(5));
        newPanel.add(Box.createVerticalStrut(5));

        newPanel.add(new JLabel("EMAIL: "));
        newPanel.add(newEmployeeEmailTextField);

        int result = JOptionPane.showConfirmDialog(null, newPanel,
                "MODIFY EMPLOYEE CONTACT DETAILS", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {

            if (newEmployeePhoneTextField.getText().isEmpty()
                    && newEmployeeEmailTextField.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "MODIFICATION CANCELLED");
                return false;
            }

            if (!newEmployeePhoneTextField.getText().isEmpty()) {
                newEmployeePhone = newEmployeePhoneTextField.getText();
            }

            if (!newEmployeeEmailTextField.getText().isEmpty()) {
                newEmployeeEmail = newEmployeeEmailTextField.getText();
            }

            if (newEmployeePhone != null) {
                tempEmployee.setEmployeePhone(newEmployeePhone, employee_id);
                JOptionPane.showMessageDialog(null, "EMPLOYEE PHONE MODIFIED");
            }

            if (newEmployeeEmail != null) {
                tempEmployee.setEmployeeEmail(newEmployeeEmail, employee_id);
                JOptionPane.showMessageDialog(null, "EMPLOYEE EMAIL MODIFIED");
            }

            return true;

        } else {
            JOptionPane.showMessageDialog(null, "MODIFICATION CANCELLED");
            return false;
        }

    }

}
