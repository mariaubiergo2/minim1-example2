package edu.upc.dsa;

import edu.upc.dsa.models.User;

import java.util.*;

import org.apache.log4j.Logger;

public class UsersManagerImpl implements UsersManager {
    private static UsersManager instance;

    protected List<User> users;
    protected Map<User, List<Object>> usersObjectMap;
    final static Logger logger = Logger.getLogger(UsersManagerImpl.class);

    private UsersManagerImpl() {
        this.users = new LinkedList<>();
        this.usersObjectMap = new HashMap<>();
    }

    public static UsersManager getInstance() {
        if (instance==null) instance = new UsersManagerImpl();
        return instance;
    }

    public int size() {
        int ret = this.users.size();
        logger.info("size " + ret);

        return ret;
    }

    public User addUser(User t) {
        logger.info("new User " + t);

        if (findUserByMail(t.getMail()) == null) {
            this.users.add(t);
            logger.info("new User added");
            return t;
        }
        logger.warn("couldnt add new user bc already exists user with that mail");
        return null;
    }

    public User createUser(String name, String surnames, String birthdate, String mail, String password) {
        return this.addUser(new User(name, surnames, birthdate, mail, password));
    }

    public User getUser(String id) {
        logger.info("TryingToGetUser("+id+")");

        for (User t: this.users) {
            if (t.getId().equals(id)) {
                logger.info("gotUser("+id+"): "+t);

                return t;
            }
        }

        logger.warn("not found " + id);
        return null;
    }

    @Override
    public User findUserByMail(String mail) {
        logger.info("TryingToGetUserWithMail("+mail+")");

        for (User u: users){
            if(u.getMail().equals(mail)){
                logger.warn("Found it! Is"+u);
                return u;
            }
        }

        logger.info("not found any user with"+mail);

        return null;
    }

    public List<User> findAll() {
        return this.users;
    }

    public void deleteUser(String id) {
        User t = this.getUser(id);
        if (t==null) {
            logger.warn("not found " + t);
        }
        else logger.info(t+" deleted ");

        this.users.remove(t);

    }

    @Override
    public User updateUser(User p) {
        User t = this.getUser(p.getId());

        if (t!=null) {
            logger.info(p+" rebut!!!! ");

            t.setSurnames(p.getSurnames());
            t.setName(p.getName());
            t.setBirthdate(p.getBirthdate());
            t.setMail(p.getMail());
            t.setPassword(p.getPassword());
            t.setDsaCoins(p.getDsaCoins());

            logger.info(t+" updated ");
        }
        else {
            logger.warn("not found "+p);
        }

        return t;
    }

    @Override
    public User updateUserCoins(User p) {
        User t = this.getUser(p.getId());

        if (t!=null) {
            logger.info(p+" rebut!!!! ");
            t.setDsaCoins(p.getDsaCoins());
            logger.info(t+" updated ");
        }
        else {
            logger.warn("not found "+p);
        }

        return t;
    }

    @Override
    public List<User> sortAlpha() {
        users.sort((User u1, User u2)->u1.getSurnames().compareTo(u2.getSurnames()));
        return users;
    }

    @Override
    public Object buyObject(String name) {

        return null;
    }
}
