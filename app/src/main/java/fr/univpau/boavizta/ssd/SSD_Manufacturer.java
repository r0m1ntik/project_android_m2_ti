package fr.univpau.boavizta.ssd;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

import fr.univpau.boavizta.HttpHandler;

public class SSD_Manufacturer {

    private static ArrayList<SSD_Manufacturer> ManufacturerArrayList;
    private final int id;
    private final String name;

    public SSD_Manufacturer(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public static ArrayList<SSD_Manufacturer> getManufacturerArrayList() {
        return ManufacturerArrayList;
    }

    public static String[] manifacturerNames() {
        String[] names = new String[ManufacturerArrayList.size()];
        for (SSD_Manufacturer m : ManufacturerArrayList) {
            names[m.getId()] = m.getName();
        }
        return names;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static class GetManufacturer extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            System.out.println("onPreExecute");
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();
            // Making a request to url and getting response
            String url = "https://uppa.api.boavizta.org/v1/utils/ssd_manufacturer";
            String jsonStr = sh.makeServiceCall(url, "GET");

            Log.e("SSD Manufacturer", "Response from url: " + jsonStr);
            if (jsonStr != null) {
                try {
                    JSONArray jsonArray = new JSONArray(jsonStr);
                    ManufacturerArrayList = new ArrayList<>();
                    for(int i = 0; i < jsonArray.length(); i++) {
                        ManufacturerArrayList.add(new SSD_Manufacturer(i, jsonArray.getString(i)));
                    }
                } catch (final JSONException e) {
                    Log.e("SSD Manufacturer", "Json parsing errors: " + e.getMessage());
                }
            } else {
                Log.e("SSD Manufacturer", "Couldn't get json from server.");
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
        }
    }
}
