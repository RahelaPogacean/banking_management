package presentationLayer;

import businessLayer.DataProcessing;
import businessLayer.GenerateReports;
import businessLayer.Login;
import businessLayer.BankingOperations;
import dataAccessLayer.AccountDAO;
import dataClasses.Account;
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

public class AccountWindow {

    private UserTasks userTasks;
    public static JFrame frame;
    public static DefaultTableModel model;
    public JScrollPane scrollPane;
    public JTable tabel;
    public JPanel panel;
    public JTextField id, id2, pnc, type, amount, date, summ, bill;
    public JLabel n, c, p, a, d, s;
    public  JButton add, update, delete, view, withdraw, deposit, back, reset, pay;

    public AccountDAO aa1, aa2 = null;

    public static JTable makeTable(ArrayList<Account> list) {

        ArrayList<String> column = new ArrayList<String>();
        column.add("ID Account");
        column.add("PNC Client's Account");
        column.add("Type");
        column.add("Amount");
        column.add("Date creation");

        // DefaultTableModel
        model = new DefaultTableModel();
        model.setColumnIdentifiers(column.toArray());

        Object[] v = new Object[5];
        JTable table = new JTable();

        for (Account c : list) {
            v[0] = c.getIdAccount();
            v[1] = c.getPncClient();
            v[2] = c.getTypeAccount();
            v[3] = c.getAmount();
            v[4] = c.getDateCreation();
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

        id = new JTextField(20);
        id.setBounds(200, 20, 100, 40);
        id.setColumns(10);

        id2 = new JTextField(20);
        id2.setBounds(320, 20, 100, 40);
        id2.setColumns(10);

        pnc = new JTextField(20);
        pnc.setBounds(200, 90, 270, 30);
        pnc.setColumns(10);

        type = new JTextField(20);
        type.setBounds(200, 150, 270, 30);
        type.setColumns(10);

        amount = new JTextField(20);
        amount.setBounds(200, 220, 270, 30);
        amount.setColumns(10);

        date = new JTextField(20);
        date.setBounds(200, 290, 270, 30);
        date.setColumns(10);

        summ = new JTextField(20);
        summ.setBounds(650, 550, 100, 40);
        summ.setColumns(10);

        bill = new JTextField(20);
        bill.setBounds(1000, 550, 100, 40);
        bill.setColumns(10);
    }

    public void initLabels(){

        n = makeLabel("ID ACCOUNT: ");
        n.setBounds(25, 20, 500, 40);

        c = makeLabel("PNC CLIENT: ");
        c.setBounds(25, 80, 500, 40);

        p = makeLabel("TYPE: ");
        p.setBounds(45, 150, 500, 40);

        a = makeLabel("AMOUNT: ");
        a.setBounds(30, 220, 500, 40);

        d = makeLabel("DATE: ");
        d.setBounds(45, 280, 500, 40);

        s = makeLabel("SUM: ");
        s.setBounds(45, 350, 500, 40);
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

        withdraw = makeButton("Withdraw");
        withdraw.setBounds(450, 550, 100, 40);

        deposit = makeButton("Deposit");
        deposit.setBounds(450, 600, 100, 40);

        pay = makeButton("Pay Bills");
        pay.setBounds(850, 550, 100, 40);
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
        panel.add(withdraw);
        panel.add(deposit);
        panel.add(id);
        panel.add(id2);
        panel.add(pnc);
        panel.add(type);
        panel.add(amount);
        panel.add(date);
        panel.add(summ);
        panel.add(bill);
        panel.add(n);
        panel.add(c);
        panel.add(p);
        panel.add(a);
        panel.add(d);
        panel.add(pay);

        ArrayList<Account> list =  new ArrayList<Account>();
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

    public AccountWindow() throws SQLException {

        initPage();

        withdraw.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int c1 = Integer.parseInt(id.getText());
                int c2 = Integer.parseInt(id2.getText());
                double sum = Double.parseDouble(summ.getText());

                BankingOperations b = new BankingOperations();
                b.withdraw(c1, c2, sum);

                Date thisDate = new Date(System.currentTimeMillis());
                String message = "The employee with PNC " + Login.pncCurrentEmployee + " made a withdrawal "  + " , " + thisDate + " .\n";
                GenerateReports.reports.add(message);

            }
        });

