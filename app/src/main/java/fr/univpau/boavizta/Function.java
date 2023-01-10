package fr.univpau.boavizta;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.view.View;

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
    public void loadingData(View v, Application application)
    {
        // CPU
        ((App) application).setNumberPickerCpuNb(v.findViewById(R.id.cpu_qt_np));
        ((App) application).getNumberPickerCpuNb().setMaxValue(1000);
        ((App) application).getNumberPickerCpuNb().setMinValue(1);
        ((App) application).getNumberPickerCpuNb().setValue(2);

        ((App) application).setNumberPickerCpuCoreUnit(v.findViewById(R.id.cpu_core_unit));
        ((App) application).getNumberPickerCpuCoreUnit().setMaxValue(1000);
        ((App) application).getNumberPickerCpuCoreUnit().setMinValue(1);
        ((App) application).getNumberPickerCpuCoreUnit().setValue(16);

        ((App) application).setNumberPickerCpuArchitecture(v.findViewById(R.id.cpu_name_architecture));
        ((App) application).getNumberPickerCpuArchitecture().setMinValue(0);
        ((App) application).getNumberPickerCpuArchitecture().setMaxValue(Architecture.getArchitectureArrayList().size() - 1);
        ((App) application).getNumberPickerCpuArchitecture().setDisplayedValues(Architecture.architectureNames());

        // RAM
        ((App) application).setNumberPickerRamNb(v.findViewById(R.id.ram_quantite));
        ((App) application).getNumberPickerRamNb().setMaxValue(1000);
        ((App) application).getNumberPickerRamNb().setMinValue(1);
        ((App) application).getNumberPickerRamNb().setValue(4);

        ((App) application).setNumberPickerRamCapacity(v.findViewById(R.id.ram_qt_capacity));
        ((App) application).getNumberPickerRamCapacity().setMaxValue(1000);
        ((App) application).getNumberPickerRamCapacity().setMinValue(1);
        ((App) application).getNumberPickerRamCapacity().setValue(32);

        ((App) application).setNumberPickerRamManufacturer(v.findViewById(R.id.ram_name_manufacturer));
        ((App) application).getNumberPickerRamManufacturer().setMinValue(0);
        ((App) application).getNumberPickerRamManufacturer().setMaxValue(RAM_Manufacturer.getManufacturerArrayList().size() - 1);
        ((App) application).getNumberPickerRamManufacturer().setDisplayedValues(RAM_Manufacturer.manifacturerNames());

        // SSD
        ((App) application).setNumberPickerSsdNb(v.findViewById(R.id.ssd_nb_quantite));
        ((App) application).getNumberPickerSsdNb().setMinValue(0);
        ((App) application).getNumberPickerSsdNb().setMaxValue(1000);
        ((App) application).getNumberPickerSsdNb().setValue(4);

        ((App) application).setNumberPickerSsdCapacity(v.findViewById(R.id.ssd_nb_capacity));
        ((App) application).getNumberPickerSsdCapacity().setMinValue(1);
        ((App) application).getNumberPickerSsdCapacity().setMaxValue(100000);
        ((App) application).getNumberPickerSsdCapacity().setValue(1000);

        ((App) application).setNumberPickerSsdManufacturer(v.findViewById(R.id.ssd_name_manufacturer));
        ((App) application).getNumberPickerSsdManufacturer().setMinValue(0);
        ((App) application).getNumberPickerSsdManufacturer().setMaxValue(SSD_Manufacturer.getManufacturerArrayList().size() - 1);
        ((App) application).getNumberPickerSsdManufacturer().setDisplayedValues(SSD_Manufacturer.manifacturerNames());

        // HDD
        ((App) application).setNumberPickerHddNb(v.findViewById(R.id.hdd_nb_quantite));
        ((App) application).getNumberPickerHddNb().setMinValue(0);
        ((App) application).getNumberPickerHddNb().setMaxValue(1000);
        ((App) application).getNumberPickerHddNb().setValue(2);

        ((App) application).setNumberPickerHddCapacity(v.findViewById(R.id.hdd_nb_capacity));
        ((App) application).getNumberPickerHddCapacity().setMinValue(0);
        ((App) application).getNumberPickerHddCapacity().setMaxValue(100000);
        ((App) application).getNumberPickerHddCapacity().setValue(1000);
    }
}
