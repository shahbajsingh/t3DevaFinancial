package gui;

import module.*;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.sql.Timestamp;
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

        handleLoanModify(tempLoan, tempCard);

        // DELETE TAB

        handleLoanDelete(tempLoan);

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

        handlePaymentModify(tempPayment, tempLoan, tempCard);

        // DELETE TAB

        handlePaymentDelete(tempPayment);

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

        handleCustomerModify(tempCustomer);

        // DELETE TAB

        handleCustomerDelete(tempCustomer);

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

        handleEmployeeModify(tempEmployee);

        // DELETE TAB

        handleEmployeeDelete(tempEmployee);

    }

    private void handleLogOut(){

        btnLogOut.addActionListener(e -> {

            int result = JOptionPane.showConfirmDialog(null, "LOG OUT?",
                    "DFS PORTAL", JOptionPane.YES_NO_OPTION);

            if(result == JOptionPane.YES_OPTION){
                this.dispose();
                new Login();
            }

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

    private void handleLoanModify(LoanImplement tempLoan, CardImplement tempCard) {


        btnModifyLoanSubmit.addActionListener(e -> { // populate text fields

            try {

                populateLoanTextFields(tempLoan, modifyLoanIDTextField, modifyLoanDateTextField,
                        modifyLoanCardNoTextField, modifyLoanPrincipleTextField, modifyLoanInterestRateTextField,
                        modifyLoanIsActiveTextField, modifyLoanAmtRemainingTextField, modifyLoanInterestAccruedTextField);

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "INVALID INPUT");
                ex.printStackTrace();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "SQL ERROR");
                ex.printStackTrace();
            }

        });

        btnModifyLoanDate.addActionListener(e -> {

            try {

                if (!modifyLoanDateTextField.getText().isEmpty()) {

                    long loan_id = Long.parseLong(modifyLoanIDTextField.getText());

                    if (tempLoan.checkLoanExists(loan_id)) {
                        boolean modified = ModifyPanels.modifyLoanDatePrompt(tempLoan, loan_id);
                        if (modified) {
                            clearTextFields(modifyLoanTextFields);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "INVALID LOAN ID");
                    }
                }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "INVALID INPUT");
                ex.printStackTrace();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "SQL ERROR");
                ex.printStackTrace();
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(null, "INVALID DATE");
                ex.printStackTrace();
            }

        });

        btnModifyLoanCardNo.addActionListener(e -> {

            try {

                if (!modifyLoanDateTextField.getText().isEmpty()) {

                    long loan_id = Long.parseLong(modifyLoanIDTextField.getText());

                    if (tempLoan.checkLoanExists(loan_id)) {
                        boolean modified = ModifyPanels.modifyLoanCardNoPrompt(tempLoan, tempCard, loan_id);
                        if (modified) {
                            clearTextFields(modifyLoanTextFields);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "INVALID LOAN ID");
                    }
                }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "INVALID INPUT");
                ex.printStackTrace();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "SQL ERROR");
                ex.printStackTrace();
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(null, "INVALID CARD NUMBER");
                ex.printStackTrace();
            }

        });

        btnModifyLoanPrinciple.addActionListener(e -> {

            try {

                if (!modifyLoanDateTextField.getText().isEmpty()) {

                    long loan_id = Long.parseLong(modifyLoanIDTextField.getText());

                    if (tempLoan.checkLoanExists(loan_id)) {
                        boolean modified = ModifyPanels.modifyLoanPrinciplePrompt(tempLoan, loan_id);
                        if (modified) {
                            clearTextFields(modifyLoanTextFields);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "INVALID LOAN ID");
                    }
                }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "INVALID INPUT");
                ex.printStackTrace();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "SQL ERROR");
                ex.printStackTrace();
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(null, "INVALID LOAN PRINCIPLE");
                ex.printStackTrace();
            }

        });

        btnModifyLoanInterestRate.addActionListener(e -> {

            try {

                if (!modifyLoanDateTextField.getText().isEmpty()) {

                    long loan_id = Long.parseLong(modifyLoanIDTextField.getText());

                    if (tempLoan.checkLoanExists(loan_id)) {
                        boolean modified = ModifyPanels.modifyLoanInterestRatePrompt(tempLoan, loan_id);
                        if (modified) {
                            clearTextFields(modifyLoanTextFields);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "INVALID LOAN ID");
                    }
                }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "INVALID INPUT");
                ex.printStackTrace();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "SQL ERROR");
                ex.printStackTrace();
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(null, "INVALID INTEREST RATE");
                ex.printStackTrace();
            }

        });

        btnModifyLoanIsActive.addActionListener(e -> {

            try {

                if (!modifyLoanDateTextField.getText().isEmpty()) {

                    long loan_id = Long.parseLong(modifyLoanIDTextField.getText());

                    if (tempLoan.checkLoanExists(loan_id)) {
                        boolean modified = ModifyPanels.modifyLoanIsActivePrompt(tempLoan, loan_id);
                        if (modified) {
                            clearTextFields(modifyLoanTextFields);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "INVALID LOAN ID");
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

    }

    private void handleLoanDelete(LoanImplement tempLoan) {

        btnDeleteLoanSubmit.addActionListener(e -> { // populate text fields

            try {

                populateLoanTextFields(tempLoan, deleteLoanIDTextField, deleteLoanDateTextField,
                        deleteLoanCardNoTextField, deleteLoanPrincipleTextField, deleteLoanInterestRateTextField,
                        deleteLoanIsActiveTextField, deleteLoanAmtRemainingTextField, deleteLoanInterestAccruedTextField);

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "INVALID INPUT");
                ex.printStackTrace();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "SQL ERROR");
                ex.printStackTrace();
            }

        });

        btnDeleteLoan.addActionListener(e -> {

            try {

                if (!deleteLoanIDTextField.getText().isEmpty()) { // If loan ID entered

                    long loan_id = Long.parseLong(deleteLoanIDTextField.getText());

                    if (tempLoan.checkLoanExists(loan_id)) { // If loan exists

                        int confirm = JOptionPane.showConfirmDialog(null,
                                String.format("ARE YOU SURE YOU WANT TO DELETE LOAN ID %d?", loan_id),
                                "DELETE LOAN", JOptionPane.YES_NO_OPTION);

                        if (confirm == JOptionPane.YES_OPTION) {
                            tempLoan.deleteLoan(loan_id);
                            JOptionPane.showMessageDialog(null, "LOAN DELETED");
                            clearTextFields(deleteLoanTextFields);
                        } else {
                            JOptionPane.showMessageDialog(null, "LOAN NOT DELETED");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "INVALID LOAN ID");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "ENTER LOAN ID");
                }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "INVALID INPUT");
                ex.printStackTrace();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "SQL ERROR");
                ex.printStackTrace();
            }

        });

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

    private void handlePaymentModify(PaymentImplement tempPayment, LoanImplement tempLoan, CardImplement tempCard) {

        btnModifyPaymentSubmit.addActionListener(e -> { // populate text fields

            try {

                populatePaymentTextFields(tempPayment, modifyPaymentIDTextField, modifyPaymentDateTextField,
                        modifyPaymentCardNoTextField, modifyPaymentValueTextField, modifyPaymentLoanIDTextField);

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "INVALID INPUT");
                ex.printStackTrace();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "SQL ERROR");
                ex.printStackTrace();
            }

        });

        btnModifyPaymentDate.addActionListener(e -> {

            try {

                if (!modifyPaymentIDTextField.getText().isEmpty()) {

                    long payment_id = Long.parseLong(modifyPaymentIDTextField.getText());

                    if (tempPayment.checkPaymentExists(payment_id)) {
                        boolean modified = ModifyPanels.modifyPaymentDatePrompt(tempPayment, payment_id);
                        if (modified) {
                            clearTextFields(modifyPaymentTextFields);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "INVALID PAYMENT ID");
                    }
                }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "INVALID INPUT");
                ex.printStackTrace();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "SQL ERROR");
                ex.printStackTrace();
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(null, "INVALID DATE");
                ex.printStackTrace();
            }

        });

        btnModifyPaymentCardNo.addActionListener(e -> {

            try {

                if (!modifyPaymentIDTextField.getText().isEmpty()) {

                    long payment_id = Long.parseLong(modifyPaymentIDTextField.getText());

                    if (tempPayment.checkPaymentExists(payment_id)) {
                        boolean modified = ModifyPanels.modifyPaymentCardNoPrompt(tempPayment, tempCard, payment_id);
                        if (modified) {
                            clearTextFields(modifyPaymentTextFields);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "INVALID PAYMENT ID");
                    }
                }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "INVALID INPUT");
                ex.printStackTrace();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "SQL ERROR");
                ex.printStackTrace();
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(null, "INVALID CARD NO");
                ex.printStackTrace();
            }

        });

        btnModifyPaymentValue.addActionListener(e -> {

            try {

                if (!modifyPaymentIDTextField.getText().isEmpty()) {

                    long payment_id = Long.parseLong(modifyPaymentIDTextField.getText());

                    if (tempPayment.checkPaymentExists(payment_id)) {
                        boolean modified = ModifyPanels.modifyPaymentValuePrompt(tempPayment, payment_id);
                        if (modified) {
                            clearTextFields(modifyPaymentTextFields);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "INVALID PAYMENT ID");
                    }
                }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "INVALID INPUT");
                ex.printStackTrace();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "SQL ERROR");
                ex.printStackTrace();
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(null, "INVALID PAYMENT VALUE");
                ex.printStackTrace();
            }

        });

        btnModifyPaymentLoanID.addActionListener(e -> {

            try {

                if (!modifyPaymentIDTextField.getText().isEmpty()) {

                    long payment_id = Long.parseLong(modifyPaymentIDTextField.getText());

                    if (tempPayment.checkPaymentExists(payment_id)) {
                        boolean modified = ModifyPanels.modifyPaymentLoanIDPrompt(tempPayment, tempLoan, payment_id);
                        if (modified) {
                            clearTextFields(modifyPaymentTextFields);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "INVALID PAYMENT ID");
                    }
                }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "INVALID INPUT");
                ex.printStackTrace();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "SQL ERROR");
                ex.printStackTrace();
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(null, "INVALID LOAN ID");
                ex.printStackTrace();
            }

        });

    }

    private void handlePaymentDelete(PaymentImplement tempPayment) {

        btnDeletePaymentSubmit.addActionListener(e -> { // populate text fields

            try {

                populatePaymentTextFields(tempPayment, deletePaymentIDTextField, deletePaymentDateTextField,
                        deletePaymentCardNoTextField, deletePaymentValueTextField, deletePaymentLoanIDTextField);

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "INVALID INPUT");
                ex.printStackTrace();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "SQL ERROR");
                ex.printStackTrace();
            }

        });

        btnDeletePayment.addActionListener(e -> {

            try {

                if (!deletePaymentIDTextField.getText().isEmpty()) { // If payment ID entered

                    long payment_id = Long.parseLong(deletePaymentIDTextField.getText());

                    if (tempPayment.checkPaymentExists(payment_id)) { // If payment exists

                        int confirm = JOptionPane.showConfirmDialog(null,
                                String.format("ARE YOU SURE YOU WANT TO PAYMENT ID %d?", payment_id),
                                "DELETE PAYMENT", JOptionPane.YES_NO_OPTION);

                        if (confirm == JOptionPane.YES_OPTION) {
                            tempPayment.deletePayment(payment_id);
                            JOptionPane.showMessageDialog(null, "PAYMENT DELETED");
                            clearTextFields(deletePaymentTextFields);
                        } else {
                            JOptionPane.showMessageDialog(null, "PAYMENT NOT DELETED");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "INVALID PAYMENT ID");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "ENTER PAYMENT ID");
                }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "INVALID INPUT");
                ex.printStackTrace();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "SQL ERROR");
                ex.printStackTrace();
            }

        });

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

    private void handleCustomerModify(CustomerImplement tempCustomer) {

        btnModifyCustomerSubmit.addActionListener(e -> { // populate text fields

            try {

                populateCustomerTextFields(tempCustomer, modifyCustomerCardNoTextField, modifyCustomerFirstNameTextField,
                        modifyCustomerMiddleNameTextField, modifyCustomerLastNameTextField, modifyCustomerAadhaarTextField,
                        modifyCustomerHouseNoTextField, modifyCustomerStreetNameTextField, modifyCustomerCityTextField,
                        modifyCustomerStateTextField, modifyCustomerCountryTextField, modifyCustomerZipCodeTextField,
                        modifyCustomerPhoneTextField, modifyCustomerEmailTextField);

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "INVALID INPUT");
                ex.printStackTrace();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "SQL ERROR");
                ex.printStackTrace();
            }

        });

        btnModifyCustomerDetails.addActionListener(e -> {


            try {

                if (!modifyCustomerCardNoTextField.getText().isEmpty()) {

                    long card_no = Long.parseLong(modifyCustomerCardNoTextField.getText());

                    if (tempCustomer.checkCustomerExists(card_no)) {
                        boolean modified = ModifyPanels.modifyCustomerDetailsPrompt(tempCustomer, card_no);
                        if (modified) {
                            clearTextFields(modifyCustomerTextFields);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "INVALID CARD NUMBER");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "ENTER CARD NUMBER");
                }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "INVALID INPUT");
                ex.printStackTrace();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "SQL ERROR");
                ex.printStackTrace();
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(null, "INVALID INPUT");
                ex.printStackTrace();
            }

        });

        btnModifyCustomerAddressDetails.addActionListener(e -> {

            try {

                if (!modifyCustomerCardNoTextField.getText().isEmpty()) {

                    long card_no = Long.parseLong(modifyCustomerCardNoTextField.getText());

                    if (tempCustomer.checkCustomerExists(card_no)) {
                        boolean modified = ModifyPanels.modifyCustomerAddressDetailsPrompt(tempCustomer, card_no);
                        if (modified) {
                            clearTextFields(modifyCustomerTextFields);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "INVALID CARD NUMBER");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "ENTER CARD NUMBER");
                }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "INVALID INPUT");
                ex.printStackTrace();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "SQL ERROR");
                ex.printStackTrace();
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(null, "INVALID INPUT");
                ex.printStackTrace();
            }

        });

        btnModifyCustomerContactDetails.addActionListener(e -> {

            try {

                if (!modifyCustomerCardNoTextField.getText().isEmpty()) {

                    long card_no = Long.parseLong(modifyCustomerCardNoTextField.getText());

                    if (tempCustomer.checkCustomerExists(card_no)) {
                        boolean modified = ModifyPanels.modifyCustomerContactDetailsPrompt(tempCustomer, card_no);
                        if (modified) {
                            clearTextFields(modifyCustomerTextFields);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "INVALID CARD NUMBER");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "ENTER CARD NUMBER");
                }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "INVALID INPUT");
                ex.printStackTrace();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "SQL ERROR");
                ex.printStackTrace();
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(null, "INVALID INPUT");
                ex.printStackTrace();
            }

        });

    }

    private void handleCustomerDelete(CustomerImplement tempCustomer) {

        btnDeleteCustomerSubmit.addActionListener(e -> { // populate text fields

            try {

                populateCustomerTextFields(tempCustomer, deleteCustomerCardNoTextField, deleteCustomerFirstNameTextField,
                        deleteCustomerMiddleNameTextField, deleteCustomerLastNameTextField, deleteCustomerAadhaarTextField,
                        deleteCustomerHouseNoTextField, deleteCustomerStreetNameTextField, deleteCustomerCityTextField,
                        deleteCustomerStateTextField, deleteCustomerCountryTextField, deleteCustomerZipCodeTextField,
                        deleteCustomerPhoneTextField, deleteCustomerEmailTextField);

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "INVALID INPUT");
                ex.printStackTrace();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "SQL ERROR");
                ex.printStackTrace();
            }

        });

        btnDeleteCustomer.addActionListener(e -> {

            try {

                if (!deleteCustomerCardNoTextField.getText().isEmpty()) { // If card number entered

                    long card_no = Long.parseLong(deleteCustomerCardNoTextField.getText());

                    if (tempCustomer.checkCustomerExists(card_no)) { // If card number exists

                        int confirm = JOptionPane.showConfirmDialog(null,
                                String.format("ARE YOU SURE YOU WANT TO DELETE CUSTOMER WITH CARD NUMBER %d?", card_no),
                                "DELETE CUSTOMER", JOptionPane.YES_NO_OPTION);

                        if (confirm == JOptionPane.YES_OPTION) {
                            tempCustomer.deleteCustomer(card_no);
                            JOptionPane.showMessageDialog(null, "CUSTOMER DELETED");
                            clearTextFields(deleteCustomerTextFields);
                        } else {
                            JOptionPane.showMessageDialog(null, "CUSTOMER NOT DELETED");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "INVALID CARD NUMBER");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "ENTER CARD NUMBER");
                }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "INVALID INPUT");
                ex.printStackTrace();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "SQL ERROR");
                ex.printStackTrace();
            }

        });

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

    private void handleEmployeeModify(EmployeeImplement tempEmployee) {

        btnModifyEmployeeSubmit.addActionListener(e -> { // populate text fields

            try {

                populateEmployeeTextFields(tempEmployee, modifyEmployeeIDTextField, modifyEmployeeFirstNameTextField,
                        modifyEmployeeMiddleNameTextField, modifyEmployeeLastNameTextField, modifyEmployeeAadhaarTextField,
                        modifyEmployeePasswordTextField, modifyEmployeeHouseNoTextField, modifyEmployeeStreetNameTextField,
                        modifyEmployeeCityTextField, modifyEmployeeStateTextField, modifyEmployeeCountryTextField,
                        modifyEmployeeZipCodeTextField, modifyEmployeePhoneTextField, modifyEmployeeEmailTextField);

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "INVALID INPUT");
                ex.printStackTrace();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "SQL ERROR");
                ex.printStackTrace();
            }

        });

        btnModifyEmployeeDetails.addActionListener(e -> {

            try {

                if (!modifyEmployeeIDTextField.getText().isEmpty()) {

                    int employee_id = Integer.parseInt(modifyEmployeeIDTextField.getText());

                    if (tempEmployee.checkEmployeeExists(employee_id)) {
                        boolean modified = ModifyPanels.modifyEmployeeDetailsPrompt(tempEmployee, employee_id);
                        if (modified) {
                            clearTextFields(modifyEmployeeTextFields);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "INVALID EMPLOYEE ID");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "ENTER EMPLOYEE ID");
                }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "INVALID INPUT");
                ex.printStackTrace();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "SQL ERROR");
                ex.printStackTrace();
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(null, "INVALID INPUT");
                ex.printStackTrace();
            }

        });

        btnModifyEmployeeAddressDetails.addActionListener(e -> {

            try {

                if (!modifyEmployeeIDTextField.getText().isEmpty()) {

                    int employee_id = Integer.parseInt(modifyEmployeeIDTextField.getText());

                    if (tempEmployee.checkEmployeeExists(employee_id)) {
                        boolean modified = ModifyPanels.modifyEmployeeAddressDetailsPrompt(tempEmployee, employee_id);
                        if (modified) {
                            clearTextFields(modifyEmployeeTextFields);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "INVALID EMPLOYEE ID");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "ENTER EMPLOYEE ID");
                }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "INVALID INPUT");
                ex.printStackTrace();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "SQL ERROR");
                ex.printStackTrace();
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(null, "INVALID INPUT");
                ex.printStackTrace();
            }

        });

        btnModifyEmployeeContactDetails.addActionListener(e -> {

            try {

                if (!modifyEmployeeIDTextField.getText().isEmpty()) {

                    int employee_id = Integer.parseInt(modifyEmployeeIDTextField.getText());

                    if (tempEmployee.checkEmployeeExists(employee_id)) {
                        boolean modified = ModifyPanels.modifyEmployeeContactDetailsPrompt(tempEmployee, employee_id);
                        if (modified) {
                            clearTextFields(modifyEmployeeTextFields);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "INVALID EMPLOYEE ID");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "ENTER EMPLOYEE ID");
                }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "INVALID INPUT");
                ex.printStackTrace();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "SQL ERROR");
                ex.printStackTrace();
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(null, "INVALID INPUT");
                ex.printStackTrace();
            }

        });

    }

    private void handleEmployeeDelete(EmployeeImplement tempEmployee) {

        btnDeleteEmployeeSubmit.addActionListener(e -> { // populate text fields

                try {

                    populateEmployeeTextFields(tempEmployee, deleteEmployeeIDTextField, deleteEmployeeFirstNameTextField,
                            deleteEmployeeMiddleNameTextField, deleteEmployeeLastNameTextField, deleteEmployeeAadhaarTextField,
                            deleteEmployeePasswordTextField, deleteEmployeeHouseNoTextField, deleteEmployeeStreetNameTextField,
                            deleteEmployeeCityTextField, deleteEmployeeStateTextField, deleteEmployeeCountryTextField,
                            deleteEmployeeZipCodeTextField, deleteEmployeePhoneTextField, deleteEmployeeEmailTextField);

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "INVALID INPUT");
                    ex.printStackTrace();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "SQL ERROR");
                    ex.printStackTrace();
                }

        });

        btnDeleteEmployee.addActionListener(e -> {

            try {

                if (!deleteEmployeeIDTextField.getText().isEmpty()) { // If employee ID entered

                    int employee_id = Integer.parseInt(deleteEmployeeIDTextField.getText());

                    if (tempEmployee.checkEmployeeExists(employee_id)) { // If employee with ID exists

                        int confirm = JOptionPane.showConfirmDialog(null,
                                String.format("ARE YOU SURE YOU WANT TO DELETE EMPLOYEE WITH ID %d?", employee_id),
                                "DELETE EMPLOYEE", JOptionPane.YES_NO_OPTION);

                        if (confirm == JOptionPane.YES_OPTION) {
                            tempEmployee.deleteEmployee(employee_id);
                            JOptionPane.showMessageDialog(null, "EMPLOYEE DELETED");
                            clearTextFields(deleteEmployeeTextFields);
                        } else {
                            JOptionPane.showMessageDialog(null, "EMPLOYEE NOT DELETED");
                        }

                    } else {
                        JOptionPane.showMessageDialog(null, "INVALID EMPLOYEE ID");
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "ENTER EMPLOYEE ID");
                }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "INVALID INPUT");
                ex.printStackTrace();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "SQL ERROR");
                ex.printStackTrace();
            }

        });

    }




    // HELPER METHODS

    private void initializeComponents(){
        initializeTextFields();
    }

    private void initializeTextFields() {

        // VIEW PANELS

        addTextFieldsToArrayList(viewLoansPanel, viewLoansTextFields, viewPaymentsPanel, viewPaymentsTextFields,
                viewCustomersPanel, viewCustomersTextFields, viewEmployeesPanel, viewEmployeesTextFields);

        // ADD PANELS

        addTextFieldsToArrayList(addLoanPanel, addLoanTextFields, addPaymentPanel, addPaymentTextFields,
                addCustomerPanel, addCustomerTextFields, addEmployeePanel, addEmployeeTextFields);

        // MODIFY PANELS

        addTextFieldsToArrayList(modifyLoanPanel, modifyLoanTextFields, modifyPaymentPanel, modifyPaymentTextFields,
                modifyCustomerPanel, modifyCustomerTextFields, modifyEmployeePanel, modifyEmployeeTextFields);

        // DELETE PANELS

        addTextFieldsToArrayList(deleteLoanPanel, deleteLoanTextFields, deletePaymentPanel, deletePaymentTextFields,
                deleteCustomerPanel, deleteCustomerTextFields, deleteEmployeePanel, deleteEmployeeTextFields);

    }

    private void addTextFieldsToArrayList(JPanel loanPanel, ArrayList<JTextField> loanTextFields,
                                           JPanel paymentPanel, ArrayList<JTextField> paymentTextFields,
                                           JPanel customerPanel, ArrayList<JTextField> customerTextFields,
                                           JPanel employeePanel, ArrayList<JTextField> employeeTextFields) {

        for (Component c : loanPanel.getComponents()) {
            if (c instanceof JTextField) {
                loanTextFields.add((JTextField) c);
            }
        }

        for (Component c : paymentPanel.getComponents()) {
            if (c instanceof JTextField) {
                paymentTextFields.add((JTextField) c);
            }
        }

        for (Component c : customerPanel.getComponents()) {
            if (c instanceof JTextField) {
                customerTextFields.add((JTextField) c);
            }
        }

        for (Component c : employeePanel.getComponents()) {
            if (c instanceof JTextField || c instanceof JPasswordField) {
                employeeTextFields.add((JTextField) c);
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

    private void populateLoanTextFields(LoanImplement tempLoan, JTextField loanIDTextField,
                                        JTextField loanDateTextField, JTextField loanCardNoTextField,
                                        JTextField loanPrincipleTextField, JTextField loanInterestRateTextField,
                                        JTextField loanIsActiveTextField, JTextField loanAmtRemainingTextField,
                                        JTextField loanInterestAccruedTextField) throws SQLException {

        if (!loanIDTextField.getText().isEmpty()) { // If loan ID entered

            long loan_id = Long.parseLong(loanIDTextField.getText());

            if (tempLoan.checkLoanExists(loan_id)) { // If loan exists

                loanDateTextField.setText(tempLoan.getLoanDateString(loan_id));
                loanCardNoTextField.setText(tempLoan.getLoanCardNoString(loan_id));
                loanPrincipleTextField.setText(tempLoan.getLoanValueString(loan_id));
                loanInterestRateTextField.setText(tempLoan.getLoanInterestRateString(loan_id));
                loanIsActiveTextField.setText(tempLoan.getLoanIsActiveString(loan_id));
                loanAmtRemainingTextField.setText(tempLoan.getLoanAmtRemainingString(loan_id));
                loanInterestAccruedTextField.setText(tempLoan.getLoanInterestAccruedString(loan_id));

            } else {
                JOptionPane.showMessageDialog(null, "INVALID LOAN ID");
            }
        } else {
            JOptionPane.showMessageDialog(null, "ENTER LOAN ID");
        }

    }

    private void populatePaymentTextFields(PaymentImplement tempPayment,
                                           JTextField paymentIDTextField, JTextField paymentDateTextField,
                                           JTextField paymentCardNoTextField, JTextField paymentValueTextField,
                                           JTextField paymentLoanIDTextField) throws SQLException {

        if (!paymentIDTextField.getText().isEmpty()) { // If payment ID entered

            long payment_id = Long.parseLong(paymentIDTextField.getText());

            if (tempPayment.checkPaymentExists(payment_id)) { // If payment exists

                paymentDateTextField.setText(tempPayment.getPaymentDateString(payment_id));
                paymentCardNoTextField.setText(tempPayment.getPaymentCardNoString(payment_id));
                paymentValueTextField.setText(tempPayment.getPaymentValueString(payment_id));
                paymentLoanIDTextField.setText(tempPayment.getPaymentLoanIDString(payment_id));

            } else {
                JOptionPane.showMessageDialog(null, "INVALID PAYMENT ID");
            }
        } else {
            JOptionPane.showMessageDialog(null, "ENTER PAYMENT ID");
        }

    }

    private void populateCustomerTextFields(CustomerImplement tempCustomer,
                                            JTextField customerCardNoTextField, JTextField customerFirstNameTextField,
                                            JTextField customerMiddleNameTextField, JTextField customerLastNameTextField,
                                            JTextField customerAadhaarTextField, JTextField customerHouseNoTextField,
                                            JTextField customerStreetNameTextField, JTextField customerCityTextField,
                                            JTextField customerStateTextField, JTextField customerCountryTextField,
                                            JTextField customerZipCodeTextField, JTextField customerPhoneTextField,
                                            JTextField customerEmailTextField) throws SQLException {

        if (!customerCardNoTextField.getText().isEmpty()) { // If card number entered

            long card_no = Long.parseLong(customerCardNoTextField.getText());

            if (tempCustomer.checkCustomerExists(card_no)) { // If card number exists

                customerFirstNameTextField.setText(tempCustomer.getCustomerFirstNameString(card_no));
                customerMiddleNameTextField.setText(tempCustomer.getCustomerMiddleNameString(card_no));
                customerLastNameTextField.setText(tempCustomer.getCustomerLastNameString(card_no));
                customerAadhaarTextField.setText(tempCustomer.getCustomerAadhaarString(card_no));
                customerHouseNoTextField.setText(tempCustomer.getCustomerAddressMap(card_no).get("house_no"));
                customerStreetNameTextField.setText(tempCustomer.getCustomerAddressMap(card_no).get("street_name"));
                customerCityTextField.setText(tempCustomer.getCustomerAddressMap(card_no).get("city"));
                customerStateTextField.setText(tempCustomer.getCustomerAddressMap(card_no).get("state"));
                customerCountryTextField.setText(tempCustomer.getCustomerAddressMap(card_no).get("country"));
                customerZipCodeTextField.setText(tempCustomer.getCustomerAddressMap(card_no).get("zip_code"));
                customerPhoneTextField.setText(tempCustomer.getCustomerPhoneString(card_no));
                customerEmailTextField.setText(tempCustomer.getCustomerEmailString(card_no));

            } else {
                JOptionPane.showMessageDialog(null, "INVALID CARD NUMBER");
            }
        } else {
            JOptionPane.showMessageDialog(null, "ENTER CARD NUMBER");
        }

    }

    private void populateEmployeeTextFields(EmployeeImplement tempEmployee, JTextField employeeIDTextField,
                                            JTextField employeeFirstNameTextField, JTextField employeeMiddleNameTextField,
                                            JTextField employeeLastNameTextField, JTextField employeeAadhaarTextField,
                                            JPasswordField employeePasswordTextField, JTextField employeeHouseNoTextField,
                                            JTextField employeeStreetNameTextField, JTextField employeeCityTextField,
                                            JTextField employeeStateTextField, JTextField employeeCountryTextField,
                                            JTextField employeeZipCodeTextField, JTextField employeePhoneTextField,
                                            JTextField employeeEmailTextField) throws SQLException{

        if (!employeeIDTextField.getText().isEmpty()) { // If employee ID entered

            int employee_id = Integer.parseInt(employeeIDTextField.getText());

            if (tempEmployee.checkEmployeeExists(employee_id)) { // If employee with ID exists

                employeeFirstNameTextField.setText(tempEmployee.getEmployeeFirstNameString(employee_id));
                employeeMiddleNameTextField.setText(tempEmployee.getEmployeeMiddleNameString(employee_id));
                employeeLastNameTextField.setText(tempEmployee.getEmployeeLastNameString(employee_id));
                employeeAadhaarTextField.setText(tempEmployee.getEmployeeAadhaarString(employee_id));
                employeePasswordTextField.setText(tempEmployee.getEmployeePasswordString(employee_id));
                employeeHouseNoTextField.setText(tempEmployee.getEmployeeAddressMap(employee_id).get("house_no"));
                employeeStreetNameTextField.setText(tempEmployee.getEmployeeAddressMap(employee_id).get("street_name"));
                employeeCityTextField.setText(tempEmployee.getEmployeeAddressMap(employee_id).get("city"));
                employeeStateTextField.setText(tempEmployee.getEmployeeAddressMap(employee_id).get("state"));
                employeeCountryTextField.setText(tempEmployee.getEmployeeAddressMap(employee_id).get("country"));
                employeeZipCodeTextField.setText(tempEmployee.getEmployeeAddressMap(employee_id).get("zip_code"));
                employeePhoneTextField.setText(tempEmployee.getEmployeePhoneString(employee_id));
                employeeEmailTextField.setText(tempEmployee.getEmployeeEmailString(employee_id));

            } else {
                JOptionPane.showMessageDialog(null, "INVALID EMPLOYEE ID");
            }
        } else {
            JOptionPane.showMessageDialog(null, "ENTER EMPLOYEE ID");
        }

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

    private final ArrayList<JTextField> viewLoansTextFields = new ArrayList<>();
    private final ArrayList<JTextField> viewPaymentsTextFields = new ArrayList<>();
    private final ArrayList<JTextField> viewCustomersTextFields = new ArrayList<>();
    private final ArrayList<JTextField> viewEmployeesTextFields = new ArrayList<>();

    private final ArrayList<JTextField> addLoanTextFields = new ArrayList<>();
    private final ArrayList<JTextField> addPaymentTextFields = new ArrayList<>();
    private final ArrayList<JTextField> addCustomerTextFields = new ArrayList<>();
    private final ArrayList<JTextField> addEmployeeTextFields = new ArrayList<>();

    private final ArrayList<JTextField> modifyLoanTextFields = new ArrayList<>();
    private final ArrayList<JTextField> modifyPaymentTextFields = new ArrayList<>();
    private final ArrayList<JTextField> modifyCustomerTextFields = new ArrayList<>();
    private final ArrayList<JTextField> modifyEmployeeTextFields = new ArrayList<>();

    private final ArrayList<JTextField> deleteLoanTextFields = new ArrayList<>();
    private final ArrayList<JTextField> deletePaymentTextFields = new ArrayList<>();
    private final ArrayList<JTextField> deleteCustomerTextFields = new ArrayList<>();
    private final ArrayList<JTextField> deleteEmployeeTextFields = new ArrayList<>();

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
    private JPanel viewPaymentsPanel;
    private JPanel addPaymentPanel;
    private JPanel modifyPaymentPanel;
    private JPanel viewCustomersPanel;
    private JPanel addCustomerPanel;
    private JPanel viewEmployeesPanel;
    private JPanel addEmployeePanel;
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
    private JPanel deleteLoanPanel;
    private JPanel deletePaymentPanel;
    private JPanel deleteCustomerPanel;
    private JPanel deleteEmployeePanel;
    private JTextField deleteLoanIDTextField;
    private JTextField deleteLoanDateTextField;
    private JTextField deleteLoanCardNoTextField;
    private JTextField deleteLoanPrincipleTextField;
    private JTextField deleteLoanInterestRateTextField;
    private JTextField deleteLoanIsActiveTextField;
    private JTextField deleteLoanAmtRemainingTextField;
    private JTextField deleteLoanInterestAccruedTextField;
    private JButton btnDeleteLoanSubmit;
    private JTextField deletePaymentIDTextField;
    private JTextField deletePaymentDateTextField;
    private JTextField deletePaymentCardNoTextField;
    private JTextField deletePaymentValueTextField;
    private JTextField deletePaymentLoanIDTextField;
    private JButton btnDeletePaymentSubmit;
    private JTextField deleteCustomerCardNoTextField;
    private JTextField deleteCustomerFirstNameTextField;
    private JTextField deleteCustomerMiddleNameTextField;
    private JTextField deleteCustomerLastNameTextField;
    private JTextField deleteCustomerAadhaarTextField;
    private JTextField deleteCustomerHouseNoTextField;
    private JTextField deleteCustomerStreetNameTextField;
    private JTextField deleteCustomerCityTextField;
    private JTextField deleteCustomerStateTextField;
    private JTextField deleteCustomerCountryTextField;
    private JTextField deleteCustomerZipCodeTextField;
    private JTextField deleteCustomerPhoneTextField;
    private JTextField deleteCustomerEmailTextField;
    private JButton btnDeleteCustomerSubmit;
    private JTextField deleteEmployeeIDTextField;
    private JTextField deleteEmployeeFirstNameTextField;
    private JTextField deleteEmployeeMiddleNameTextField;
    private JTextField deleteEmployeeLastNameTextField;
    private JTextField deleteEmployeeAadhaarTextField;
    private JPasswordField deleteEmployeePasswordTextField;
    private JTextField deleteEmployeeHouseNoTextField;
    private JTextField deleteEmployeeStreetNameTextField;
    private JTextField deleteEmployeeCityTextField;
    private JTextField deleteEmployeeStateTextField;
    private JTextField deleteEmployeeCountryTextField;
    private JTextField deleteEmployeeZipCodeTextField;
    private JTextField deleteEmployeePhoneTextField;
    private JTextField deleteEmployeeEmailTextField;
    private JButton btnDeleteEmployeeSubmit;
    private JButton btnDeleteLoan;
    private JButton btnDeletePayment;
    private JButton btnDeleteCustomer;
    private JButton btnDeleteEmployee;

}

