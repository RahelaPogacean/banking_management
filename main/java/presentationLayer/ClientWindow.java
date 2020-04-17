package presentationLayer;

import businessLayer.DataProcessing;
import businessLayer.GenerateReports;
import businessLayer.Login;
import dataAccessLayer.ClientDAO;
import dataClasses.Client;
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

public class ClientWindow {

    private UserTasks userTasks;
    public static JFrame frame;
    public static DefaultTableModel model;
    public JScrollPane scrollPane;
    public JTable tabel;
    public JPanel panel;
    public JLabel n, c, p, a;
    public  JButton add, update, delete, view, withdraw, back, reset;
    JTextField name, card, pnc, address;


    public static JTable makeTable(ArrayList<Client> list) {

        ArrayList<String> column = new ArrayList<String>();
        column.add("Name");
        column.add("Card Number");
        column.add("PNC");
        column.add("Address");

        // DefaultTableModel
        model = new DefaultTableModel();
        model.setColumnIdentifiers(column.toArray());

        Object[] v = new Object[4];
        JTable table = new JTable();

        for (Client c : list) {
            v[0] = c.getNameClient();
            v[1] = c.getCardNo();
            v[2] = c.getPncClient();
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

    public void processTable(){

        scrollPane = new JScrollPane();
        scrollPane.setBounds(500, 40, 900, 500);
        tabel.setBounds(70, 40, 700, 500);
        tabel.setVisible(true);

        scrollPane.setViewportView(tabel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    }

    public void initTextFields(){

        name = new JTextField(20);
        name.setBounds(160, 55, 270, 30);
        name.setColumns(10);

        card = new JTextField(20);
        card.setBounds(160, 125, 270, 30);
        card.setColumns(10);

        pnc = new JTextField(20);
        pnc.setBounds(160, 195, 270, 30);
        pnc.setColumns(10);

        address = new JTextField(20);
        address.setBounds(160, 265, 270, 30);
        address.setColumns(10);
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

        n = makeLabel("NAME: ");
        n.setBounds(45, 50, 500, 40);

        c = makeLabel("CARD NO: ");
        c.setBounds(25, 120, 500, 40);

        p = makeLabel("PNC: ");
        p.setBounds(45, 190, 500, 40);

        a = makeLabel("ADDRESS: ");
        a.setBounds(25, 260, 500, 40);
    }

    public void initPanel(){

        panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(26, 105, 300, 315);
        panel.setBackground(Color.lightGray);

        initButtons();
        initLabels();
        initTextFields();

        panel.add(add);
        panel.add(update);
        panel.add(delete);
        panel.add(view);
        panel.add(back);
        panel.add(reset);
        panel.add(name);
        panel.add(card);
        panel.add(pnc);
        panel.add(address);
        panel.add(n);
        panel.add(c);
        panel.add(p);
        panel.add(a);


        ArrayList<Client> list =  new ArrayList<Client>();
        tabel = makeTable(list);
        processTable();
        panel.add(scrollPane);

    }

    public void initFrame(){
        frame = new JFrame();
        frame.setDefaultCloseOperation(3);
        frame.setSize(new Dimension(1500, 700));
        frame.add(panel);
        frame.setVisible(true);
    }

    public void initPage(){

        initPanel();
        initFrame();
    }

    public ClientWindow() throws SQLException {

        initPage();


        add.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent et) {

                ArrayList<Client> list = new ArrayList<Client>();

                String nameC = name.getText();
                int cardC = Integer.parseInt(card.getText());
                int pncC = Integer.parseInt(pnc.getText());
                String addressC = address.getText();

                Client cl = new Client(nameC, cardC, pncC, addressC);
                list.add(cl);//update table with new client

               DataProcessing dp = new DataProcessing();
               dp.addClient(nameC, cardC, pncC, addressC);

                Date thisDate = new Date(System.currentTimeMillis());
                String message = "The employee with PNC " + Login.pncCurrentEmployee + " added a new client with PNC " + pncC + " , " + thisDate + " .\n";
                GenerateReports.reports.add(message);

                tabel=makeTable(list);
                processTable();
                panel.add(scrollPane);
                frame.setVisible(true);
            }
        });

        update.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent et) {

                ArrayList<Client> list = new ArrayList<Client>();
                int pncC = Integer.parseInt(pnc.getText());

                String newName = name.getText();
                int newCard = Integer.parseInt(card.getText());
                String newAddress = address.getText();

                DataProcessing dp = new DataProcessing();
                dp.updateClient(pncC, newName, newCard, newAddress);

                Date thisDate = new Date(System.currentTimeMillis());
                String message = "The employee with PNC " + Login.pncCurrentEmployee + " updated a client with pnc " + pncC + " , " + thisDate + " .\n";
                GenerateReports.reports.add(message);

                tabel=makeTable(list);
                processTable();

                panel.add(scrollPane);
                frame.setVisible(true);
            }
        });

        delete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent et) {

                ArrayList<Client> list = new ArrayList<Client>();
                int pncC = Integer.parseInt(pnc.getText());

                Client c1 = null;
                DataProcessing dp = new DataProcessing();
                dp.deleteClient(pncC, c1);

                Date thisDate = new Date(System.currentTimeMillis());
                String message = "The employee with PNC " + Login.pncCurrentEmployee + " deleted a client with pnc " + pncC + " , " + thisDate + " .\n";
                GenerateReports.reports.add(message);

                list.remove(c1);
                tabel=makeTable(list);

                processTable();
                panel.add(scrollPane);
                frame.setVisible(true);
            }
        });

        view.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent et) {

                ArrayList<Client> list = new ArrayList<Client>();
                list = ClientDAO.viewAll();

                tabel=makeTable(list);
                processTable();
                panel.add(scrollPane);
                frame.setVisible(true);
            }
        });

        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent et) {

                try {
                    userTasks.main(null);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                frame.setVisible(false);
            }
        });

        reset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent et) {

                name.setText("");
                card.setText("");
                pnc.setText("");
                address.setText("");

                frame.setVisible(true);
            }
        });


    }

}
