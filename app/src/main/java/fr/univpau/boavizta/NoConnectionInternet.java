package fr.univpau.boavizta;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class NoConnectionInternet extends AppCompatActivity implements View.OnClickListener {

    private Function mFunction;
    private Intent mIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_connection_internet);
        mFunction = new Function();
        mIntent = new Intent(NoConnectionInternet.this, MainActivity.class);
        Button button = findViewById(R.id.btn_try_again);
        button.setOnClickListener(this);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        if (mFunction.isNetworkAvailable(getApplication()) && hasFocus) {
            startActivity(mIntent);
            finish();
        }
    }

    @Override
    public void onClick(View v) {
        if (mFunction.isNetworkAvailable(getApplication())) {
            startActivity(mIntent);
            finish();
        } else {
            Toast.makeText(getApplicationContext(), "Please check your connection and try again!", Toast.LENGTH_SHORT).show();
        }
    }
}