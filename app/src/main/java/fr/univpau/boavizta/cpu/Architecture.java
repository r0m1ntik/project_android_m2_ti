package fr.univpau.boavizta.cpu;

import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class Architecture extends AppCompatActivity {

    private static ArrayList<Architecture> ArchitectureArrayList;
    private final int id;
    private final String name;

    public Architecture(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public static ArrayList<Architecture> getArchitectureArrayList() {
        return ArchitectureArrayList;
    }

    public static String[] architectureNames() {
        String[] names = new String[ArchitectureArrayList.size()];
        for (Architecture a : ArchitectureArrayList) {
            names[a.getId()] = a.getName();
        }
        return names;
    }

    public static void setArchitectureArrayList(ArrayList<Architecture> architectureArrayList) {
        ArchitectureArrayList = architectureArrayList;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
