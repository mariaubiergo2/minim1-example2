package edu.upc.dsa.models;

import edu.upc.dsa.util.RandomUtils;

public class User {

    String id;
    String name;
    String surnames;

    String birthdate;

    String mail;

    String password;

    int dsaCoins;

    static int lastId;

    public User() {
        this.id = RandomUtils.getId();
    }

    public User(String name, String surnames, String birthdate, String mail, String password) {
        this();
        this.setName(name);
        this.setSurnames(surnames);
        this.setBirthdate(birthdate);
        this.setMail(mail);
        this.setPassword(password);
        this.setDsaCoins(50);
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id=id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurnames() {
        return surnames;
    }
    public void setSurnames(String surnames) {
        this.surnames = surnames;
    }


    public String getBirthdate() {
        return birthdate;
    }
    public void setBirthdate(String b) {
        this.birthdate = b;
    }

    public String getMail() {
        return mail;
    }
    public void setMail(String m) {
        this.mail = m;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String p) {
        this.password = p;
    }

    public int getDsaCoins() {
        return dsaCoins;
    }
    public void setDsaCoins(int p) {
        this.dsaCoins = p;
    }

    @Override
    public String toString() {
        return "Track [id="+id+", name=" + name + surnames +", birth date=" + birthdate +", password=" + password +", dsaCoins=" + dsaCoins +"]";
    }

}