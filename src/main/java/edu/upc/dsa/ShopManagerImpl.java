package edu.upc.dsa;

import edu.upc.dsa.models.Objecte;
import edu.upc.dsa.models.User;

import java.util.*;

import edu.upc.dsa.models.VOcredencials;
import org.apache.log4j.Logger;

import static java.util.stream.Collectors.toCollection;
import static java.util.stream.Collectors.toList;

public class ShopManagerImpl implements ShopManager {
    private static ShopManager instance;

    protected Map<Integer, User> users;
    protected List<Objecte> objectes;

    protected Integer idActual;

    final static Logger logger = Logger.getLogger(ShopManagerImpl.class);

    private ShopManagerImpl() {
        this.users = new HashMap<>();
        this.objectes= new ArrayList<>();
        this.idActual=0;
    }

    public static ShopManager getInstance() {
        if (instance==null) instance = new ShopManagerImpl();
        return instance;
    }

    public User addUser(String name, String surnames, String birthdate, String mail, String password) {
        return this.addUser(new User(name, surnames, birthdate, mail, password));
    }

    public User addUser(User t) {
        logger.info("new User " + t);

        if (getUserByMail(t.getMail()) == null) {
            this.idActual=this.idActual+1;
            this.users.put(this.idActual, t);
            logger.info("new User added");
            return t;
        }
        logger.warn("Could not add new user bc already exists user with that mail");
        return null;
    }


    public Integer getUser(VOcredencials credencials) {
        logger.info("TryingToGetUser("+credencials+")");

        for (Map.Entry<Integer, User> entry : this.users.entrySet()) {
            if (entry.getValue().authentification(credencials)==0){
                logger.info("found user: "+entry.getValue());
                return entry.getKey();
            }
        }
        logger.warn("unfound user with "+credencials);
        return -1;
        /*
        this.users.entrySet().stream()
                .filter(map -> (map.getValue().authentification(credencials)==0))
                .map(map -> map.getKey());
        logger.info("gotUser: "+t);
        return t.getKey()  ,c;
            }
        }
         */

    }

    @Override
    public User getUser(String id) {
        return this.users.get(id);
    }

    @Override
    public Integer getUserByMail(String mail) {
        logger.info("Trying to Get User With Mail "+mail);

        for (Map.Entry<Integer, User> entry : this.users.entrySet()) {
            if (entry.getValue().getMail().equals(mail)){
                logger.info("found user: "+entry.getValue());
                return entry.getKey();
            }
        }
        logger.warn("Not found any user with "+mail);
        return null;
    }

    public List<User> findAllUsers() {
        List<User> list = this.users.values().stream().collect(toList());
        //List<User> list = this.users.values().stream()
        //  .map(foo -> foo.deepCopy())
        //  .collect(toCollection(List::new));
        return list;
    }


    @Override
    public User updateUser(User newUser) { //UN USER NO POT UPDATE LES SEVES CREDENCIALS
        Integer key = this.getUser(newUser.getCredentials());

        if (key!=-1) {
            this.users.replace(key, newUser);
            logger.info("updated "+newUser);

            return newUser;
        }
        logger.warn("not found "+newUser);

        return null;
    }

    @Override
    public List<User> sortAlpha() {
        List<User> list = this.users.values().stream()
                .map(foo -> foo.deepCopy())
                .collect(toCollection(LinkedList::new));

        list.sort((User u1, User u2)-> {
            int res = u1.getSurnames().compareToIgnoreCase(u2.getSurnames());
            if (res == 0){
                res = u1.getName().compareToIgnoreCase(u2.getName());
            }
            return res;
        });
        return list;
    }

    @Override
    public User logIn(VOcredencials credecinals) {
        logger.info("LOGIN: looking for "+credecinals);
        Integer u = getUserByMail(credecinals.getMail());
        if(u==null){
            logger.warn("LOGIN: could not login");
            return null;
        }
        logger.info("LOGIN: nice you are in");
        return users.get(u);
    }

    @Override
    public Object buyObject(VOcredencials credencials, String objecte) {
        Objecte lukcompro = getObject(objecte);
        if(lukcompro==null){
            logger.info("Invalid object");
            return null;
        }
        Integer i = getUser(credencials);
        if (i==-1){
            logger.info("Invalid user");
            return null;
        }
        boolean compraHecha = this.users.get(i).buyObject(lukcompro);
        if (compraHecha){
            logger.info("Nice contribution to capitalism");
            return lukcompro;
        }
        logger.warn("no tenies suficients diners");
        return null;
    }

    public Integer deleteUser(String id) {
        User t = this.users.get(id);
        if (t==null) {
            logger.warn("not found ");
            return -1;
        }
        else logger.info("Deleted "+t);

        this.users.remove(id);
        return 0;

    }

    public int sizeUsers() {
        int ret = this.users.size();
        logger.info("size " + ret);

        return ret;
    }

    public int sizeObjectes() {
        int ret = this.objectes.size();
        logger.info("size " + ret);

        return ret;
    }


    @Override
    public Objecte addObject(String name, String description, int coins) {
        return new Objecte(name, description, coins);
    }

    @Override
    public Objecte addObject(Objecte t) {
        logger.info("new Object " + t);

        if(findObject(t.getName())==null){
            this.objectes.add(t);
            logger.info("OBJECT ADDED "+t);
            return t;
        }
        logger.info("this object already exists");
        return null;
    }


    @Override
    public Objecte getObject(String name) {
        logger.info("getting "+name);
        for(Objecte o: this.objectes){
            if(o.getName().equals(name)){
                logger.info("FOUND " + o);
                return o;
            }
        }
        logger.info("NOT FOUND " + name);
        return null;
    }

    @Override
    public Objecte findObject(String name) {
        for (Objecte ob : objectes)
            if(name.equals(ob.getName())){
                return ob;
            }
        return null;
    }


    public List<Objecte> findAllObjectes() {
        return this.objectes;
    }

    @Override
    public void deleteObject(String name) {
        Objecte t = this.getObject(name);
        if (t==null) {
            logger.warn("not found ");
        }
        else logger.info(t+" deleted ");

        this.objectes.remove(t);
    }


    @Override
    public List<Objecte> sortNumObjectes() {
        Collections.sort(objectes, (Objecte o1, Objecte o2)->Integer.compare(o1.getDsaCoins(),o2.getDsaCoins()));
        //podria importar un comparable?
        return null;
    }
}
