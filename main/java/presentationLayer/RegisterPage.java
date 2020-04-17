package presentationLayer;

import businessLayer.Register;
import dataClasses.Employee;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.*;

public class RegisterPage {
    public static JFrame frame;
    public JPanel panel;
    public JTextField pncText, userText, addressText;
    public JPasswordField passwordField, confirmPasswordField;
    public JLabel titleRegister, pncLabel, userLabel, passLabel, confirmPassLabel, addressLabel;
    public JButton registerButton, loginButton;

    public static void main(String[] args) throws SQLException {
        new RegisterPage();

    }

    public JLabel makeLabel(String title){

        JLabel label = new JLabel(title);
        label.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 23));
        label.setForeground(Color.white);
        return label;
    }

    public JButton makeButton(String title){

        JButton button = new JButton(title);
        button.setFont(new Font("Times New Roman", 1, 20));
        button.setBackground(Color.white);

        return button;
    }

    public void initPanel(){

        panel = new JPanel();
        panel.setLayout((LayoutManager)null);
        panel.setBounds(26, 195, 500, 500);
        panel.add(titleRegister);
        panel.add(pncLabel);
        panel.add(userLabel);
        panel.add(passLabel);
        panel.add(confirmPassLabel);
        panel.add(addressLabel);
        panel.add(pncText);
        panel.add(userText);
        panel.add(passwordField);
        panel.add(confirmPasswordField);
        panel.add(addressText);
        panel.add(registerButton);
        panel.add(loginButton);
        panel.setBackground(Color.black);
    }

    public void initFrame(){

        frame = new JFrame();
        frame.setDefaultCloseOperation(3);
        frame.setSize(new Dimension(600, 550));
        frame.add(panel);
        frame.setVisible(true);
    }

    public void initLabels(){

        titleRegister = makeLabel("Banking   App");
        titleRegister.setBounds(210, 10, 500, 40);
        titleRegister.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 33));

        pncLabel = makeLabel(" PNC");
        pncLabel.setBounds(60, 100, 100, 40);

        userLabel = makeLabel("Username");
        userLabel.setBounds(60, 150, 100, 40);

        passLabel = makeLabel("Password");
        passLabel.setBounds(60, 200, 100, 40);

        confirmPassLabel = makeLabel("Confirm Password");
        confirmPassLabel.setBounds(60, 250, 300, 40);

        addressLabel = makeLabel("Address");
        addressLabel.setBounds(60, 300, 100, 40);
    }

    public void initButtons(){

        registerButton = makeButton("Register");
        registerButton.setBounds(180, 360, 200, 30);

        loginButton = makeButton("<<Already have an account? Login!>>");
        loginButton.setBounds(95, 410, 370, 30);
    }

    public void initTextFields(){

        pncText = new JTextField(10);
        pncText.setBounds(260, 110, 200, 25);
        pncText.setColumns(10);

        userText = new JTextField(10);
        userText.setBounds(260, 160, 200, 25);
        userText.setColumns(10);

        passwordField = new JPasswordField(10);
        passwordField.setBounds(260, 210, 200, 25);

        confirmPasswordField = new JPasswordField(10);
        confirmPasswordField.setBounds(260, 260, 200, 25);

        addressText = new JTextField(10);
        addressText.setBounds(260, 310, 200, 25);
        addressText.setColumns(10);
    }

    public void initPage(){

        initLabels();
        initButtons();
        initTextFields();
        initPanel();
        initFrame();
    }


    public RegisterPage() {

        initPage();

        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
            }
        });

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new LoginPage();
            }
        });

        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                ArrayList<Employee> list = new ArrayList<Employee>();

                int pncE = Integer.parseInt(pncText.getText());
                String userE = userText.getText();
                String passE = passwordField.getText();
                String confirmE = confirmPasswordField.getText();
                String addressE = addressText.getText();

                Register.registerAction(userE, pncE, passE, confirmE, addressE);

            }
        });
    }

}
