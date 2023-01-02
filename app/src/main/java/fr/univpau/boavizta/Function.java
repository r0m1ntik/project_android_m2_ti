package fr.univpau.boavizta;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.view.View;
import android.widget.NumberPicker;

import fr.univpau.boavizta.cpu.Architecture;
import fr.univpau.boavizta.ram.RAM_Manufacturer;
import fr.univpau.boavizta.ssd.SSD_Manufacturer;

public class Function {
    // if you are connected to internet
    public Boolean isNetworkAvailable(Application application) {
        ConnectivityManager connectivityManager = (ConnectivityManager) application.getSystemService(Context.CONNECTIVITY_SERVICE);
        Network nw = connectivityManager.getActiveNetwork();
        if (nw == null)
            return false;
        NetworkCapabilities actNw = connectivityManager.getNetworkCapabilities(nw);
        return actNw != null && (actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) || actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) || actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) || actNw.hasTransport(NetworkCapabilities.TRANSPORT_BLUETOOTH));
    }
    // loading data
    public void loadingData(View v) {
        // Initialisation par default CPU
        // CPU
        NumberPicker numberPickerCpuNb = v.findViewById(R.id.cpu_qt_np);
        numberPickerCpuNb.setMaxValue(1000);
        numberPickerCpuNb.setMinValue(1);
        numberPickerCpuNb.setValue(2);

        NumberPicker numberPickerCpuCoreUnit = v.findViewById(R.id.cpu_core_unit);
        numberPickerCpuCoreUnit.setMaxValue(1000);
        numberPickerCpuCoreUnit.setMinValue(1);
        numberPickerCpuCoreUnit.setValue(16);

        NumberPicker numberPickerCpuArchitecture = v.findViewById(R.id.cpu_name_architecture);
        numberPickerCpuArchitecture.setMinValue(0);
        numberPickerCpuArchitecture.setMaxValue(Architecture.getArchitectureArrayList().size() - 1);
        numberPickerCpuArchitecture.setDisplayedValues(Architecture.architectureNames());


        // Initialisation par default RAM
        // RAM
        NumberPicker numberPickerRamNb = v.findViewById(R.id.ram_quantite);
        numberPickerRamNb.setMaxValue(1000);
        numberPickerRamNb.setMinValue(1);
        numberPickerRamNb.setValue(4);

        NumberPicker numberPickerRamCapacity = v.findViewById(R.id.ram_qt_capacity);
        numberPickerRamCapacity.setMaxValue(1000);
        numberPickerRamCapacity.setMinValue(1);
        numberPickerRamCapacity.setValue(32);

        NumberPicker numberPickerRamManufacturer = v.findViewById(R.id.ram_name_manufacturer);
        numberPickerRamManufacturer.setMinValue(0);
        numberPickerRamManufacturer.setMaxValue(RAM_Manufacturer.getManufacturerArrayList().size() - 1);
        numberPickerRamManufacturer.setDisplayedValues(RAM_Manufacturer.manifacturerNames());

        // Initialisation par default SSD
        // SSD
        NumberPicker numberPickerSsdNb = v.findViewById(R.id.ssd_nb_quantite);
        numberPickerSsdNb.setMinValue(0);
        numberPickerSsdNb.setMaxValue(1000);
        numberPickerSsdNb.setValue(4);

        NumberPicker numberPickerSsdCapacity = v.findViewById(R.id.ssd_nb_capacity);
        numberPickerSsdCapacity.setMinValue(1);
        numberPickerSsdCapacity.setMaxValue(100000);
        numberPickerSsdCapacity.setValue(1000);

        NumberPicker numberPickerSsdManufacturer = v.findViewById(R.id.ssd_name_manufacturer);
        numberPickerSsdManufacturer.setMinValue(0);
        numberPickerSsdManufacturer.setMaxValue(SSD_Manufacturer.getManufacturerArrayList().size() - 1);
        numberPickerSsdManufacturer.setDisplayedValues(SSD_Manufacturer.manifacturerNames());

        // Initialisation par default HDD
        // HDD
        NumberPicker numberPickerHddNb = v.findViewById(R.id.hdd_nb_quantite);
        numberPickerHddNb.setMinValue(0);
        numberPickerHddNb.setMaxValue(1000);
        numberPickerHddNb.setValue(2);

        NumberPicker numberPickerHddCapacity = v.findViewById(R.id.hdd_nb_capacity);
        numberPickerHddCapacity.setMinValue(0);
        numberPickerHddCapacity.setMaxValue(100000);
        numberPickerHddCapacity.setValue(1000);
    }
}
