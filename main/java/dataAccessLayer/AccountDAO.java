package dataAccessLayer;

import dataClasses.Account;
import java.sql.*;

import java.util.ArrayList;

public class AccountDAO {

    public void createAccount(int idAccount, int pncClient, String typeAccount, double amount, Date dateCreation) {

        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank", "root", "");
            Statement stmt = con.createStatement();

            // Inserting data in database
            String q1 = "insert into Account values('" + idAccount + "', '" + pncClient + "', '" + typeAccount + "', '" + amount + "', '" + dateCreation + "')";
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

    public static ArrayList<Account> viewAllAccounts() {

        ArrayList<Account> list = new ArrayList<>();

        try {

            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank", "root", "");
            Statement smt = con.createStatement();

            String q = "Select * from Account";
            ResultSet rs = smt.executeQuery(q);


            while (rs.next()) {

                Account c = new Account(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getInt(4), rs.getDate(5));
                list.add(c);
            }

            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }


    public  void deleteAccount(int idAccount) {

        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank", "root", "");
            Statement stmt = con.createStatement();

            // Inserting data in database
            String q1 = "DELETE from Account WHERE idAccount = '" + idAccount + "'";

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


    public  Account findById(int idAccount) {

        Account c = null;

        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank", "root", "");
            Statement stmt = con.createStatement();

            String q1 = "select * from Account WHERE idAccount = '" + idAccount + "'";
            ResultSet rs = stmt.executeQuery(q1);

            if (rs.next()) {
                c = new Account(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getInt(4), rs.getDate(5));
            } else {
                System.out.println("No such user id is already registered");
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }

        return c;
    }

    public  Account findByPnc(int pnc) {

        Account c = null;

        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank", "root", "");
            Statement stmt = con.createStatement();

            String q1 = "select * from Account WHERE pncClient = '" + pnc + "'";
            ResultSet rs = stmt.executeQuery(q1);

            if (rs.next()) {
                c = new Account(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getInt(4), rs.getDate(5));
            } else {
                System.out.println("No such user id is already registered");
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }

        return c;
    }


    public void updateAccount(int id, int pnc, String type, double value){

        try
        {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank", "root", "");
            Statement stmt = con.createStatement();

            String q1 = "UPDATE Account set typeAccount = '" + type + "' WHERE idAccount = '" + id + "'";;
            String q2 = "UPDATE Account set amount = '" + value + "' WHERE idAccount = '" + id + "'";;


            int x = stmt.executeUpdate(q1);
            int x1 = stmt.executeUpdate(q2);

            if (x > 0 && x1 > 0)
                System.out.println("Account Successfully Updated");
            else
                System.out.println("ERROR OCCURED :(");

            con.close();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }


    public AccountDAO(){

    }



}