package edu.upc.dsa;

import edu.upc.dsa.models.*;

import java.util.*;

import org.apache.log4j.Logger;

import static java.util.stream.Collectors.toList;

public class ShopManagerImpl implements ShopManager {
    private static ShopManager instance;

    protected Map<String, User> users; //DNI
    protected List<Objecte> objectes;

    final static Logger logger = Logger.getLogger(ShopManagerImpl.class);

    ShopManagerImpl() {
        this.users = new HashMap<>();
        this.objectes= new ArrayList<>();
    }

    public static ShopManager getInstance() {
        if (instance==null) instance = new ShopManagerImpl();
        return instance;
    }


    @Override
    public User addUser(VOuser vo) {
        logger.info("Trying to create new User: " + vo);

        if (getUserByMail(vo.getMail()) == null) {
            User u = new User(vo);
            this.users.put(vo.getDni(), u);
            logger.info("New User added ID = "+vo.getDni());

            return u;
        }
        logger.warn("Could not add new User bc it already exists user with that mail");

        return null;
    }

    public String getUser(VOcredencials credencials) {
        logger.info("Trying to get User "+credencials);
        
        String iduser = getUserByMail(credencials.getMail());
        if(iduser!=null)
            if (this.users.get(iduser).getPassword().equals(credencials.getPass())){
                logger.info("User Found: "+this.users.get(iduser));
                return iduser;
            }

        logger.warn("User Not Found "+credencials);
        return null;
                

        /*
        for (Map.Entry<String, User> entry : this.users.entrySet()) {
            if (entry.getValue().autentification(credencials)==0){
                logger.info("User Found: "+entry.getValue());
                return entry.getKey();
            }
        }
        */
        
    }

    @Override
    public VOcredencials getCredentials(User u) {
        return new VOcredencials(u);
    }


    @Override
    public List<Objecte> getObjectes(User u) {
        return u.getObjects();
    }

    @Override
    public String getUserByMail(String mail) {
        logger.info("Trying to get User with mail "+mail);

        for (Map.Entry<String, User> entry : this.users.entrySet()) {
            if (entry.getValue().getMail().equals(mail)){
                logger.info("User Found: "+entry.getValue());

                return entry.getKey();
            }
        }
        logger.warn("Not found any user with "+mail);

        return null;
    }

    public List<User> getAllUsers() {
        List<User> list = this.users.values().stream().collect(toList());
        //List<User> list = this.users.values().stream()
        //  .map(foo -> foo.deepCopy())
        //  .collect(toCollection(List::new));
        return list;
    }

/*
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

 */

    @Override
    public List<User> sortAlpha() {
        if(sizeUsers()==0){
            logger.info("Unavailable sort");
            return null;
        }
        List<User> list = new ArrayList<>(this.users.values());

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

        String u = getUser(credecinals);
        if(u==null){
            logger.warn("Failed login");

            return null;
        }
        logger.info("Successful login! Nice you are in");

        return users.get(u);
    }

    @Override
    public Object buyObject(VOcredencials credencials, String objecte) {
        Objecte lukcompro = getObject(objecte);

        if(lukcompro==null){
            logger.info("Invalid object");
            return null;
        }
        
        String i = getUser(credencials);
        if (i==null){
            logger.info("Invalid user");
            return null;
        }
        
        Objecte compraHecha = this.users.get(i).buyObject(lukcompro);
        if (compraHecha!=null){
            logger.info("New compra hecha "+compraHecha+"\nNice contribution to capitalism");
            return lukcompro;
        }
        logger.info("No tienes suficiente saldo");
        
        return null;
    }





    public int sizeUsers() {
        int ret = this.users.size();
        logger.info("There are " + ret+" users");

        return ret;
    }

    public int sizeObjectes() {
        int ret = this.objectes.size();
        logger.info("There are " + ret+" objectes");

        return ret;
    }


    @Override
    public Objecte addObject(Objecte vOobjecte) {
        logger.info("Trying to create new Object " + vOobjecte);

        if(getObject(vOobjecte.getName())==null){
            this.objectes.add(vOobjecte);
            logger.info("New Object added = "+vOobjecte);

            return vOobjecte;
        }
        logger.info("Could not add new User bc it already exists user with that name");

        return null;
    }


    @Override
    public Objecte getObject(String name) {
        logger.info("Getting "+name);

        for(Objecte o: this.objectes){
            if(o.getName().equals(name)){
                logger.info("Found " + o);
                return o;
            }
        }
        logger.info("Unfound Object " + name);
        return null;
    }

    @Override
    public User getUser(String id) {
        return this.users.get(id);
    }

    public List<Objecte> getAllObjectes() {
        return this.objectes;
    }




    @Override
    public List<Objecte> sortNumObjectes() {
        if(sizeObjectes()==0){
            logger.info("Unavailable sort");
            return null;
        }
        objectes.sort((Objecte o1, Objecte o2) -> Integer.compare(o2.getDsaCoins(),(o1.getDsaCoins())));

        return this.objectes;
    }
    /*
    @Override
    public User updateUser(User t) {
        return null;
    }

    @Override
    public Objecte deleteObject(String name) {
        Objecte t = this.getObject(name);
        if (t==null) {
            logger.warn("not found ");
            return null;
        }
        else logger.info(t+" deleted ");

        this.objectes.remove(t);
        return t
    }
     */

}
