package com.example.nfcconnect;

public class UserHelperClass {
    String userName,emailId,password, nfcPassword;

    public UserHelperClass() {
        this.userName = userName;
    }

    public UserHelperClass(String userName, String emailId, String encPassword, String encNFCpass) {
        this.userName = userName;
        this.emailId = emailId;
        this.password = encPassword;
        this.nfcPassword =encNFCpass;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String encPassword) {
        this.password = encPassword;
    }

    public String getNfcPassword() {
        return nfcPassword;
    }

    public void setNfcPassword(String encnfcPassword) {
        this.nfcPassword = encnfcPassword;
    }
}
