package edu.upc.dsa;

import edu.upc.dsa.models.Objecte;
import edu.upc.dsa.models.User;
import edu.upc.dsa.models.VOcredencials;
import edu.upc.dsa.models.VOuser;
import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class ShopManagerImplTest {
    ShopManager sm;

    @Before
    public void setUp() {
        sm = new ShopManagerImpl();

        sm.addUser(new VOuser("2175R","maria","ubiergo gomez", "avui", "meri@gmail", "gats"));
        sm.addUser(new VOuser("475R","laia", "Luis Fonsi", "dema","lia@lalia", "gossos"));
        sm.addUser(new VOuser("5R","Biel",  "Luis Fonsi", "desa", "aeros@love",  "gossos"));

        sm.addObject(new Objecte("taula", "te potes", 50));
        sm.addObject(new Objecte("jerro", "trencat", 2));
        sm.addObject(new Objecte("Play", "aparell electronic", 149));
        sm.addObject(new Objecte("Constitucio", "nothing", 9));

    }

    @After
    public void tearDown() { this.sm = null; }


    @Test
    public void addUser() {
        Assert.assertEquals(3,this.sm.sizeUsers());

        this.sm.addUser(new VOuser("33R","Mama","Zowi", "mai", "gest@love",  "gossos"));

        Assert.assertEquals(4,this.sm.sizeUsers());

    }
    @Test
    public void addObject() {
        Assert.assertEquals(4, this.sm.sizeObjectes());

        this.sm.addObject(new Objecte("PC", "util", 100));

        Assert.assertEquals(5, this.sm.sizeObjectes());
    }
    @Test
    public void sortAlpha(){
        List<User> initialusers = this.sm.getAllUsers();
        List<User> sortedusers = this.sm.sortAlpha();

        Assert.assertEquals("maria", initialusers.get(0).getName());
        Assert.assertEquals("laia", initialusers.get(1).getName());
        Assert.assertEquals("Biel", initialusers.get(2).getName());

        Assert.assertEquals("Biel", sortedusers.get(0).getName());
        Assert.assertEquals("laia", sortedusers.get(1).getName());
        Assert.assertEquals("maria", sortedusers.get(2).getName());
    }
    @Test
    public void logIn(){
        List<User> users = this.sm.getAllUsers();
        VOcredencials cred1 = this.sm.getCredentials(users.get(0));

        Assert.assertEquals(users.get(0), this.sm.logIn(cred1));

        VOcredencials cred2 = this.sm.getCredentials(users.get(0));
        cred2.setPassword("random");

        Assert.assertEquals(null, this.sm.logIn(cred2));
    }
    @Test
    public void sortObjectes(){
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
    public void boughtObjectss(){
        List<User> users = this.sm.getAllUsers();
        List<Objecte> objectes = this.sm.getAllObjectes();

        Assert.assertEquals(objectes.get(1), this.sm.buyObject(this.sm.getCredentials(users.get(0)),"jerro"));

        Assert.assertEquals(1, this.sm.getObjectes(users.get(0)).size());

        Assert.assertEquals(objectes.get(3), this.sm.buyObject(this.sm.getCredentials(users.get(0)),"Constitucio"));

        Assert.assertEquals(2, this.sm.getObjectes(users.get(0)).size());
        Assert.assertEquals(39, users.get(0).getDsaCoins());

    }

}
