package dataClasses;

import java.sql.Date;

public class DataHistory {

    private int pncEmployee;
    private int pncClient;
    private String operation;
    private Date dateOfOperation;

    public int getPncClient() {
        return pncClient;
    }

    public void setPncClient(int pncClient) {
        this.pncClient = pncClient;
    }

    public int getPncEmployee() {
        return pncEmployee;
    }

    public void setPncEmployee(int pncEmployee) {
        this.pncEmployee = pncEmployee;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public Date getDateOfOperation() {
        return dateOfOperation;
    }

    public void setDateOfOperation(Date dateOfOperation) {
        this.dateOfOperation = dateOfOperation;
    }

    public DataHistory(int pncEmployee, int pncClient, String operation, Date dateOfOperation) {
        this.pncEmployee = pncEmployee;
        this.operation = operation;
        this.pncClient = pncClient;
        this.dateOfOperation = dateOfOperation;
    }

    public String toString() {
        return " Employee with pnc " + this.pncEmployee + " has done the operation " + this.operation + " on client with  pnc " + this.pncClient + " , performed on  " + this.dateOfOperation + "/n";
    }
}
