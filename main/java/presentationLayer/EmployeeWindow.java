package presentationLayer;

import businessLayer.DataProcessing;
import businessLayer.GenerateReports;
import dataAccessLayer.EmployeeDAO;
import dataClasses.Employee;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;

public class EmployeeWindow {

    public static JFrame frame;
    public static DefaultTableModel model;
    public JScrollPane scrollPane, jScrollPane1;
    public JTable tabel;
    public JPanel panel, panel2;
    public JTextField pnc, user, pass, address;
    public JLabel n, c, p, a, act;
    public JButton add, update, delete, view, reset, back;
    public JTextArea history;

    public static void main(String[] args) throws SQLException {

        new EmployeeWindow();
    }

    public static JTable makeTable(ArrayList<Employee> list) {

        ArrayList<String> column = new ArrayList<String>();
        column.add("PNC");
        column.add("USERNAME");
        column.add("PASSWORD");
        column.add("ADDRESS");

        // DefaultTableModel
        model = new DefaultTableModel();
        model.setColumnIdentifiers(column.toArray());

        Object[] v = new Object[4];
        JTable table = new JTable();

        for (Employee c : list) {
            v[0] = c.getPncEmployee();
            v[1] = c.getUsername();
            v[2] = c.getPassword();
            v[3] = c.getAddress();
            model.addRow(v);
        }
        table.setModel(model);

        JTableHeader header = table.getTableHeader();
        TableColumnModel col = header.getColumnModel();
        for (int i = 0; i < column.size(); i++) {
            TableColumn tc = col.getColumn(i);
            tc.setHeaderValue(column.get(i));
        }

        Font font = new Font("Times New Roman", 1, 22);
        table.setFont(font);
        table.setRowHeight(40);

        for (int i = 1; i < table.getColumnCount(); i++)
            table.getColumnModel().getColumn(i).setMinWidth(150);

        return table;

    }

    public JLabel makeLabel(String title){

        JLabel label = new JLabel(title);
        label.setFont(new Font("Times New Roman", Font.ITALIC, 25));

        return label;
    }

