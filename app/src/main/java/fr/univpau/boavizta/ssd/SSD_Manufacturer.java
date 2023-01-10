package fr.univpau.boavizta.ssd;

import java.util.ArrayList;

public class SSD_Manufacturer {

    private static ArrayList<SSD_Manufacturer> ManufacturerArrayList;
    private final int id;
    private final String name;

    public SSD_Manufacturer(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public static ArrayList<SSD_Manufacturer> getManufacturerArrayList() {
        return ManufacturerArrayList;
    }

    public static String getNameOfValue(int value) {
        return ManufacturerArrayList.get(value).getName();
    }

    public static String[] manifacturerNames() {
        String[] names = new String[ManufacturerArrayList.size()];
        for (SSD_Manufacturer m : ManufacturerArrayList) {
            names[m.getId()] = m.getName();
        }
        return names;
    }

    public static void setManufacturerArrayList(ArrayList<SSD_Manufacturer> manufacturerArrayList) {
        ManufacturerArrayList = manufacturerArrayList;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
