package edu.upc.dsa.models;

import edu.upc.dsa.util.RandomUtils;

public class VOcredencials {
    String mail;
    String password;

    public VOcredencials() {   }
    public VOcredencials(String m, String p) {
        this();
        this.setMail(m);
        this.setPassword(p);
    }

    public String getMail() {
        return this.mail;
    }
    public void setMail(String i) {
        this.mail=i;
    }

    public String getPass() {
        return this.password;
    }
    public void setPassword(String name) {
        this.password = name;
    }

    @Override
    public String toString() {
        return "password=" + password +" mail =" + mail +"]";
    }
}
