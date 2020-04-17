package dataClasses;

public class Admin {

    private String adminUsername;
    private String adminPass;
    private int adminId;

    public Admin(String adminUsername, String adminPass, int adminId){

        this.adminUsername = adminUsername;
        this.adminPass = adminPass;
        this.adminId = adminId;
    }

    public String getAdminUsername() {
        return adminUsername;
    }

    public void setAdminUsername(String adminUsername) {
        this.adminUsername = adminUsername;
    }

    public String getAdminPass() {
        return adminPass;
    }

    public void setAdminPass(String adminPass) {
        this.adminPass = adminPass;
    }

    public int getAdminId(){
        return adminId;
    }
    public void setAdminId(int adminId){
        this.adminId = adminId;
    }
}
