package businessLayer;

import dataAccessLayer.AdminDAO;
import dataAccessLayer.EmployeeDAO;
import dataClasses.Admin;
import dataClasses.Employee;
import presentationLayer.EmployeeWindow;
import presentationLayer.LoginPage;
import presentationLayer.UserTasks;

import javax.swing.*;
import java.sql.SQLException;

public class Login {

    public static int pncCurrentEmployee = 0;

    public Login(){

    }
    public static Employee employeeLocationInDB(String user) {

        EmployeeDAO ed = new EmployeeDAO();
        Employee a = ed.findByName(user);//employee found in db

        if(a != null){
            if(!(user.equals("Rahe"))) {//daca e regular user
                pncCurrentEmployee = a.getPncEmployee();//id for admin to keep the evidence
            }
            return a;
        }

        return null;
    }

    public static Admin adminLocationInDB(String user) {

        AdminDAO ed = new AdminDAO();
        Admin a = ed.findByName(user);//employee found in db

        if(a != null){
            return a;
        }

        return null;
    }

    public static void userLoginAction(String user, String pass) {

        Employee a = employeeLocationInDB(user);

        if (!(user.equals("")) && !(pass.equals("")) && a != null && pass.equals(a.getPassword())) {

            JOptionPane.showMessageDialog(LoginPage.frame, "Successfully logged in!");
            new UserTasks();
        }
        else
        if(user.equals("") || pass.equals("")){
            JOptionPane.showMessageDialog(LoginPage.frame, "All fields must be completed!");
        }

        else
        if(a == null ){
            JOptionPane.showMessageDialog(LoginPage.frame, "This user doesn't exist!");
        }

        else
        if(a.getUsername().equals(user) && !(a.getPassword().equals(pass))){
            JOptionPane.showMessageDialog(LoginPage.frame, "Incorrect password!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void adminLoginAction(String user, String pass) throws SQLException {

        Admin a = adminLocationInDB(user);

        if (!(user.equals("")) && !(pass.equals("")) && a != null && pass.equals(a.getAdminPass())) {

            JOptionPane.showMessageDialog(LoginPage.frame, "Successfully logged in!");
            new EmployeeWindow();

        }
        else
        if(user.equals("") || pass.equals("")){
            JOptionPane.showMessageDialog(LoginPage.frame, "All fields must be completed!");
        }

        else
        if(a == null ){
            JOptionPane.showMessageDialog(LoginPage.frame, "This admin doesn't exist!");
        }

        else
        if(a.getAdminUsername().equals(user) && !(a.getAdminPass().equals(pass))){
            JOptionPane.showMessageDialog(LoginPage.frame, "Incorrect password!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    }

