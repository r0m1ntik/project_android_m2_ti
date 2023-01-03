package fr.univpau.boavizta;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import java.util.ArrayList;
import java.util.Objects;

import fr.univpau.boavizta.cpu.Architecture;
import fr.univpau.boavizta.ssd.SSD_Manufacturer;
import fr.univpau.boavizta.ram.RAM_Manufacturer;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    // Custom functions
    private static Function mFunction;
    private Intent mIntentConnection;
    private Intent mErrorIntent;

    @SuppressWarnings("deprecation")
    @SuppressLint("StaticFieldLeak")
    public final class JsonLoader extends AsyncTask<String, Void, String> {

        private static final String CpuJSON = "https://uppa.api.boavizta.org/v1/utils/cpu_family";
        private static final String RamJSON = "https://uppa.api.boavizta.org/v1/utils/ram_manufacturer";
        private static final String SsdJSON = "https://uppa.api.boavizta.org/v1/utils/ssd_manufacturer";

        Dialog mDialog;

        private int error = 0;

        String TAG = getClass().getSimpleName();

        @Override
        protected void onPreExecute() {
            // display a progress dialog for good user experiance
            mDialog = new Dialog(MainActivity.this, android.R.style.Theme_Black_NoTitleBar_Fullscreen);
            mDialog.requestWindowFeature(Window.FEATURE_ACTION_BAR);
            mDialog.setContentView(R.layout.activity_splash);
            mDialog.setCancelable(false);
            mDialog.show();
        }

        @Override
        protected String doInBackground(String... strings) {
            HttpHandler sh = new HttpHandler();
            String jsonStrCpu = sh.makeServiceCall(CpuJSON, "GET");
            String jsonStrRam = sh.makeServiceCall(RamJSON, "GET");
            String jsonStrSsd = sh.makeServiceCall(SsdJSON, "GET");

            // LOG in console
            Log.e(TAG, "Response for CPU: " + jsonStrCpu);
            Log.e(TAG, "Response for RAM: " + jsonStrRam);
            Log.e(TAG, "Response for SSD: " + jsonStrSsd);

            // CPU INIT
            if (jsonStrCpu != null) {
                try {
                    new Handler(Looper.getMainLooper()).post(() -> {
                        JSONArray jsonArray = null;
                        try {
                            jsonArray = new JSONArray(jsonStrCpu);
                        } catch (JSONException e) {
                            e.printStackTrace();
                            error++;
                        }
                        ArrayList<Architecture> ArchitectureArrayList = new ArrayList<>();
                        for(int i = 0; i < Objects.requireNonNull(jsonArray).length(); i++) {
                            try {
                                ArchitectureArrayList.add(new Architecture(i, jsonArray.getString(i)));
                            } catch (JSONException e) {
                                e.printStackTrace();
                                error++;
                            }
                        }
                        Architecture.setArchitectureArrayList(ArchitectureArrayList);
                    });
                } catch (final Exception e) {
                    error++;
                    return TAG + "Json parsing CPU error !";
                }
            }
            // RAM INIT
            if (jsonStrRam != null) {
                try {
                    new Handler(Looper.getMainLooper()).post(() -> {
                        JSONArray jsonArray = null;
                        try {
                            jsonArray = new JSONArray(jsonStrRam);
                        } catch (JSONException e) {
                            e.printStackTrace();
                            error++;
                        }
                        ArrayList<RAM_Manufacturer> ManufacturerArrayList = new ArrayList<>();
                        for(int i = 0; i < Objects.requireNonNull(jsonArray).length(); i++) {
                            try {
                                ManufacturerArrayList.add(new RAM_Manufacturer(i, jsonArray.getString(i)));
                            } catch (JSONException e) {
                                error++;
                                e.printStackTrace();
                            }
                        }
                        // HACK FIX TODO
                        int y = 0;
                        for(int i = jsonArray.length(); i < jsonArray.length() + jsonArray.length(); i++, y++) {
                            try {
                                ManufacturerArrayList.add(new RAM_Manufacturer(i, jsonArray.getString(y)));
                            } catch (JSONException e) {
                                error++;
                                e.printStackTrace();
                            }
                        }
                        RAM_Manufacturer.setManufacturerArrayList(ManufacturerArrayList);
                    });
                } catch (final Exception e) {
                    error++;
                    return TAG + e.getMessage();
                }
            }
            // SSD INIT
            if (jsonStrSsd != null) {
                try {
                    JSONArray jsonArray = new JSONArray(jsonStrSsd);
                    ArrayList<SSD_Manufacturer> ManufacturerArrayList = new ArrayList<>();
                    for(int i = 0; i < jsonArray.length(); i++) {
                        ManufacturerArrayList.add(new SSD_Manufacturer(i, jsonArray.getString(i)));
                    }
                    // HACK FIX TODO
                    int y = 0;
                    for(int i = jsonArray.length(); i < jsonArray.length() + jsonArray.length(); i++, y++) {
                        ManufacturerArrayList.add(new SSD_Manufacturer(i, jsonArray.getString(y)));
                    }
                    SSD_Manufacturer.setManufacturerArrayList(ManufacturerArrayList);
                } catch (final Exception e) {
                    error++;
                    return TAG + e.getMessage();
                }
            }

            if (jsonStrSsd == null || jsonStrCpu == null || jsonStrRam == null) {
                error++;
                return "CPU or SSD or RAM is empty ! Check the links used for https requests.";
            }
            return null;
        }

        @Override
        protected void onPostExecute(@Nullable String result) {
            if (error == 0) {
                // loading data
                mFunction.loadingData(getWindow().getDecorView());
                mDialog.dismiss();
            } else {
                Log.e(TAG, result);
                mErrorIntent.putExtra("error_info", result);
                startActivity(mErrorIntent);
                finish();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        System.out.println("MainActivity::onCreate()");

        // Loading custom functions
        mFunction = new Function();

        // No connection to internet Intent
        mIntentConnection = new Intent(MainActivity.this, NoConnectionInternet.class);
        // Error http request Intent
        mErrorIntent = new Intent(MainActivity.this, Error.class);

        // Check internet connection
        if (!mFunction.isNetworkAvailable(getApplication())) {
            this.noConnection();
        } else {
            new JsonLoader().execute();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        System.out.println("MainActivity::onStart()");
        // Create button for go to website boavizta
        ImageView BoaviztaButtonHeader = findViewById(R.id.BoaviztaButtonHeader);
        TextView BoaviztaTextHeader = findViewById(R.id.BoaviztaTextHeader);
        BoaviztaButtonHeader.setOnClickListener(this);
        BoaviztaTextHeader.setOnClickListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println("MainActivity::onDestroy()");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        System.out.println("MainActivity::onRestart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("MainActivity::onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        System.out.println("MainActivity::onPause()");
    }

    public void noConnection() {
        startActivity(mIntentConnection);
        finish();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        if (!mFunction.isNetworkAvailable(getApplication()) && hasFocus) {
            startActivity(mIntentConnection);
            finish();
        } else {
            onStart();
        }
    }

    // OnClick logo or name -> open web site
    @Override
    public void onClick(View v) {
        if (mFunction.isNetworkAvailable(getApplication())) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://boavizta.org/"));
            startActivity(intent);
            finish();
        } else {
            startActivity(new Intent(MainActivity.this, NoConnectionInternet.class));
            finish();
        }
    }
}