package com.example.nfcconnect;

public class UserHelperClass {
    String userName,emailId,password, nfcPassword;

    public UserHelperClass() {
        this.userName = userName;
    }

    public UserHelperClass(String userName, String emailId, String password, String nPassword) {
        this.userName = userName;
        this.emailId = emailId;
        this.password = password;
        this.nfcPassword = nPassword;
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

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNfcPassword() {
        return nfcPassword;
    }

    public void setNfcPassword(String nfcPassword) {
        this.nfcPassword = nfcPassword;
    }
}
