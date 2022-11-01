package edu.upc.dsa.models;

import edu.upc.dsa.util.RandomUtils;

public class Object {

    String id;
    String name;
    String description;

    int dsaCoins;

    static int lastId;

    public Object() {
        this.id = RandomUtils.getId();
    }

    public Object(String name, String description) {
        this();
        this.setDescription(description);
        this.setName(name);
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