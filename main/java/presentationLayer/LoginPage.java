package presentationLayer;

import businessLayer.GenerateReports;
import businessLayer.Login;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import javax.swing.*;

public class LoginPage {

    public static JFrame frame;
    public JPanel panel;
    public JLabel titleLogin, userLabel, passLabel;
    public JButton adminButton, userButton, registerButton;
    public JTextField userText;
    public JPasswordField passText;

    public static void main(String[] args) throws SQLException {
        new LoginPage();
    }

    public JButton makeButton(String title){

        JButton thisButton = new JButton(title);
        thisButton.setFont(new Font("Times New Roman", 1, 20));
        thisButton.setBackground(Color.white);

        return thisButton;
    }

    public JLabel makeLabel(String title){

        JLabel label = new JLabel(title);
        label.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 23));
        label.setForeground(Color.white);

        return label;
    }

    public void initFrame(){

        frame = new JFrame();
        frame.setDefaultCloseOperation(3);
        frame.setSize(new Dimension(600, 550));
        frame.add(panel);
        frame.setVisible(true);
    }
    public void initPanel(){

        panel = new JPanel();
        panel.setLayout((LayoutManager)null);
        panel.setBounds(26, 195, 500, 500);
        panel.setBackground(Color.black);

        panel.add(titleLogin);
        panel.add(userLabel);
        panel.add(passLabel);
        panel.add(userText);
        panel.add(passText);
        panel.add(registerButton);
        panel.add(adminButton);
        panel.add(userButton);
    }

    public void initButtons(){

        adminButton = makeButton("Administrator");
        adminButton.setBounds(70, 130, 180, 40);

        userButton = makeButton("User");
        userButton.setBounds(310, 130, 180, 40);

        registerButton = makeButton("<<No account? Create one!>>");
        registerButton.setBounds(80, 380, 400, 30);
    }

    public void initLabels(){

        titleLogin = makeLabel("Banking  App");
        titleLogin.setBounds(190, 10, 500, 40);
        titleLogin.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 33));

        userLabel = makeLabel("username");
        userLabel.setBounds(90, 210, 100, 40);

        passLabel = makeLabel("password");
        passLabel.setBounds(90, 300, 100, 40);
    }

    public void initTextFields(){

        userText = new JTextField(10);
        userText.setBounds(230, 220, 200, 25);
        userText.setColumns(10);

        passText = new JPasswordField(10);
        passText.setBounds(230, 310, 200, 25);
        passText.setColumns(10);
    }

    public void initPage(){

        initButtons();
        initLabels();
        initTextFields();
        initPanel();
        initFrame();
    }

    public LoginPage() {

        initPage();

        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new RegisterPage();
            }
        });

        userButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String name = userText.getText();
                String pass = passText.getText();

                Login.userLoginAction(name, pass);
            }

        });

        adminButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String name = userText.getText();
                String pass = passText.getText();

                try {
                    Login.adminLoginAction(name, pass);
                    GenerateReports g = new GenerateReports();
                    g.createListOfOperations();

                } catch (SQLException ex) {
                    ex.printStackTrace();
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                } catch (UnsupportedEncodingException ex) {
                    ex.printStackTrace();
                }
            }
        });

        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
            }
        });
    }
}
