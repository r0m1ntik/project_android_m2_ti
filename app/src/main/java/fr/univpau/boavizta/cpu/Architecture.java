package fr.univpau.boavizta.cpu;

import java.util.ArrayList;

public class Architecture {

    private static ArrayList<Architecture> ArchitectureArrayList;

    private int id;
    private String name;

    public Architecture(int id, String name) {
        this.id = id;
        this.name = name;
    }

    // initialisation de la liste (hors ligne)
    public static void Init() {
        ArchitectureArrayList = new ArrayList<>();
        ArchitectureArrayList.add(new Architecture(0, "skylake"));
        ArchitectureArrayList.add(new Architecture(1, "coffe lake"));
        ArchitectureArrayList.add(new Architecture(2, "broadwell"));
        ArchitectureArrayList.add(new Architecture(3, "haswell"));
        ArchitectureArrayList.add(new Architecture(4, "ivy bridge"));
        ArchitectureArrayList.add(new Architecture(5, "sandy bridge"));
        ArchitectureArrayList.add(new Architecture(6, "ice lake"));
        ArchitectureArrayList.add(new Architecture(7, "kaby lake"));
        ArchitectureArrayList.add(new Architecture(8, "zen2"));
        ArchitectureArrayList.add(new Architecture(9, "zen3"));
        ArchitectureArrayList.add(new Architecture(10, "graviton2"));
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
