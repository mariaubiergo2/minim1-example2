package edu.upc.dsa;

import edu.upc.dsa.models.*;

import java.util.List;

public interface ShopManager {
    public User addUser(VOuser vouser);
    public List<User> sortAlpha();
    public User logIn(VOcredencials credencials);
    public Object buyObject(VOcredencials credencials, String objecte);
    public Objecte addObject(Objecte vOobjecte);
    public List<Objecte> sortNumObjectes();
    public List<Objecte> getObjectes(User u);



    //Additional functions
    public VOcredencials getCredentials(User u);
    public Objecte getObject(String name); //
    public User getUser(String id);
    public String getUser(VOcredencials credencials); //
    public String getUserByMail(String mail); //
    public List<User> getAllUsers();
    public List<Objecte> getAllObjectes();
    public int sizeUsers();
    public int sizeObjectes();
}
