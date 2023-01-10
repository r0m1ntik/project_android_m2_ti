package fr.univpau.boavizta.ram;
import java.util.ArrayList;

public class RAM_Manufacturer {

    private static ArrayList<RAM_Manufacturer> ManufacturerArrayList;
    private final int id;
    private final String name;

    public RAM_Manufacturer(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public static ArrayList<RAM_Manufacturer> getManufacturerArrayList() {
        return ManufacturerArrayList;
    }

    public static String[] manifacturerNames() {
        String[] names = new String[ManufacturerArrayList.size()];
        for (RAM_Manufacturer m : ManufacturerArrayList) {
            names[m.getId()] = m.getName();
        }
        return names;
    }

    public static String getNameOfValue(int value) {
        return ManufacturerArrayList.get(value).getName();
    }

    public static void setManufacturerArrayList(ArrayList<RAM_Manufacturer> manufacturerArrayList) {
        ManufacturerArrayList = manufacturerArrayList;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
