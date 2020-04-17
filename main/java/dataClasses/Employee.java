package dataClasses;

public class Employee {

    private  String username;
    private  String password;
    private int pncEmployee;
    private String address;

    public Employee(int pncEmployee, String username, String password, String address) {

        this.username = username;
        this.password = password;
        this.pncEmployee = pncEmployee;
        this.address = address;
    }

    public Employee(String username) {
        this.username = username;
    }

    public Employee() {

    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getPncEmployee() {
        return this.pncEmployee;
    }

    public void setPncEmployee(int pncEmployee) {
        this.pncEmployee = pncEmployee;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String toString() {
        return "Employee " + this.username + "with pnc " + this.pncEmployee + "lives at " + this.address;
    }
}
