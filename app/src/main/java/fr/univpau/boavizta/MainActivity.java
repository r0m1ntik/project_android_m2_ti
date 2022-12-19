package fr.univpau.boavizta;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;

import fr.univpau.boavizta.cpu.Architecture;
import fr.univpau.boavizta.ram.RAM_Manufacturer;
import fr.univpau.boavizta.ssd.SSD_Manufacturer;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Function mFunction;
    private Intent mIntent;
    private Intent mErrorIntent;
    // CPU
    private NumberPicker mNumberPickerCpuNb;
    private NumberPicker mNumberPickerCpuCoreUnit;
    private NumberPicker mNumberPickerCpuArchitecture;
    // RAM
    private NumberPicker mNumberPickerRamNb;
    private NumberPicker mNumberPickerRamCapacity;
    private NumberPicker mNumberPickerRamManufacturer;
    // SSD
    private NumberPicker mNumberPickerSsdNb;
    private NumberPicker mNumberPickerSsdCapacity;
    private NumberPicker mNumberPickerSsdManufacturer;
    // HDD
    private NumberPicker mNumberPickerHddNb;
    private NumberPicker mNumberPickerHddCapacity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("onCreate()");
        setContentView(R.layout.activity_main);
        mFunction = new Function();
    }

    @Override
    protected void onStart() {
        super.onStart();
        System.out.println("onStart()");
        mIntent = new Intent(MainActivity.this, NoConnectionInternet.class);
        mErrorIntent = new Intent(MainActivity.this, Error.class);

        ImageView BoaviztaButtonHeader = findViewById(R.id.BoaviztaButtonHeader);
        TextView BoaviztaTextHeader = findViewById(R.id.BoaviztaTextHeader);

        BoaviztaButtonHeader.setOnClickListener(this);
        BoaviztaTextHeader.setOnClickListener(this);

        if (Architecture.getArchitectureArrayList().isEmpty()) {
            mErrorIntent.putExtra("error_info", "<CPU> Couldn't get json from server.");
            finish();
        } else if (RAM_Manufacturer.getManufacturerArrayList().isEmpty()) {
            mErrorIntent.putExtra("error_info", "<RAM> Couldn't get json from server.");
            finish();
        } else if (SSD_Manufacturer.getManufacturerArrayList().isEmpty()) {
            mErrorIntent.putExtra("error_info", "<SSD> Couldn't get json from server.");
            finish();
        } else {
            loadingData();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println("onDestroy()");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        System.out.println("onRestart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        System.out.println("onPause()");
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        if (!mFunction.isNetworkAvailable(getApplication())) {
            startActivity(mIntent);
            finish();
        } else {
            onStart();
        }
    }

    @Override
    public void onClick(View v) {
        // en cliquant sur le logo ou le nom on va ouvrir la page web de Boavizta
        if (mFunction.isNetworkAvailable(getApplication())) {
            startActivity(mIntent);
            finish();
        } else {
            startActivity(new Intent(MainActivity.this, NoConnectionInternet.class));
            finish();
        }
    }

    private void loadingData() {
        // Initialisation par default CPU
        mNumberPickerCpuNb = findViewById(R.id.cpu_qt_np);
        mNumberPickerCpuNb.setMaxValue(1000);
        mNumberPickerCpuNb.setMinValue(1);
        mNumberPickerCpuNb.setValue(2);

        mNumberPickerCpuCoreUnit = findViewById(R.id.cpu_core_unit);
        mNumberPickerCpuCoreUnit.setMaxValue(1000);
        mNumberPickerCpuCoreUnit.setMinValue(1);
        mNumberPickerCpuCoreUnit.setValue(16);

        mNumberPickerCpuArchitecture = findViewById(R.id.cpu_name_architecture);
        mNumberPickerCpuArchitecture.setMinValue(0);
        mNumberPickerCpuArchitecture.setMaxValue(Architecture.getArchitectureArrayList().size() - 1);
        mNumberPickerCpuArchitecture.setDisplayedValues(Architecture.architectureNames());


        // Initialisation par default RAM
        mNumberPickerRamNb = findViewById(R.id.ram_quantite);
        mNumberPickerRamNb.setMaxValue(1000);
        mNumberPickerRamNb.setMinValue(1);
        mNumberPickerRamNb.setValue(4);

        mNumberPickerRamCapacity = findViewById(R.id.ram_qt_capacity);
        mNumberPickerRamCapacity.setMaxValue(1000);
        mNumberPickerRamCapacity.setMinValue(1);
        mNumberPickerRamCapacity.setValue(32);

        mNumberPickerRamManufacturer = findViewById(R.id.ram_name_manufacturer);
        mNumberPickerRamManufacturer.setMinValue(0);
        mNumberPickerRamManufacturer.setMaxValue(RAM_Manufacturer.getManufacturerArrayList().size() - 1);
        mNumberPickerRamManufacturer.setDisplayedValues(RAM_Manufacturer.manifacturerNames());

        // Initialisation par default SSD
        mNumberPickerSsdNb = findViewById(R.id.ssd_nb_quantite);
        mNumberPickerSsdNb.setMinValue(0);
        mNumberPickerSsdNb.setMaxValue(1000);
        mNumberPickerSsdNb.setValue(4);

        mNumberPickerSsdCapacity = findViewById(R.id.ssd_nb_capacity);
        mNumberPickerSsdCapacity.setMinValue(1);
        mNumberPickerSsdCapacity.setMaxValue(100000);
        mNumberPickerSsdCapacity.setValue(1000);

        mNumberPickerSsdManufacturer = findViewById(R.id.ssd_name_manufacturer);
        mNumberPickerSsdManufacturer.setMinValue(0);
        mNumberPickerSsdManufacturer.setMaxValue(SSD_Manufacturer.getManufacturerArrayList().size() - 1);
        mNumberPickerSsdManufacturer.setDisplayedValues(SSD_Manufacturer.manifacturerNames());

        // Initialisation par default HDD
        mNumberPickerHddNb = findViewById(R.id.hdd_nb_quantite);
        mNumberPickerHddNb.setMinValue(0);
        mNumberPickerHddNb.setMaxValue(1000);
        mNumberPickerHddNb.setValue(2);

        mNumberPickerHddCapacity = findViewById(R.id.hdd_nb_capacity);
        mNumberPickerHddCapacity.setMinValue(0);
        mNumberPickerHddCapacity.setMaxValue(100000);
        mNumberPickerHddCapacity.setValue(1000);
    }
}