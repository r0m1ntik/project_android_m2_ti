package fr.univpau.boavizta.result;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.Window;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.google.gson.Gson;
import java.io.BufferedInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

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

        ((App) getApplication()).setChart1_txt(findViewById(R.id.chart1_txt));
        ((App) getApplication()).setChart2_txt(findViewById(R.id.chart2_txt));
        ((App) getApplication()).setChart3_txt(findViewById(R.id.chart3_txt));

        ((App) getApplication()).setKgCO2eqTotal(findViewById(R.id.kgCO2eqTotal));
        ((App) getApplication()).setMJTotal(findViewById(R.id.MJTotal));
        ((App) getApplication()).setKgSbeqTotal(findViewById(R.id.kgSbeqTotal));

        ((App) getApplication()).setGwp_usage(findViewById(R.id.gwp_usage));
        ((App) getApplication()).setGwp_manufacturing(findViewById(R.id.gwp_manufacturing));
        ((App) getApplication()).setPe_usage(findViewById(R.id.mj_usage));
        ((App) getApplication()).setPe_manufacturing(findViewById(R.id.mj_manufacturing));
        ((App) getApplication()).setAdp_usage(findViewById(R.id.adp_usage));
        ((App) getApplication()).setAdp_manufacturing(findViewById(R.id.adp_manufacturing));
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
            final String[] error = {""};
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

                Log.i("STATUS", String.valueOf(conn.getResponseCode()));
                Log.i("MSG" , conn.getResponseMessage());

                if (conn.getResponseMessage().equals("OK")) {
                    String response;
                    InputStream in = new BufferedInputStream(conn.getInputStream());
                    response = HttpHandler.convertStreamToString(in);
                    Log.i("RESPONSE", response);
                    if (!response.equals("")) {
                        try {
                            new Handler(Looper.getMainLooper()).post(() -> ((App) getApplication()).setResultGraph(new Gson().fromJson(response, ResultGraph.class)));
                        } catch (final Exception e) {
                            return e.getMessage();
                        }
                    } else {
                        return "No answer is found";
                    }
                } else {
                    return String.valueOf(conn.getResponseCode());
                }
                conn.disconnect();
            } catch (IOException e) {
                return e.getMessage();
            }
            return error[0];
        }

        @SuppressLint("SetTextI18n")
        @Override
        protected void onPostExecute(@Nullable String result) {
            mDialog.dismiss();
            if (!(result != null && result.equals(""))) {
                mErrorIntent.putExtra("error_info", result);
                startActivity(mErrorIntent);
                finish();
            } else {
                // PIE CHART DECLARATION
                PieChart pieChart, pieChart1, pieChart2;

                pieChart1 = findViewById(R.id.chart1); // pe
                pieChart2 = findViewById(R.id.chart2); // adp
                pieChart = findViewById(R.id.chart3);  // gwp

                Legend l = pieChart.getLegend();
                l.setEnabled(false);
                Legend l1 = pieChart1.getLegend();
                l1.setEnabled(false);
                Legend l2 = pieChart2.getLegend();
                l2.setEnabled(false);

                BigDecimal adp_use = BigDecimal.valueOf(((App) getApplication()).getResultGraph().getAdp().getUse());
                BigDecimal adp_manufacture = BigDecimal.valueOf(((App) getApplication()).getResultGraph().getAdp().getManufacture());
                double adp_PercenteNumber = (((App) getApplication()).getResultGraph().getAdp().getManufacture() + ((App) getApplication()).getResultGraph().getAdp().getUse())/100;

                BigDecimal gwp_use = BigDecimal.valueOf(((App) getApplication()).getResultGraph().getGwp().getUse());
                BigDecimal gwp_manufacture = BigDecimal.valueOf(((App) getApplication()).getResultGraph().getGwp().getManufacture());
                double gwp_PercenteNumber = (((App) getApplication()).getResultGraph().getGwp().getManufacture() + ((App) getApplication()).getResultGraph().getGwp().getUse())/100;

                BigDecimal pe_use = BigDecimal.valueOf(((App) getApplication()).getResultGraph().getPe().getUse());
                BigDecimal pe_manufacture = BigDecimal.valueOf(((App) getApplication()).getResultGraph().getPe().getManufacture());
                double pe_PercenteNumber = (((App) getApplication()).getResultGraph().getPe().getManufacture() + ((App) getApplication()).getResultGraph().getPe().getUse())/100;

                // adp kgSbeq
                ((App) getApplication()).getChart2_txt().setText(((App) getApplication()).getResultGraph().getAdp().getUnit());
                // gwp kgCO2eq
                ((App) getApplication()).getChart3_txt().setText(((App) getApplication()).getResultGraph().getGwp().getUnit());
                // pe MJ
                ((App) getApplication()).getChart1_txt().setText(((App) getApplication()).getResultGraph().getPe().getUnit());

                // TOTAL
                double total_gwp = ((App) getApplication()).getResultGraph().getGwp().getManufacture() + ((App) getApplication()).getResultGraph().getGwp().getUse();
                double total_pe = ((App) getApplication()).getResultGraph().getPe().getManufacture() + ((App) getApplication()).getResultGraph().getPe().getUse();
                double total_adp = ((App) getApplication()).getResultGraph().getAdp().getManufacture() + ((App) getApplication()).getResultGraph().getAdp().getUse();
                // gwp kgCO2eq
                ((App) getApplication()).getKgCO2eqTotal().setText(Double.toString(total_gwp));
                // pe MJ
                ((App) getApplication()).getMJTotal().setText(Double.toString(total_pe));
                // adp kgSbeq
                ((App) getApplication()).getKgSbeqTotal().setText(Double.toString(total_adp));

                // gwp kgCO2eq
                ((App) getApplication()).getGwp_usage().setText(((App) getApplication()).getResultGraph().getGwp().getUse().toString());
                ((App) getApplication()).getGwp_manufacturing().setText(((App) getApplication()).getResultGraph().getGwp().getManufacture().toString());
                ((App) getApplication()).getPe_usage().setText(((App) getApplication()).getResultGraph().getPe().getUse().toString());
                ((App) getApplication()).getPe_manufacturing().setText(((App) getApplication()).getResultGraph().getPe().getManufacture().toString());
                ((App) getApplication()).getAdp_usage().setText(((App) getApplication()).getResultGraph().getAdp().getUse().toString());
                ((App) getApplication()).getAdp_manufacturing().setText(((App) getApplication()).getResultGraph().getAdp().getManufacture().toString());

                // gwp kgCO2eq
                pieChart.setCenterText(((App) getApplication()).getResultGraph().getGwp().getUnit());
                // pe MJ
                pieChart1.setCenterText(((App) getApplication()).getResultGraph().getPe().getUnit());
                // apt kgSbeq
                pieChart2.setCenterText(((App) getApplication()).getResultGraph().getAdp().getUnit());

                ArrayList<PieEntry> entries = new ArrayList<>();
                entries.add(new PieEntry(gwp_use.setScale(4, RoundingMode.HALF_UP).floatValue()/ (float) gwp_PercenteNumber, "Use"));
                entries.add(new PieEntry(gwp_manufacture.setScale(4, RoundingMode.HALF_UP).floatValue()/ (float) gwp_PercenteNumber, "Manufacture"));

                ArrayList<PieEntry> entries1 = new ArrayList<>();
                entries1.add(new PieEntry(pe_use.setScale(4, RoundingMode.HALF_UP).floatValue()/ (float) pe_PercenteNumber, "Use"));
                entries1.add(new PieEntry(pe_manufacture.setScale(4, RoundingMode.HALF_UP).floatValue()/ (float) pe_PercenteNumber, "Manufacture"));

                ArrayList<PieEntry> entries2 = new ArrayList<>();
                entries2.add(new PieEntry(adp_use.setScale(4, RoundingMode.HALF_UP).floatValue()/ (float) adp_PercenteNumber, "Use"));
                entries2.add(new PieEntry(adp_manufacture.setScale(4, RoundingMode.HALF_UP).floatValue()/ (float) adp_PercenteNumber, "Manufacture"));

                ArrayList<Integer> colors = new ArrayList<>();
                colors.add(Color.parseColor("#caa27c"));
                colors.add(Color.parseColor("#54b6bd"));

                PieDataSet dataSet = new PieDataSet(entries, ((App) getApplication()).getResultGraph().getGwp().getUnit());
                dataSet.setColors(colors);

                PieDataSet dataSet1 = new PieDataSet(entries1, ((App) getApplication()).getResultGraph().getPe().getUnit());
                dataSet1.setColors(colors);

                PieDataSet dataSet2 = new PieDataSet(entries2, ((App) getApplication()).getResultGraph().getAdp().getUnit());
                dataSet2.setColors(colors);

                PieData data = new PieData(dataSet);
                PieData data1 = new PieData(dataSet1);
                PieData data2 = new PieData(dataSet2);

                data.setDrawValues(true);
                data1.setDrawValues(true);
                data2.setDrawValues(true);

                data.setValueFormatter(new PercentFormatter());
                data1.setValueFormatter(new PercentFormatter());
                data2.setValueFormatter(new PercentFormatter());

                data.setValueTextSize(12f);
                data1.setValueTextSize(12f);
                data2.setValueTextSize(12f);

                data.setValueTextColor(Color.WHITE);
                data1.setValueTextColor(Color.WHITE);
                data2.setValueTextColor(Color.WHITE);

                pieChart.setDrawHoleEnabled(true);
                pieChart1.setDrawHoleEnabled(true);
                pieChart2.setDrawHoleEnabled(true);

                pieChart.setEntryLabelColor(Color.WHITE);
                pieChart1.setEntryLabelColor(Color.WHITE);
                pieChart2.setEntryLabelColor(Color.WHITE);

                pieChart.setData(data);
                pieChart1.setData(data1);
                pieChart2.setData(data2);

                pieChart.getDescription().setEnabled(false);
                pieChart1.getDescription().setEnabled(false);
                pieChart2.getDescription().setEnabled(false);

                pieChart.setUsePercentValues(true);
                pieChart1.setUsePercentValues(true);
                pieChart2.setUsePercentValues(true);

                pieChart.setCenterTextColor(Color.parseColor("#057173"));
                pieChart1.setCenterTextColor(Color.parseColor("#057173"));
                pieChart2.setCenterTextColor(Color.parseColor("#057173"));

                pieChart1.animateY(500, Easing.EaseInBack);  // kgCO2eq gwp
                pieChart2.animateY(1000, Easing.EaseInBack); // kgSbeq adp
                pieChart.animateY(1500, Easing.EaseInBack);  // kgSbeq pe

                pieChart.invalidate();
                pieChart1.invalidate();
                pieChart2.invalidate();
            }
        }
    }
}
