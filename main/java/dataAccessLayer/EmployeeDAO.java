package dataAccessLayer;

import dataClasses.Employee;
import java.sql.*;
import java.util.ArrayList;

public class EmployeeDAO {

    public void createEmployee(int pncEmployee, String username, String password,  String address) {

        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank", "root", "");
            Statement stmt = con.createStatement();

            String q1 = "insert into Employee values('" + pncEmployee + "', '" + username + "', '" + password + "', '" + address + "')";
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

    public static ArrayList<Employee> viewAllEmployees() {

        ArrayList<Employee> list = new ArrayList<>();

        try {

            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank", "root", "");
            Statement smt = con.createStatement();

            String q = "Select * from Employee";
            ResultSet rs = smt.executeQuery(q);


            while (rs.next()) {

                Employee c = new Employee(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
                list.add(c);
            }

            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }


    public void deleteEmployee(int pncEmployee) {

        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank", "root", "");
            Statement stmt = con.createStatement();

            String q1 = "DELETE from Employee WHERE pncEmployee = '" + pncEmployee + "'";

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


    public Employee findById(int pncEmployee) {

        Employee c = null;

        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank", "root", "");
            Statement stmt = con.createStatement();

            String q1 = "select * from Employee WHERE pncEmployee = '" + pncEmployee + "'";
            ResultSet rs = stmt.executeQuery(q1);
            if (rs.next()) {
                c = new Employee(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));

            } else {
                System.out.println("No such user id is already registered");
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }

        return c;
    }

    public  Employee findByName(String name) {

        Employee c = null;

        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank", "root", "");
            Statement stmt = con.createStatement();

            String q1 = "select * from Employee WHERE username = '" + name + "'";
            ResultSet rs = stmt.executeQuery(q1);
            if (rs.next()) {
                c = new Employee(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));

            } else {
                System.out.println("No such user id is already registered");
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }

        return c;
    }

    public void updateEmployee(int pnc, String newName, String newAddress){

        try
        {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank", "root", "");
            Statement stmt = con.createStatement();

            String q1 = "UPDATE Employee set username = '" + newName + "' WHERE pncEmployee = '" + pnc + "'";;
            String q2 = "UPDATE Employee set address = '" + newAddress + "' WHERE pncEmployee = '" + pnc + "'";;

            int x = stmt.executeUpdate(q1);
            int x1 = stmt.executeUpdate(q2);

            if (x > 0 && x1 > 0)
                System.out.println("Employee Successfully Updated");
            else
                System.out.println("ERROR OCCURED :(");

            con.close();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }

    public EmployeeDAO(){

    }

}
