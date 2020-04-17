package businessLayer;

import dataAccessLayer.AccountDAO;
import dataAccessLayer.ClientDAO;
import dataAccessLayer.DataHistoryDAO;
import dataAccessLayer.EmployeeDAO;
import dataClasses.Account;
import dataClasses.Client;
import dataClasses.Employee;

import java.sql.Date;

public class DataProcessing {

    public void addAccount(int idAccount, int pncC, String typeAcc, double amm, Date newDate){

        AccountDAO ad = new AccountDAO();
        ad.createAccount(idAccount, pncC, typeAcc, amm, newDate);

        Date thisDate = new Date(System.currentTimeMillis());
        DataHistoryDAO dh = new DataHistoryDAO();
        dh.insertIntoHistory(Login.pncCurrentEmployee, pncC, "Create new Account", thisDate);

    }

    public void updateAccount(int idAccount, String typeAcc, double amm){

        Account aa = new Account(idAccount);
        int pncC = aa.getPncClient();

        AccountDAO ad = new AccountDAO();

        Account c1 = ad.findById(idAccount);
        ad.updateAccount(idAccount, c1.getPncClient(), typeAcc, amm);

        Date thisDate = new Date(System.currentTimeMillis());
        DataHistoryDAO dh = new DataHistoryDAO();

        dh.insertIntoHistory(Login.pncCurrentEmployee, pncC, "Update Account", thisDate);
    }

    public void deleteAccount(int idAc){

        Account aa = new Account(idAc);
        int pncC = aa.getPncClient();

        AccountDAO ad = new AccountDAO();
        ad.deleteAccount(idAc);

        Date thisDate = new Date(System.currentTimeMillis());
        DataHistoryDAO dh = new DataHistoryDAO();
        dh.insertIntoHistory(Login.pncCurrentEmployee, pncC, "Delete Account", thisDate);
    }

    public void addClient(String nameC, int cardC, int pncC, String addressC){

        ClientDAO cd = new ClientDAO();
        cd.create(nameC, cardC, pncC, addressC);//insert into db

        Date thisDate = new Date(System.currentTimeMillis());
        DataHistoryDAO dh = new DataHistoryDAO();

        dh.insertIntoHistory(Login.pncCurrentEmployee, pncC, "Create new Client", thisDate);
    }

    public void updateClient(int pncC, String newName, int newCard, String newAddress){

        ClientDAO cc = new ClientDAO();
        cc.updateClient(pncC, newName, newCard, newAddress);

        Date thisDate = new Date(System.currentTimeMillis());
        DataHistoryDAO dh = new DataHistoryDAO();
        dh.insertIntoHistory(Login.pncCurrentEmployee, pncC, "Update  Client", thisDate);
    }

    public void deleteClient(int pncC, Client c1){

        ClientDAO cd = new ClientDAO();
        cd.deleteClient(pncC);

        Date thisDate = new Date(System.currentTimeMillis());
        DataHistoryDAO dh = new DataHistoryDAO();
        dh.insertIntoHistory(Login.pncCurrentEmployee, pncC, "Delete Client", thisDate);

        c1 = cd.findByPnc(pncC);
    }

    public void addEmployee(int pncE, String userE, String passE, String addressE){

        EmployeeDAO ee = new EmployeeDAO();
        ee.createEmployee(pncE, userE, passE, addressE);//insert into db
    }

    public void updateEmployee(int pncC, String newName, String newAddress){

        EmployeeDAO ed = new EmployeeDAO();
        ed.updateEmployee(pncC, newName, newAddress);
    }

    public void deleteEmployee(int pncC, Employee c1){

        EmployeeDAO ed = new EmployeeDAO();
        ed.deleteEmployee(pncC);

        c1 = ed.findById(pncC);

    }
}
