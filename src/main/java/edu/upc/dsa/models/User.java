package edu.upc.dsa.models;

import java.util.LinkedList;
import java.util.List;

public class User {
    String dni;
    String name;
    String surnames;
    String birthdate;
    String mail;
    String password;
    LinkedList<Objecte> boughtObjectes;
    int dsaCoins;

    public User() {    }

    public User(String dni, String name, String surnames, String birthdate, String mail, String password) {
        this();
        this.setDni(dni);
        this.setName(name);
        this.setSurnames(surnames);
        this.setBirthdate(birthdate);
        this.setMail(mail);
        this.setPassword(password);
        this.setDsaCoins(50);
        this.startBoughtObjectes();
    }

    public User(VOuser vouser){
        this();
        this.setDni(vouser.getDni());
        this.setName(vouser.getName());
        this.setSurnames(vouser.getSurnames());
        this.setBirthdate(vouser.getBirthdate());
        this.setMail(vouser.getMail());
        this.setPassword(vouser.getPassword());
        this.setDsaCoins(50);
        this.startBoughtObjectes();
    }

/*
    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id=id;
    }
*/
    public String getDni() {
        return dni;
    }
    public void setDni(String dni) {
        this.dni = dni;
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

    public List<Objecte> getObjects() {
        return this.boughtObjectes;
    }
    public void startBoughtObjectes() {
        this.boughtObjectes = new LinkedList<>();
    }


    public Objecte buyObject(Objecte objecte){
        if (this.dsaCoins>=objecte.getDsaCoins()){
            this.dsaCoins=this.dsaCoins-objecte.getDsaCoins();
            this.boughtObjectes.add(objecte);
            return objecte;
        }
        return null;
    }

    public Integer autentification(VOcredencials credencials){
        if (this.getMail().equals(credencials.getMail())&&this.getPassword().equals(credencials.getPass())){
            return 0;
        }
        return 1;
    }


    @Override
    public String toString() {
        return "User [name=" + name+surnames+", birth date=" + birthdate +", password=" + password +" mail =" + mail +", dsaCoins=" + dsaCoins +", bougth products="+boughtObjectes+"]";
    }
}
