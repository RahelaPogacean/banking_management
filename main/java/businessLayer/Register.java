package businessLayer;

import dataAccessLayer.EmployeeDAO;
import dataClasses.Employee;
import presentationLayer.RegisterPage;
import presentationLayer.UserTasks;

import javax.swing.*;

import static businessLayer.Login.employeeLocationInDB;

public class Register {

    public static int pncCurrentEmployee = 0;

    public Register(){

    }


    public static Employee employeeLocationInDB(int pncE) {

        EmployeeDAO ed = new EmployeeDAO();
        Employee a = ed.findById(pncE);


        if(a != null){

            pncCurrentEmployee = a.getPncEmployee();//id for admin to keep the evidence
            return a;
        }

        return null;
    }

    public static void registerAction(String userE, int pncE, String passE, String confirmE, String addressE){

        EmployeeDAO ed = new EmployeeDAO();
        Employee a = ed.findById(pncE);

        if(a != null && a.getPncEmployee() == pncE){
            JOptionPane.showMessageDialog(RegisterPage.frame, "Your already have an account! Please login!");
        }

        else if (userE == "" || passE == "" || confirmE == "" || addressE == "") {
                JOptionPane.showMessageDialog(RegisterPage.frame, "All the fields must be completed!!!");
            }
            else
                if (pncE < 0 || pncE > 9999999) {
                JOptionPane.showMessageDialog(RegisterPage.frame, "Your PNC must be a value between 0 and 9999999!");
            }
            else
                if (!(passE.equals(confirmE))) {
                JOptionPane.showMessageDialog(RegisterPage.frame, "Incorrect login or password", "Error", JOptionPane.ERROR_MESSAGE);
            }

            else
               if(!(userE.equals("")) && !(passE.equals("")) && !(confirmE.equals("")) && !(addressE.equals("")) && passE.equals(confirmE )){

                ed.createEmployee(pncE, userE, passE, addressE);//insert into db
                JOptionPane.showMessageDialog(RegisterPage.frame, "Your account has been created!");
                new UserTasks();
            }
        }
    }


