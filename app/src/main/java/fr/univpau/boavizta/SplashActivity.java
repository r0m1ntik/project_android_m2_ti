package fr.univpau.boavizta;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import fr.univpau.boavizta.cpu.Architecture;
import fr.univpau.boavizta.ram.RAM_Manufacturer;
import fr.univpau.boavizta.ssd.SSD_Manufacturer;

public class SplashActivity extends AppCompatActivity {

    private Function mFunction;
    private Intent mIntentConnection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        mIntentConnection = new Intent(SplashActivity.this, NoConnectionInternet.class);

        mFunction = new Function();
        if (!mFunction.isNetworkAvailable(getApplication())) {
            startActivity(mIntentConnection);
            finish();
        } else {
            jsonloader();
        }
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        if (!mFunction.isNetworkAvailable(getApplication())) {
            startActivity(mIntentConnection);
            finish();
        }
    }

    public void jsonloader() {
        TextView textView = findViewById(R.id.CustomTextLoading);
        Handler handler = new Handler();
        handler.postDelayed(this::run, 2500);

        // chargement des données JSON avant le lancement de l'application
        textView.setText("CPU, RAM, SDD...");
        // CPU
        new Architecture.GetArchitectures().execute();
        // RAM
        new RAM_Manufacturer.GetManufacturer().execute();
        // SSD
        new SSD_Manufacturer.GetManufacturer().execute();
    }

    public void run() {
        startActivity(new Intent(SplashActivity.this, MainActivity.class));
        finish();
    }
}