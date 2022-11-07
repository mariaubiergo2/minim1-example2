package edu.upc.dsa;

import edu.upc.dsa.models.Objecte;
import edu.upc.dsa.models.User;
import edu.upc.dsa.models.VOcredencials;

import java.util.List;

public interface ShopManager {

    public User createUser(String name, String surnames, String birthdate, String mail, String password);
    public User addUser(User t);
    public Integer getUser(VOcredencials credencials);
    public VOcredencials getCredentials(User u);
    public User getUser(Integer id);
    public List<User> findAllUsers();
    public Integer getUserByMail(String mail);
    public Integer deleteUser(String id);
    public User updateUser(User t);
    public List<User> sortAlpha();

    public User logIn(VOcredencials credencials);
    public Object buyObject(VOcredencials credencials, String name);
    public int sizeUsers();

    public Objecte addObject(String name, String description, int coins);
    public Objecte addObject(Objecte t);
    public Objecte getObject(String name);
    public List<Objecte> findAllObjectes();
    public void deleteObject(String name);
    public Objecte findObject(String name);
    public List<Objecte> sortNumObjectes();
    public int sizeObjectes();

}
