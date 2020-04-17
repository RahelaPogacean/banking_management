package businessLayer;

import dataAccessLayer.AccountDAO;
import dataClasses.Account;
import presentationLayer.AccountWindow;
import javax.swing.*;
import java.sql.Date;

public class BankingOperations {

    public BankingOperations(){

    }

    public void payBills(double sum, int idAc, String typeBill){

        AccountDAO ad = new AccountDAO();
        Account a = ad.findById(idAc);

        double newSum = a.getAmount() - sum;

        if(newSum < 100){
            JOptionPane.showMessageDialog(AccountWindow.frame, "Insufficient funds! The operation cannot be processed!");
        }
        else{
            DataProcessing dp = new DataProcessing();
            dp.updateAccount(idAc, a.getTypeAccount(), newSum);
            String message = "The client with PNC " + idAc + " payed the expenses related to  " + typeBill + " in the amount of  " + sum +  ".";
            JOptionPane.showMessageDialog(AccountWindow.frame, message);
        }
    }

    public void withdraw(int clientId1, int clientId2, double sum) {

        AccountDAO aa1 = new AccountDAO();
        AccountDAO aa2 = new AccountDAO();

        Account cl1 = aa1.findById(clientId1);
        Account cl2 = aa2.findById(clientId2);

        double minSum = 100;

        double s1 = cl1.getAmount() + sum;
        double s2 = cl2.getAmount() - sum;

        if (s2 < 100) {

            JOptionPane.showMessageDialog(AccountWindow.frame, "Insufficient funds! The operation cannot be processed!");
        }
        else {
            aa1.updateAccount(clientId1, cl1.getPncClient(), cl1.getTypeAccount(), s1);
            aa2.updateAccount(clientId2, cl2.getPncClient(), cl2.getTypeAccount(), s2);
            Date thisTime = new Date(System.currentTimeMillis());
            String message = "The client with PNC " + clientId1 + " borrowed " + sum + " RON from the client with PNC " + clientId2 + " at " + thisTime + ".";
            JOptionPane.showMessageDialog(AccountWindow.frame, message);
        }
    }

    public void deposit(int clientId1, int clientId2, double sum){

        AccountDAO aa1 = new AccountDAO();
        AccountDAO aa2 = new AccountDAO();

        Account cl1 = aa1.findById(clientId1);
        Account cl2 = aa2.findById(clientId2);

        double s1 = cl1.getAmount() - sum;
        double s2 = cl2.getAmount() + sum;

        if (s1 < 100) {

            JOptionPane.showMessageDialog(AccountWindow.frame, "Insufficient funds! The operation cannot be processed!");
        }
        else {
            aa1.updateAccount(clientId1, cl1.getPncClient(), cl1.getTypeAccount(), s1);
            aa2.updateAccount(clientId2, cl2.getPncClient(), cl2.getTypeAccount(), s2);
            Date thisTime = new Date(System.currentTimeMillis());
            String message = "The client with PNC " + clientId1 + " gave " + sum + " RON to the client with PNC " + clientId2 + " at " + thisTime + ".";
            JOptionPane.showMessageDialog(AccountWindow.frame, message);
        }
    }
}