    public JButton makeButton(String title){

        JButton button = new JButton(title);
        button.setForeground(Color.white);
        button.setBackground(Color.darkGray);
        button.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));

        return button;
    }

    public void initTextFields(){

        pnc = new JTextField(20);
        pnc.setBounds(170, 55, 270, 30);
        pnc.setColumns(10);

        user = new JTextField(20);
        user.setBounds(170, 125, 270, 30);
        user.setColumns(10);

        pass = new JTextField(20);
        pass.setBounds(170, 195, 270, 30);
        pass.setColumns(10);

        address = new JTextField(20);
        address.setBounds(170, 265, 270, 30);
        address.setColumns(10);

        history = new JTextArea();
        history.setColumns(10);
        history.setTabSize(100);
        history.setBounds(80, 80, 100, 100);
    }

    public void initButtons(){

        add = makeButton("Add");
        add.setBounds(90, 350, 100, 40);

        update = makeButton("Update");
        update.setBounds(90, 450, 100, 40);

        delete = makeButton("Delete");
        delete.setBounds(90, 550, 100, 40);

        view = makeButton("View");
        view.setBounds(270, 350, 100, 40);

        back = makeButton("Back");
        back.setBounds(270, 450, 100, 40);

        reset = makeButton("Reset");
        reset.setBounds(270, 550, 100, 40);
    }

    public void initLabels(){

        n = makeLabel("PNC: ");
        n.setBounds(45, 50, 500, 40);

        c = makeLabel("USERNAME: ");
        c.setBounds(25, 120, 500, 40);

        p = makeLabel("PASSWORD: ");
        p.setBounds(30, 190, 500, 40);

        a = makeLabel("ADDRESS: ");
        a.setBounds(25, 260, 500, 40);

        act = makeLabel("Employees' activity");
        act.setBounds(0, 600, 100, 100);
        act.setBackground(Color.pink);
    }

    public void processTable(){

        scrollPane = new JScrollPane();
        scrollPane.setBounds(500, 40, 900, 500);
        tabel.setBounds(70, 40, 700, 500);
        tabel.setVisible(true);

        scrollPane.setViewportView(tabel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    }

    public void initPanel(){

        panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(0, 0, 1500, 600);
        panel.setBackground(Color.darkGray);

        history= new JTextArea(8,30);
        history.append("\n");
        for(int i=0; i<GenerateReports.reports.size(); i++){
            history.append(GenerateReports.reports.get(i));
        }

        jScrollPane1 = new JScrollPane(history);
        jScrollPane1.setBounds(0, 600, 1500, 400);//
        jScrollPane1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        jScrollPane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        panel2 = new JPanel();
        panel2.setLayout(null);
        panel2.setBounds(0, 350, 1400, 900);
        panel2.setBackground(Color.yellow);
        panel2.add(jScrollPane1);

        panel2.add(panel);
        jScrollPane1.revalidate();

        initButtons();
        initLabels();
        initTextFields();

        panel.add(add);
        panel.add(update);
        panel.add(delete);
        panel.add(view);
        panel.add(back);
        panel.add(reset);
        panel.add(user);
        panel.add(pass);
        panel.add(pnc);
        panel.add(address);
        panel.add(n);
        panel.add(c);
        panel.add(p);
        panel.add(a);
        panel.setBackground(Color.lightGray);

        ArrayList<Employee> l =  new ArrayList<Employee>();
        tabel = makeTable(l);
        processTable();
        panel.add(scrollPane);
    }

    public void initFrame(){

        frame = new JFrame();
        frame.setDefaultCloseOperation(3);
        frame.setSize(new Dimension(1500, 1000));

        //frame.add(panel);
        frame.add(panel2);
        frame.setVisible(true);

    }

    public void initPage(){

        initPanel();
        initFrame();
    }

    public EmployeeWindow() throws SQLException {

        initPage();

        add.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent et) {

                ArrayList<Employee> list = new ArrayList<Employee>();

                int pncE = Integer.parseInt(pnc.getText());
                String userE = user.getText();
                String passE = pass.getText();
                String addressE = address.getText();

                Employee ne = new Employee(pncE, userE, passE, addressE);
                list.add(ne);

                DataProcessing dp = new DataProcessing();
                dp.addEmployee(pncE, userE, passE, addressE);

                tabel=makeTable(list);
                processTable();
                panel.add(scrollPane);
                frame.setVisible(true);

            }
        });

        update.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent et) {

                ArrayList<Employee> list = new ArrayList<Employee>();
                int pncC = Integer.parseInt(pnc.getText());

                String newName = user.getText();
                String newAddress = address.getText();

                DataProcessing dp = new DataProcessing();
                dp.updateEmployee(pncC, newName, newAddress);

                tabel=makeTable(list);
                processTable();
                panel.add(scrollPane);
                frame.setVisible(true);

            }
        });

        delete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent et) {

                ArrayList<Employee> list = new ArrayList<Employee>();
                int pncC = Integer.parseInt(pnc.getText());

                Employee c1 = null;
                DataProcessing dp = new DataProcessing();
                dp.deleteEmployee(pncC, c1);
                list.remove(c1);
                tabel=makeTable(list);
                processTable();
                panel.add(scrollPane);
                frame.setVisible(true);
            }
        });

        view.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent et) {

                ArrayList<Employee> list = new ArrayList<Employee>();
                list = EmployeeDAO.viewAllEmployees();

                tabel=makeTable(list);
                processTable();
                panel.add(scrollPane);
                frame.setVisible(true);
            }
        });

        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent et) {

                try {
                    LoginPage.main(null);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                frame.setVisible(false);

            }
        });

        reset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent et) {

                pnc.setText("");
                user.setText("");
                pass.setText("");
                address.setText("");

                frame.setVisible(true);
            }
        });


    }

}

