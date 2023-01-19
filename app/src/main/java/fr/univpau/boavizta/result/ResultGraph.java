package fr.univpau.boavizta.result;

public class ResultGraph {
    private Gwp gwp;
    private Pe pe;
    private Adp adp;

    public Gwp getGwp() {
        return gwp;
    }

    public void setGwp(Gwp gwp) {
        this.gwp = gwp;
    }

    public Pe getPe() {
        return pe;
    }

    public void setPe(Pe pe) {
        this.pe = pe;
    }

    public Adp getAdp() {
        return adp;
    }

    public void setAdp(Adp adp) {
        this.adp = adp;
    }

    public static class Gwp {
        private Float manufacture;
        private Float use;
        private String unit;

        public Float getManufacture() {
            return manufacture;
        }

        public void setManufacture(Float manufacture) {
            this.manufacture = manufacture;
        }

        public Float getUse() {
            return use;
        }

        public void setUse(Float use) {
            this.use = use;
        }

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }
    }
    public static class Pe {
        private Float manufacture;
        private Float use;
        private String unit;

        public Float getManufacture() {
            return manufacture;
        }

        public void setManufacture(Float manufacture) {
            this.manufacture = manufacture;
        }

        public Float getUse() {
            return use;
        }

        public void setUse(Float use) {
            this.use = use;
        }

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }
    }
    public static class Adp {
        private Double manufacture;
        private Double use;
        private String unit;

        public Double getManufacture() {
            return manufacture;
        }

        public void setManufacture(Double manufacture) {
            this.manufacture = manufacture;
        }

        public Double getUse() {
            return use;
        }

        public void setUse(Double use) {
            this.use = use;
        }

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }
    }
}
