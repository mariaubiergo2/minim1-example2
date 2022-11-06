package edu.upc.dsa.models;

import edu.upc.dsa.util.RandomUtils;

public class Objecte {

    String id;
    String name;
    String description;

    int dsaCoins;

    static int lastId;

    public Objecte() {
        this.id = RandomUtils.getId();
    }

    public Objecte(String name, String description, int coins) {
        this();
        this.setDescription(description);
        this.setName(name);
        this.setDsaCoins(coins);
    }

    public String getId() {
        return this.id;
    }
    public void setId(String id) {
        this.id=id;
    }


    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public int getDsaCoins() {
        return dsaCoins;
    }
    public void setDsaCoins(int p) {
        this.dsaCoins = p;
    }

    @Override
    public String toString() {
        return "Track [id="+id+", name=" + name + ", description=" + description +", dsaCoins=" + dsaCoins +"]";
    }

}