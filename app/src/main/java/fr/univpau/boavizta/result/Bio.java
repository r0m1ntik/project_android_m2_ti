package fr.univpau.boavizta.result;

public class Bio {
    private Model model;
    private Configuration configuration;
    private Usage usage;

    public Bio() {
        model = new Model();
        configuration = new Configuration();
        usage = new Usage();
    }

    public static class Model {
        private final String type = "rack";

        public String getType() {
            return type;
        }
    }

    public class Configuration {

        public Configuration() {
            cpu = new Cpu();
            ram = new Ram[] { new Ram() };
            disk = new Disk[] { new Disk(), new Disk() };
            power_supply = new Power_supply();
        }

        private Cpu cpu;
        private Ram[] ram;
        private Disk[] disk;
        private Power_supply power_supply;

        public class Cpu {
            private int core_units;
            private int units;
            private String family;

            public int getCore_units() {
                return core_units;
            }

            public void setCore_units(int core_units) {
                this.core_units = core_units;
            }

            public int getUnits() {
                return units;
            }

            public void setUnits(int units) {
                this.units = units;
            }

            public String getFamily() {
                return family;
            }

            public void setFamily(String family) {
                this.family = family;
            }
        }

        public class Ram {
            private int units;
            private int capacity;
            private String manufacturer;

            public int getUnits() {
                return units;
            }

            public void setUnits(int units) {
                this.units = units;
            }

            public int getCapacity() {
                return capacity;
            }

            public void setCapacity(int capacity) {
                this.capacity = capacity;
            }

            public String getManufacturer() {
                return manufacturer;
            }

            public void setManufacturer(String manufacturer) {
                this.manufacturer = manufacturer;
            }
        }

        public class Disk {
            private int units;
            private String type;
            private int capacity;
            private String manufacturer;

            public int getUnits() {
                return units;
            }

            public void setUnits(int units) {
                this.units = units;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public int getCapacity() {
                return capacity;
            }

            public void setCapacity(int capacity) {
                this.capacity = capacity;
            }

            public String getManufacturer() {
                return manufacturer;
            }

            public void setManufacturer(String manufacturer) {
                this.manufacturer = manufacturer;
            }
        }

        public class Power_supply {
            private final int units = 2;
            private final int unit_weight = 4;

            public int getUnits() {
                return units;
            }

            public int getUnit_weight() {
                return unit_weight;
            }
        }

        public Cpu getCpu() {
            return cpu;
        }

        public void setCpu(Cpu cpu) {
            this.cpu = cpu;
        }

        public Ram[] getRam() {
            return ram;
        }

        public Ram getRam(int i) { return ram[i]; }

        public void setRam(Ram[] ram) {
            this.ram = ram;
        }

        public Disk[] getDisk() {
            return disk;
        }

        public Disk getDisk(int i) { return disk[i]; }

        public void setDisk(Disk[] disk) {
            this.disk = disk;
        }

        public Power_supply getPower_supply() {
            return power_supply;
        }

        public void setPower_supply(Power_supply power_supply) {
            this.power_supply = power_supply;
        }
    }

    public class Usage {
        private final int days_use_time = 1;
        private final int hours_use_time = 1;
        private final int years_use_time = 1;
        private final String usage_location = "FRA";
        private final int hours_electrical_consumption = 300;

        public int getDays_use_time() {
            return days_use_time;
        }

        public int getHours_use_time() {
            return hours_use_time;
        }

        public int getYears_use_time() {
            return years_use_time;
        }

        public String getUsage_location() {
            return usage_location;
        }

        public int getHours_electrical_consumption() {
            return hours_electrical_consumption;
        }
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }

    public Usage getUsage() {
        return usage;
    }

    public void setUsage(Usage usage) {
        this.usage = usage;
    }
}
