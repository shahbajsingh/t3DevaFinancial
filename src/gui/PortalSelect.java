package gui;

import javax.swing.*;
import java.awt.*;

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

        this.setPreferredSize(new Dimension(600, 400));
        this.setContentPane(portalSelectPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);

    }




    // COMPONENT DECLARATION

    private JPanel portalSelectPanel;
    private JButton btnLogOut;
    private JTabbedPane portalsTabbedPane;
    private JPanel customerPanel;
    private JPanel employeePanel;
    private JPanel loanPanel;
    private JPanel paymentPanel;

}

