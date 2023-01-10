package fr.univpau.boavizta.result;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

import java.io.BufferedInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import fr.univpau.boavizta.App;
import fr.univpau.boavizta.Error;
import fr.univpau.boavizta.Function;
import fr.univpau.boavizta.HttpHandler;
import fr.univpau.boavizta.NoConnectionInternet;
import fr.univpau.boavizta.R;
import fr.univpau.boavizta.cpu.Architecture;
import fr.univpau.boavizta.ram.RAM_Manufacturer;
import fr.univpau.boavizta.ssd.SSD_Manufacturer;

public class ResultJson extends AppCompatActivity {

    private Intent mErrorIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        // Error http request Intent
        mErrorIntent = new Intent(ResultJson.this, Error.class);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent mIntentConnection = new Intent(ResultJson.this, NoConnectionInternet.class);
        // Check internet connection
        Function mFunction = new Function();
        if (!mFunction.isNetworkAvailable(getApplication())) {
            startActivity(mIntentConnection);
            finish();
        } else {
            new JsonSend().execute();
        }
    }

    @SuppressWarnings("deprecation")
    @SuppressLint("StaticFieldLeak")
    public final class JsonSend extends AsyncTask<String, Void, String> {

        Dialog mDialog;
        private static final String JSON = "https://uppa.api.boavizta.org/v1/server/?verbose=false";

        @Override
        protected void onPreExecute() {
            // display a progress dialog for good user experience
            mDialog = new Dialog(ResultJson.this, android.R.style.Theme_Black_NoTitleBar_Fullscreen);
            mDialog.requestWindowFeature(Window.FEATURE_ACTION_BAR);
            mDialog.setContentView(R.layout.activity_splash);
            mDialog.setCancelable(false);
            mDialog.show();
        }

        @Override
        protected String doInBackground(String... strings) {
            HttpURLConnection conn;
            try {
                URL url = new URL(JSON);
                conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
                conn.setRequestProperty("Accept","application/json");
                conn.setDoOutput(true);
                conn.setDoInput(true);

                Gson gson = new Gson();
                Bio bio = new Bio();

                // CPU
                bio.getConfiguration().getCpu().setUnits(((App) getApplication()).getNumberPickerCpuNb().getValue());
                bio.getConfiguration().getCpu().setCore_units(((App) getApplication()).getNumberPickerCpuCoreUnit().getValue());
                bio.getConfiguration().getCpu().setFamily(Architecture.getNameOfValue(((App) getApplication()).getNumberPickerCpuArchitecture().getValue()));
                // RAM
                bio.getConfiguration().getRam(0).setUnits(((App) getApplication()).getNumberPickerRamNb().getValue());
                bio.getConfiguration().getRam(0).setCapacity(((App) getApplication()).getNumberPickerRamCapacity().getValue());
                bio.getConfiguration().getRam(0).setManufacturer(RAM_Manufacturer.getNameOfValue(((App) getApplication()).getNumberPickerRamManufacturer().getValue()));
                // SSD
                bio.getConfiguration().getDisk(0).setUnits(((App) getApplication()).getNumberPickerSsdNb().getValue());
                bio.getConfiguration().getDisk(0).setCapacity(((App) getApplication()).getNumberPickerSsdCapacity().getValue());
                bio.getConfiguration().getDisk(0).setType("SSD");
                bio.getConfiguration().getDisk(0).setManufacturer(SSD_Manufacturer.getNameOfValue(((App) getApplication()).getNumberPickerSsdManufacturer().getValue()));
                // HDD
                bio.getConfiguration().getDisk(1).setUnits(((App) getApplication()).getNumberPickerHddNb().getValue());
                bio.getConfiguration().getDisk(1).setCapacity(((App) getApplication()).getNumberPickerHddCapacity().getValue());
                bio.getConfiguration().getDisk(1).setType("HDD");

                String json = gson.toJson(bio);
                DataOutputStream os = new DataOutputStream(conn.getOutputStream());
                os.writeBytes(json);
                System.out.println(json);

                Log.i("STATUS", String.valueOf(conn.getResponseCode()));
                Log.i("MSG" , conn.getResponseMessage());

                if (conn.getResponseMessage().equals("OK")) {
                    String response;
                    InputStream in = new BufferedInputStream(conn.getInputStream());
                    response = HttpHandler.convertStreamToString(in);
                    Log.i("RESPONSE", response);
                } else {
                    return String.valueOf(conn.getResponseCode());
                }
                conn.disconnect();
            } catch (IOException e) {
                return e.getMessage();
            }
            return null;
        }

        @Override
        protected void onPostExecute(@Nullable String result) {
            mDialog.dismiss();
            if (result != null) {
                mErrorIntent.putExtra("error_info", result);
                startActivity(mErrorIntent);
                finish();
            }
        }
    }
}
