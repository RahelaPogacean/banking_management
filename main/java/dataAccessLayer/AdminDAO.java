package dataAccessLayer;

import dataClasses.Admin;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class AdminDAO {

    public static Admin findById(int id) {

        Admin c = null;

        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank", "root", "");
            Statement stmt = con.createStatement();

            String q1 = "select * from Admin WHERE adminId = '" + id + "'";
            ResultSet rs = stmt.executeQuery(q1);
            if (rs.next()) {
                c = new Admin(rs.getString(1), rs.getString(2), rs.getInt(3));

            } else {
                System.out.println("No such user id is already registered");
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }

        return c;
    }

    public static Admin findByName(String name) {

        Admin c = null;

        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank", "root", "");
            Statement stmt = con.createStatement();

            // SELECT query
            String q1 = "select * from Admin WHERE adminUsername = '" + name + "'";
            ResultSet rs = stmt.executeQuery(q1);
            if (rs.next()) {
                c = new Admin(rs.getString(1), rs.getString(2), rs.getInt(3));

            } else {
                System.out.println("No such user id is already registered");
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }

        return c;
    }


    public AdminDAO(){

    }
}
