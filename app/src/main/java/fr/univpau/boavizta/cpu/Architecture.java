package fr.univpau.boavizta.cpu;

import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import java.util.ArrayList;
import fr.univpau.boavizta.HttpHandler;

public class Architecture extends AppCompatActivity {

    private static ArrayList<Architecture> ArchitectureArrayList;
    private final int id;
    private final String name;
    private static Intent mIntent;

    public Architecture(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public static ArrayList<Architecture> getArchitectureArrayList() {
        return ArchitectureArrayList;
    }

    public static String[] architectureNames() {
        String[] names = new String[ArchitectureArrayList.size()];
        for (Architecture a : ArchitectureArrayList) {
            names[a.getId()] = a.getName();
        }
        return names;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static class GetArchitectures extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            System.out.println("onPreExecute");
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();
            // Making a request to url and getting response
            String url = "https://uppa.api.boavizta.org/v1/utils/cpu_family";
            String jsonStr = sh.makeServiceCall(url, "GET");

            Log.e("Architecture", "Response from url: " + jsonStr);
            if (jsonStr != null) {
                try {
                    JSONArray jsonArray = new JSONArray(jsonStr);
                    ArchitectureArrayList = new ArrayList<>();
                    for(int i = 0; i < jsonArray.length(); i++) {
                        ArchitectureArrayList.add(new Architecture(i, jsonArray.getString(i)));
                    }
                } catch (final JSONException e) {
                    Log.e("Architecture", "Json parsing error: " + e.getMessage());
                }
            } else {
                // données non récuperer
                Log.e("Architecture", "Couldn't get json from server.");
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
        }
    }
}
