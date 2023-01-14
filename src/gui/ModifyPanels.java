package gui;

import javax.swing.*;
import java.sql.Timestamp;

public class ModifyPanels {

    // MODIFY LOAN METHODS

    public static Timestamp modifyLoanDatePanel() {

        Timestamp newLoanDate = null;

        JTextField newLoanDateTextField = new JTextField(10);
        JPanel newPanel = new JPanel();

        newPanel.add(new JLabel("ENTER NEW LOAN DATE:"));
        newPanel.add(Box.createHorizontalStrut(15));
        newPanel.add(newLoanDateTextField);

        int result = JOptionPane.showConfirmDialog(null, newPanel,
                "MODIFY LOAN DATE", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            newLoanDate = Timestamp.valueOf(newLoanDateTextField.getText() + " 00:00:00");
        }

        return newLoanDate;

    }

    public static long modifyLoanCardNoPanel() {

        long newLoanCardNo = 0L;

        JTextField newLoanCardNoTextField = new JTextField(10);
        JPanel newPanel = new JPanel();

        newPanel.add(new JLabel("ENTER NEW LOAN CARD NUMBER: "));
        newPanel.add(Box.createHorizontalStrut(15));
        newPanel.add(newLoanCardNoTextField);

        int result = JOptionPane.showConfirmDialog(null, newPanel,
                "MODIFY LOAN CARD NO", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            newLoanCardNo = Long.parseLong(newLoanCardNoTextField.getText());
        }

        return newLoanCardNo;

    }

    public static float modifyLoanPrinciplePanel() {

        float newLoanPrinciple = 0.00f;

        JTextField newLoanPrincipleTextField = new JTextField(10);
        JPanel newPanel = new JPanel();

        newPanel.add(new JLabel("ENTER NEW LOAN PRINCIPLE: ₹"));
        newPanel.add(Box.createHorizontalStrut(15));
        newPanel.add(newLoanPrincipleTextField);

        int result = JOptionPane.showConfirmDialog(null, newPanel,
                "MODIFY LOAN PRINCIPLE", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            newLoanPrinciple = Float.parseFloat(newLoanPrincipleTextField.getText());
        }

        return newLoanPrinciple;

    }

    public static float modifyLoanInterestRatePanel() {

        float newLoanInterestRate = 0.00f;

        JTextField newLoanInterestRateTextField = new JTextField(10);
        JPanel newPanel = new JPanel();

        newPanel.add(new JLabel("ENTER NEW LOAN INTEREST RATE: "));
        newPanel.add(Box.createHorizontalStrut(15));
        newPanel.add(newLoanInterestRateTextField);

        int result = JOptionPane.showConfirmDialog(null, newPanel,
                "MODIFY LOAN INTEREST RATE", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            newLoanInterestRate = Float.parseFloat(newLoanInterestRateTextField.getText());
        }

        return newLoanInterestRate;

    }

    public static boolean modifyLoanIsActivePanel() {

        boolean newLoanIsActive = false;

        JComboBox newLoanIsActiveComboBox = new JComboBox();
        newLoanIsActiveComboBox.addItem("ACTIVE");
        newLoanIsActiveComboBox.addItem("INACTIVE");
        JPanel newPanel = new JPanel();

        newPanel.add(new JLabel("ENTER NEW LOAN ACTIVE STATUS: "));
        newPanel.add(Box.createHorizontalStrut(15));
        newPanel.add(newLoanIsActiveComboBox);

        int result = JOptionPane.showConfirmDialog(null, newPanel,
                "MODIFY LOAN ACTIVE STATUS", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            newLoanIsActive = newLoanIsActiveComboBox.getSelectedItem().equals("ACTIVE");
        }

        return newLoanIsActive;

    }

}
