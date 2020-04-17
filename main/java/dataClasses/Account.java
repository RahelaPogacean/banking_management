package dataClasses;


import java.sql.Date;

public class Account {
    private int idAccount;
    private int pncClient;
    private String typeAccount;
    private double amount;
    private Date dateCreation;

    public Account(int idAccount, int pncClient, String typeAccount, double amount, Date dateCreation) {
        this.idAccount = idAccount;
        this.pncClient = pncClient;
        this.typeAccount = typeAccount;
        this.amount = amount;
        this.dateCreation = dateCreation;
    }

    public Account(int idAccount){
        this.pncClient = pncClient;
    }

    public Account(int idAccount, int pncC, String typeAcc, int amm) {
        this.idAccount = idAccount;
        this.pncClient = pncClient;
        this.typeAccount = typeAccount;
        this.amount = amount;
    }

    public int getIdAccount() {
        return this.idAccount;
    }

    public void setIdAccount(int idAccount) {
        this.idAccount = idAccount;
    }

    public int getPncClient() {
        return this.pncClient;
    }

    public void setPncClient(int pncClient) {
        this.pncClient = pncClient;
    }

    public String getTypeAccount() {
        return this.typeAccount;
    }

    public void setTypeAccount(String typeAccount) {
        this.typeAccount = typeAccount;
    }

    public double getAmount() {
        return this.amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public String toString() {
        return "Account with id " + this.idAccount + "belongs to client with pnc " + this.pncClient + ", has type " + this.typeAccount + "and amount " + this.amount + ", the dateof transaction was " + this.dateCreation;
    }


    public void withdraw(int idAccount1, int idAccount2, double value, double actualSum1, double actualSum2){

        Account acc1 = new Account(idAccount1);
        Account acc2 = new Account(idAccount2);
        actualSum1 = acc1.getAmount();
        actualSum1 += value;
        acc1.setAmount(actualSum1);
        actualSum2 = acc2.getAmount();
        actualSum2 -= value;
        acc2.setAmount(actualSum2);
    }

}
