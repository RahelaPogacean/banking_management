package dataClasses;

public class Client {
    private String nameClient;
    private int cardNo;
    private int pncClient;
    private String address;

    public Client(String nameClient, int cardNo, int pncClient, String address) {
        this.nameClient = nameClient;
        this.cardNo = cardNo;
        this.pncClient = pncClient;
        this.address = address;
    }

    public Client() {
    }

    public Client(int pncClient) {
        this.pncClient = pncClient;
    }

    public String getNameClient() {
        return this.nameClient;
    }

    public int getCardNo() {
        return this.cardNo;
    }

    public int getPncClient() {
        return this.pncClient;
    }

    public String getAddress() {
        return this.address;
    }

    public void setNameClient(String nameClient) {
        this.nameClient = nameClient;
    }

    public void setCardNo(int cardNo) {
        this.cardNo = cardNo;
    }

    public void setPncClient(int pncClient) {
        this.pncClient = pncClient;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String toString() {
        return " Client " + this.nameClient + " has cardNo " + this.cardNo + " and pnc " + this.pncClient + " and lives at " + this.address;
    }
}
