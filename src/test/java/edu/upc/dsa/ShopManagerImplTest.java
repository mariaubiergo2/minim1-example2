package edu.upc.dsa;

import edu.upc.dsa.models.Objecte;
import edu.upc.dsa.models.User;
import edu.upc.dsa.models.VOcredencials;
import edu.upc.dsa.models.VOuser;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.apache.log4j.Logger;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class ShopManagerImplTest {
    final static Logger logger = Logger.getLogger(ShopManagerImpl.class);
    ShopManager sm;

    @Before
    public void setUp() {
        sm = new ShopManagerImpl();

        sm.addUser(new VOuser("111","Marti","Ubiergo Gomez", "02/11/2001", "martini@gmail.com", "gats"));
        sm.addUser(new VOuser("222","Laia", "Fonsi", "11/01/2001","lia@lalia.com", "gossos"));
        sm.addUser(new VOuser("333","Biel",  "Fonsi", "01/12/2001", "aeros@love.com",  "dofins"));

        sm.addObject(new Objecte("taula", "te potes", 50));
        sm.addObject(new Objecte("jerro", "trencat", 2));
        sm.addObject(new Objecte("Play", "aparell electronic", 149));
        sm.addObject(new Objecte("prestatge", "Aguanta molt", 9));
    }

    @After
    public void tearDown() {
        this.sm = null;
        logger.info("--- End of the test ---");
    }


    @Test
    public void addUser() {
        logger.info("--- Start of the test - Add User ---");

        logger.info("Condicions inicials: ");
        Assert.assertEquals(3,this.sm.sizeUsers());

        logger.info("S'afegeix 1 usuari: Paula");
        this.sm.addUser(new VOuser("444","Paula","Zuckerberg", "23/05/2001", "paint@love.com",  "pandas"));

        Assert.assertEquals(4,this.sm.sizeUsers());

    }
    @Test
    public void addObject() {
        logger.info("--- Start of the test - Add Objecte ---");

        logger.info("Condicions inicials: ");
        Assert.assertEquals(4, this.sm.sizeObjectes());

        logger.info("S'afegeixen 2 objectes: PC i quadre");
        this.sm.addObject(new Objecte("PC", "util", 100));
        this.sm.addObject(new Objecte("quadre", "precios", 1990));

        Assert.assertEquals(6, this.sm.sizeObjectes());
    }
    @Test
    public void sortAlpha(){
        logger.info("--- Start of the test - Sort users ---");
        List<User> initialusers = this.sm.getAllUsers();

        Assert.assertEquals("Marti", initialusers.get(0).getName());
        Assert.assertEquals("Laia", initialusers.get(1).getName());
        Assert.assertEquals("Biel", initialusers.get(2).getName());

        List<User> sortedusers = this.sm.sortAlpha();

        Assert.assertEquals("Biel", sortedusers.get(0).getName());
        Assert.assertEquals("Fonsi", sortedusers.get(0).getSurnames());

        Assert.assertEquals("Laia", sortedusers.get(1).getName());
        Assert.assertEquals("Fonsi", sortedusers.get(1).getSurnames());

        Assert.assertEquals("Marti", sortedusers.get(2).getName());
        Assert.assertEquals("Ubiergo Gomez", sortedusers.get(2).getSurnames());
    }
    @Test
    public void logIn(){
        logger.info("--- Start of the test - LogIn ---");
        List<User> users = this.sm.getAllUsers();
        VOcredencials cred1 = this.sm.getCredentials(users.get(0));
        logger.info("Trying to LogIn "+cred1);

        Assert.assertEquals(users.get(0), this.sm.logIn(cred1));

        VOcredencials cred2 = this.sm.getCredentials(users.get(0));
        cred2.setPassword("random");
        logger.info("Trying to LogIn "+cred2);

        Assert.assertEquals(null, this.sm.logIn(cred2));
    }
    @Test
    public void sortObjectes(){
        logger.info("--- Start of the test - Sort Objectes ---");
        List<Objecte> initialobjectes = this.sm.getAllObjectes();

        Assert.assertEquals(50, initialobjectes.get(0).getDsaCoins());
        Assert.assertEquals(2, initialobjectes.get(1).getDsaCoins());
        Assert.assertEquals(149, initialobjectes.get(2).getDsaCoins());
        Assert.assertEquals(9, initialobjectes.get(3).getDsaCoins());

        List<Objecte> sortedobjectes = this.sm.sortNumObjectes();

        Assert.assertEquals(149, sortedobjectes.get(0).getDsaCoins());
        Assert.assertEquals(50, sortedobjectes.get(1).getDsaCoins());
        Assert.assertEquals(9, sortedobjectes.get(2).getDsaCoins());
        Assert.assertEquals(2, sortedobjectes.get(3).getDsaCoins());
    }
    @Test
    public void boughtObjects(){
        List<User> users = this.sm.getAllUsers();
        List<Objecte> objectes = this.sm.getAllObjectes();
        logger.info("--- Start of the test - Buy objects and see bought objects---");

        logger.info("El Marti compra el jerro");
        Assert.assertEquals(objectes.get(1), this.sm.buyObject(this.sm.getCredentials(users.get(0)),"jerro"));
        Assert.assertEquals(1, this.sm.getObjectes(users.get(0)).size());

        logger.info("El Marti compra el prestatge");
        Assert.assertEquals(objectes.get(3), this.sm.buyObject(this.sm.getCredentials(users.get(0)),"prestatge"));
        Assert.assertEquals(2, this.sm.getObjectes(users.get(0)).size());

        Assert.assertEquals(39, users.get(0).getDsaCoins());

        logger.info("El Marti intenta comprar la Play");
        Assert.assertEquals(null, this.sm.buyObject(this.sm.getCredentials(users.get(0)),"Play"));
        Assert.assertEquals(2, this.sm.getObjectes(users.get(0)).size());

        logger.info("El Marti te:");
        for(Objecte objecte : users.get(0).getObjects()){
            logger.info(objecte);
        }

    }

}
