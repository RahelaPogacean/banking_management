package dataAccessLayer;

import dataClasses.Client;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class ClientDAO {

    public void create(String nameClient, int cardNo, int pnc, String address) {

        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank", "root", "");
            Statement stmt = con.createStatement();

            String q1 = "insert into Client values('" + nameClient + "', '" + cardNo + "', '" + pnc + "', '" + address + "')";
            int x = stmt.executeUpdate(q1);
            if (x > 0)
                System.out.println("Successfully Inserted");
            else
                System.out.println("Insert Failed");

            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static ArrayList<Client> viewAll() {

        ArrayList<Client> list = new ArrayList<>();

        try {

            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank", "root", "");
            Statement smt = con.createStatement();

            String q = "Select * from Client";
            ResultSet rs = smt.executeQuery(q);


            while (rs.next()) {

                Client c = new Client(rs.getString(1), rs.getInt(2), rs.getInt(3), rs.getString(4));
                list.add(c);
            }

            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }


    public void deleteClient(int pnc) {

        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank", "root", "");
            Statement stmt = con.createStatement();

            String q1 = "DELETE from Client WHERE pncClient = '" + pnc + "'";

            int x = stmt.executeUpdate(q1);
            if (x > 0)
                System.out.println("Successfully Deleted");
            else
                System.out.println("Delete Failed");

            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }


    public Client findByPnc(int pnc) {

        Client c = null;

        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank", "root", "");
            Statement stmt = con.createStatement();

            // SELECT query
            String q1 = "select * from Client WHERE pncClient = '" + pnc + "'";
            ResultSet rs = stmt.executeQuery(q1);
            if (rs.next()) {
                c = new Client(rs.getString(1), rs.getInt(2), rs.getInt(3), rs.getString(4));

            } else {
                System.out.println("No such user id is already registered");
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }

        return c;
    }


    public void updateClient(int pnc, String newName, int newCard, String newAddress) {

        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank", "root", "");
            Statement stmt = con.createStatement();

            String q1 = "UPDATE Client set nameClient = '" + newName + "' WHERE pncClient = '" + pnc + "'";
            ;
            String q2 = "UPDATE Client set cardNo = '" + newCard + "' WHERE pncClient = '" + pnc + "'";
            ;
            String q3 = "UPDATE Client set nameClient = '" + newName + "' WHERE pncClient = '" + pnc + "'";
            ;
            String q4 = "UPDATE Client set address = '" + newAddress + "' WHERE pncClient = '" + pnc + "'";
            ;


            int x = stmt.executeUpdate(q1);
            int x1 = stmt.executeUpdate(q2);
            int x2 = stmt.executeUpdate(q3);
            int x3 = stmt.executeUpdate(q4);

            if (x > 0 && x1 > 0 && x2 > 0 && x3 > 0)
                System.out.println("Client Successfully Updated");
            else
                System.out.println("ERROR OCCURED :(");

            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }


    public ClientDAO() {

    }


}
