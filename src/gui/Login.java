package gui;

import module.EmployeeImplement;

import javax.swing.*;
import java.sql.SQLException;

public class Login extends JFrame {

    public Login() {

        super("Login");

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

        handleLogin();

        this.setContentPane(loginPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);

    }

    public static void main(String[] args){
        Login login = new Login();
    }

    private void handleLogin(){

        btnLogIn.addActionListener(e -> {

            int username = getUsername();
            String password = getPassword();

            try {
                if (checkEmployeeExists(username)) {
                    if (checkPassword(username, password)){
                        PortalSelect portalSelect = new PortalSelect();
                        this.dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "Login Failed");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Employee does not exist");
                }

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });
    }




    // GETTERS
    // These methods return the value of the
    // specified text field from the GUI
    // parsed in the appropriate data type

    public int getUsername() {
        int username = 0;
        try { // try to parse the username to an int
            username = Integer.parseInt(usernameTextField.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Please enter a valid username");
        }
        return username;
    }

    public String getPassword() {
        return String.valueOf(passwordTextField.getPassword());
    }




    // HELPER METHODS

    private boolean checkEmployeeExists(int employee_id) throws SQLException {
        EmployeeImplement employeeImplement = new EmployeeImplement();
        return employeeImplement.checkEmployeeExists(employee_id);
    }

    private boolean checkPassword(int employee_id, String password) throws SQLException {
        EmployeeImplement tempEmployee = new EmployeeImplement();
        return tempEmployee.checkEmployeePassword(employee_id, password);
    }




    // COMPONENT DECLARATION

    private JPasswordField passwordTextField;
    private JTextField usernameTextField;
    private JButton btnLogIn;
    private JPanel loginPanel;

}
