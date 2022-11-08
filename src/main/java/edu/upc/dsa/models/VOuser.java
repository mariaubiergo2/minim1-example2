package edu.upc.dsa.models;

public class VOuser {
    String dni;
    String name;
    String surnames;
    String birthdate;
    String mail;
    String password;

    public VOuser() {    }

    public VOuser(String dni, String name, String surnames, String birthdate, String mail, String password) {
        this();
        this.setDni(dni);
        this.setName(name);
        this.setSurnames(surnames);
        this.setBirthdate(birthdate);
        this.setMail(mail);
        this.setPassword(password);
    }

    public VOuser(User u) {
        this();
        this.setName(u.getName());
        this.setSurnames(u.getSurnames());
        this.setBirthdate(u.getBirthdate());
        this.setMail(u.getMail());
        this.setPassword(u.getPassword());
    }

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



    @Override
    public String toString() {
        return "User [name=" + name+surnames+", birth date=" + birthdate +", password=" + password +" mail =" + mail +"]";
    }

}
