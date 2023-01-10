package fr.univpau.boavizta;

import android.app.Application;
import android.widget.NumberPicker;

public class App extends Application {

    // CPU
    private NumberPicker numberPickerCpuNb;
    private NumberPicker numberPickerCpuCoreUnit;
    private NumberPicker numberPickerCpuArchitecture;
    // RAM
    private NumberPicker numberPickerRamNb;
    private NumberPicker numberPickerRamCapacity;
    private NumberPicker numberPickerRamManufacturer;
    // SSD
    private NumberPicker numberPickerSsdNb;
    private NumberPicker numberPickerSsdCapacity;
    private NumberPicker numberPickerSsdManufacturer;
    // HDD
    private NumberPicker numberPickerHddNb;
    private NumberPicker numberPickerHddCapacity;



    public NumberPicker getNumberPickerCpuNb() {
        return numberPickerCpuNb;
    }

    public void setNumberPickerCpuNb(NumberPicker numberPickerCpuNb) {
        this.numberPickerCpuNb = numberPickerCpuNb;
    }

    public NumberPicker getNumberPickerCpuCoreUnit() {
        return numberPickerCpuCoreUnit;
    }

    public void setNumberPickerCpuCoreUnit(NumberPicker numberPickerCpuCoreUnit) {
        this.numberPickerCpuCoreUnit = numberPickerCpuCoreUnit;
    }

    public NumberPicker getNumberPickerCpuArchitecture() {
        return numberPickerCpuArchitecture;
    }

    public void setNumberPickerCpuArchitecture(NumberPicker numberPickerCpuArchitecture) {
        this.numberPickerCpuArchitecture = numberPickerCpuArchitecture;
    }

    public NumberPicker getNumberPickerRamNb() {
        return numberPickerRamNb;
    }

    public void setNumberPickerRamNb(NumberPicker numberPickerRamNb) {
        this.numberPickerRamNb = numberPickerRamNb;
    }

    public NumberPicker getNumberPickerRamCapacity() {
        return numberPickerRamCapacity;
    }

    public void setNumberPickerRamCapacity(NumberPicker numberPickerRamCapacity) {
        this.numberPickerRamCapacity = numberPickerRamCapacity;
    }

    public NumberPicker getNumberPickerRamManufacturer() {
        return numberPickerRamManufacturer;
    }

    public void setNumberPickerRamManufacturer(NumberPicker numberPickerRamManufacturer) {
        this.numberPickerRamManufacturer = numberPickerRamManufacturer;
    }

    public NumberPicker getNumberPickerSsdNb() {
        return numberPickerSsdNb;
    }

    public void setNumberPickerSsdNb(NumberPicker numberPickerSsdNb) {
        this.numberPickerSsdNb = numberPickerSsdNb;
    }

    public NumberPicker getNumberPickerSsdCapacity() {
        return numberPickerSsdCapacity;
    }

    public void setNumberPickerSsdCapacity(NumberPicker numberPickerSsdCapacity) {
        this.numberPickerSsdCapacity = numberPickerSsdCapacity;
    }

    public NumberPicker getNumberPickerSsdManufacturer() {
        return numberPickerSsdManufacturer;
    }

    public void setNumberPickerSsdManufacturer(NumberPicker numberPickerSsdManufacturer) {
        this.numberPickerSsdManufacturer = numberPickerSsdManufacturer;
    }

    public NumberPicker getNumberPickerHddNb() {
        return numberPickerHddNb;
    }

    public void setNumberPickerHddNb(NumberPicker numberPickerHddNb) {
        this.numberPickerHddNb = numberPickerHddNb;
    }

    public NumberPicker getNumberPickerHddCapacity() {
        return numberPickerHddCapacity;
    }

    public void setNumberPickerHddCapacity(NumberPicker numberPickerHddCapacity) {
        this.numberPickerHddCapacity = numberPickerHddCapacity;
    }
}
