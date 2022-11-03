package edu.upc.dsa;

import edu.upc.dsa.models.Object;
import edu.upc.dsa.models.User;

import java.util.List;

public interface ObjectsManager {

    public Object createObject(String name, String description, int coins);
    public Object addObject(Object t);
    public Object getObject(String name);
    public List<Object> findAll();
    public void deleteObject(String name);
    public boolean findObject(String name);
    public List<Object> sortNum();
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
