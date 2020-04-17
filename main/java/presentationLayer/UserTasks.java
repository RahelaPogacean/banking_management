package presentationLayer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class UserTasks {

    public LoginPage login;
    public JFrame frame;
    JLabel page;
    JButton cl, ac, back;
    public JPanel panel;

    public static void main(String[] args) throws SQLException {
        new UserTasks();
    }

    public JButton makeButton(String title){

        JButton button = new JButton(title);
        button.setFont(new Font("Times New Roman", 1, 20));
        button.setBackground(Color.white);

        return button;
    }

    public void initLabel(){

        page = new JLabel("The current page is assigned \n" +
                "  to regular users.");
        page.setBounds(50, 40, 500, 100);
        page.setFont(new Font("Times New Roman", Font.ITALIC, 24));
        page.setForeground(Color.white);
    }

    public void initButtons(){

        cl = makeButton("Clients");
        cl.setBounds(70, 190, 200, 40);

        ac = makeButton("Accounts");
        ac.setBounds(300, 190, 200, 40);

        back = makeButton("Back");
        back.setBounds(200, 290, 180, 40);
    }

    public void initPanel(){

        panel = new JPanel();
        panel.setLayout((LayoutManager)null);
        panel.setBounds(26, 195, 500, 500);

        panel.add(cl);
        panel.add(ac);
        panel.add(back);
        panel.add(page);
        panel.setBackground(Color.black);
    }

    public void initFrame(){

        frame = new JFrame();
        frame.setDefaultCloseOperation(3);
        frame.setSize(new Dimension(600, 550));

        frame.add(panel);
        frame.setVisible(true);
    }

    public void initPage(){

        initButtons();
        initLabel();
        initPanel();
        initFrame();
    }

    public UserTasks(){

        initPage();

        cl.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new ClientWindow();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });

        ac.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new AccountWindow();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });

        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent et) {

                try {
                    login.main(null);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                frame.setVisible(false);

            }
        });

    }
}
