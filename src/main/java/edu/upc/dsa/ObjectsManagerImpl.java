package edu.upc.dsa;

import edu.upc.dsa.models.Object;
import org.apache.log4j.Logger;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class ObjectsManagerImpl implements ObjectsManager {
    private static ObjectsManager instance;
    protected List<Object> objects;
    final static Logger logger = Logger.getLogger(ObjectsManagerImpl.class);

    private ObjectsManagerImpl() {
        this.objects = new LinkedList<>();
    }

    public static ObjectsManager getInstance() {
        if (instance==null) instance = new ObjectsManagerImpl();
        return instance;
    }

    public int size() {
        int ret = this.objects.size();
        logger.info("size " + ret);

        return ret;
    }


    @Override
    public Object createObject(String name, String description, int coins) {
        Object o = new Object(name, description, coins);
        return o;
    }

    @Override
    public Object addObject(Object t) {
        logger.info("new Object " + t);

        if(findObject(t.getName())==false){
            this.objects.add(t);
            logger.info("OBJECT ADDED "+t);
            return t;
        }
        logger.info("this object already exists");
        return null;
    }



    @Override
    public Object getObject(String name) {
        logger.info("getting "+name);
        for(Object o: this.objects){
            if(o.getName().equals(name)){
                logger.info("FOUND " + o);
                return o;
            }
        }
        logger.info("NOT FOUND " + name);
        return null;
    }

    @Override
    public boolean findObject(String name) {
        boolean res = false;
        for(int i =0; (i<objects.size() && !res); i++){
            if(name==objects.get(i).getName()){
                res = true;
            }
        }
        return res;
    }


    public List<Object> findAll() {
        return this.objects;
    }

    @Override
    public void deleteObject(String name) {
        Object t = this.getObject(name);
        if (t==null) {
            logger.warn("not found " + t);
        }
        else logger.info(t+" deleted ");

        this.objects.remove(t);

    }


    @Override
    public List<Object> sortNum() {
        Collections.sort(objects, (Object o1, Object o2)->o1.getDsaCoins().compareTo(o2.getDsaCoins()));
        //podria importar un comparable?
        return null;
    }
}
