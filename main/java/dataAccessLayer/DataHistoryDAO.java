package dataAccessLayer;

import dataClasses.DataHistory;
import java.sql.*;
import java.util.ArrayList;

public class DataHistoryDAO {

    public DataHistoryDAO() {

    }

    public  void insertIntoHistory(int pncE, int pncC, String op, Date myDate) {

        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank", "root", "");
            Statement stmt = con.createStatement();

            // Inserting data in database
            String q1 = "insert into DataHistory values('" + pncE + "', '" + pncC + "', '" + op + "', '" + myDate + "')";
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

    public static  ArrayList<DataHistory> viewAll() {

        ArrayList<DataHistory> list = new ArrayList<>();

        try {

            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank", "root", "");
            Statement smt = con.createStatement();

            String q = "Select * from DataHistory";
            ResultSet rs = smt.executeQuery(q);


            while (rs.next()) {

                DataHistory c = new DataHistory(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getDate(4));
                list.add(c);
            }

            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }


}