        deposit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int c1 = Integer.parseInt(id.getText());
                int c2 = Integer.parseInt(id2.getText());
                double sum = Double.parseDouble(summ.getText());

                BankingOperations b = new BankingOperations();
                b.deposit(c1, c2, sum);

                Date thisDate = new Date(System.currentTimeMillis());
                String message = "The employee with PNC " + Login.pncCurrentEmployee + " made a deposit " + " , " + thisDate + " .\n";
                GenerateReports.reports.add(message);//
            }
        });

        pay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int idAc = Integer.parseInt(id.getText());
                double sum = Double.parseDouble(summ.getText());
                String typeBill = bill.getText();

                BankingOperations p = new BankingOperations();
                p.payBills(sum, idAc, typeBill);

                Date thisDate = new Date(System.currentTimeMillis());
                String message = "The employee with PNC " + Login.pncCurrentEmployee + " payed a bill " + " , " + thisDate + " .\n";
                GenerateReports.reports.add(message);
            }
        });

        add.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent et) {

                ArrayList<Account> list = new ArrayList<Account>();

                int idAccount = Integer.parseInt(id.getText());
                int pncC = Integer.parseInt(pnc.getText());
                String typeAcc = type.getText();
                int amm = Integer.parseInt(amount.getText());
                Date newDate = new Date(System.currentTimeMillis());

                DataProcessing d = new DataProcessing();
                d.addAccount(idAccount, pncC, typeAcc, amm, newDate);

                Date thisDate = new Date(System.currentTimeMillis());
                String message = "The employee with PNC " + Login.pncCurrentEmployee + " created a new account with id " + idAccount + " , " + thisDate + " .\n";

                GenerateReports.reports.add(message);

                Account cl = new Account(idAccount, pncC, typeAcc, amm, newDate);
                list.add(cl);
                tabel=makeTable(list);

                processTable();
                panel.add(scrollPane);
                frame.setVisible(true);

            }
        });

        update.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent et) {

                ArrayList<Account> list = new ArrayList<Account>();
                int idAccount = Integer.parseInt(id.getText());

                String typeAcc = type.getText();
                int amm = Integer.parseInt(amount.getText());

                DataProcessing dp = new DataProcessing();
                dp.updateAccount(idAccount, typeAcc, amm);

                Date thisDate = new Date(System.currentTimeMillis());
                String message = "The employee with PNC " + Login.pncCurrentEmployee + " updated an account with id " + idAccount + " , " + thisDate + " .\n";
                GenerateReports.reports.add(message);

                tabel=makeTable(list);
                processTable();
                panel.add(scrollPane);
                frame.setVisible(true);

            }
        });

        delete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent et) {

                ArrayList<Account> list = new ArrayList<Account>();
                int idAc = Integer.parseInt(id.getText());
                Account c1 = new Account(idAc);

               DataProcessing dp = new DataProcessing();
               dp.deleteAccount(idAc);

                list.remove(c1);
                tabel=makeTable(list);

                Date thisDate = new Date(System.currentTimeMillis());
                String message = "The employee with PNC " + Login.pncCurrentEmployee + " deleted an account with id " + idAc + " , " + thisDate + " .\n";
                GenerateReports.reports.add(message);

                processTable();
                panel.add(scrollPane);
                frame.setVisible(true);
            }
        });

        view.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent et) {

                ArrayList<Account> list = new ArrayList<Account>();
                list = AccountDAO.viewAllAccounts();
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

                id.setText("");
                pnc.setText("");
                pnc.setText("");
                amount.setText("");
                type.setText("");

                frame.setVisible(true);
            }
        });

    }

}
