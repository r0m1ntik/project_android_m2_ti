package fr.univpau.boavizta.ram;

import java.util.ArrayList;

public class Manufacturer {

    private static ArrayList<Manufacturer> ManufacturerArrayList;
    private int id;
    private String name;

    public Manufacturer(int id, String name) {
        this.id = id;
        this.name = name;
    }

    // initialisation de la liste (hors ligne)
    public static void Init() {
        ManufacturerArrayList = new ArrayList<>();
        ManufacturerArrayList.add(new Manufacturer(0, "Samsung"));
        ManufacturerArrayList.add(new Manufacturer(1, "SK hynix"));
        ManufacturerArrayList.add(new Manufacturer(2, "Micro"));
        ManufacturerArrayList.add(new Manufacturer(3, "Samsung"));
        ManufacturerArrayList.add(new Manufacturer(4, "SK hynix"));
        ManufacturerArrayList.add(new Manufacturer(5, "Micro"));
    }

    public static ArrayList<Manufacturer> getManufacturerArrayList() {
        return ManufacturerArrayList;
    }

    public static String[] manifacturerNames() {
        String[] names = new String[ManufacturerArrayList.size()];
        for (Manufacturer m : ManufacturerArrayList) {
            names[m.getId()] = m.getName();
        }
        return names;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
