package fr.univpau.boavizta;

import android.app.Application;
import android.widget.NumberPicker;
import android.widget.TextView;

import fr.univpau.boavizta.result.ResultGraph;

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

    private TextView chart1_txt;
    private TextView chart2_txt;
    private TextView chart3_txt;

    private TextView kgCO2eqTotal;
    private TextView MJTotal;
    private TextView kgSbeqTotal;

    private TextView gwp_usage;
    private TextView gwp_manufacturing;
    private TextView pe_usage;
    private TextView pe_manufacturing;
    private TextView adp_manufacturing;
    private TextView adp_usage;

    public TextView getGwp_usage() {
        return gwp_usage;
    }

    public void setGwp_usage(TextView gwp_usage) {
        this.gwp_usage = gwp_usage;
    }

    public TextView getGwp_manufacturing() {
        return gwp_manufacturing;
    }

    public void setGwp_manufacturing(TextView gwp_manufacturing) {
        this.gwp_manufacturing = gwp_manufacturing;
    }

    public TextView getPe_usage() {
        return pe_usage;
    }

    public void setPe_usage(TextView pe_usage) {
        this.pe_usage = pe_usage;
    }

    public TextView getPe_manufacturing() {
        return pe_manufacturing;
    }

    public void setPe_manufacturing(TextView pe_manufacturing) {
        this.pe_manufacturing = pe_manufacturing;
    }

    public TextView getAdp_manufacturing() {
        return adp_manufacturing;
    }

    public void setAdp_manufacturing(TextView adp_manufacturing) {
        this.adp_manufacturing = adp_manufacturing;
    }

    public TextView getAdp_usage() {
        return adp_usage;
    }

    public void setAdp_usage(TextView adp_usage) {
        this.adp_usage = adp_usage;
    }

    public TextView getKgCO2eqTotal() {
        return kgCO2eqTotal;
    }

    public void setKgCO2eqTotal(TextView kgCO2eqTotal) {
        this.kgCO2eqTotal = kgCO2eqTotal;
    }

    public TextView getMJTotal() {
        return MJTotal;
    }

    public void setMJTotal(TextView MJTotal) {
        this.MJTotal = MJTotal;
    }

    public TextView getKgSbeqTotal() {
        return kgSbeqTotal;
    }

    public void setKgSbeqTotal(TextView kgSbeqTotal) {
        this.kgSbeqTotal = kgSbeqTotal;
    }

    public TextView getChart1_txt() {
        return chart1_txt;
    }

    public void setChart1_txt(TextView chart1_txt) {
        this.chart1_txt = chart1_txt;
    }

    public TextView getChart2_txt() {
        return chart2_txt;
    }

    public void setChart2_txt(TextView chart2_txt) {
        this.chart2_txt = chart2_txt;
    }

    public TextView getChart3_txt() {
        return chart3_txt;
    }

    public void setChart3_txt(TextView chart3_txt) {
        this.chart3_txt = chart3_txt;
    }

    private ResultGraph resultGraph;

    public ResultGraph getResultGraph() {
        return resultGraph;
    }

    public void setResultGraph(ResultGraph resultGraph) {
        this.resultGraph = resultGraph;
    }

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
