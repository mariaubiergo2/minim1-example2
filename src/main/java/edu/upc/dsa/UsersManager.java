package edu.upc.dsa;

import edu.upc.dsa.models.User;

import java.util.List;

public interface UsersManager {

    public User createUser(String name, String surnames, String birthdate, String mail, String password);
    public User addUser(User t);
    public User getUser(String id);
    public List<User> findAll();
    public User findUserByMail(String mail);
    public void deleteUser(String id);
    public User updateUser(User t);
    public User updateUserCoins(User t);
    public List<User> sortAlpha();
    public Object buyObject(String name);
    public int size();
/*
    public User addUser(String id, String singer);
    public User addUser(User t);
    public User getTrack(String id);
    public List<User> findAll();
    public void deleteTrack(String id);
    public User updateTrack(User t);

    public int size();

 */
}
