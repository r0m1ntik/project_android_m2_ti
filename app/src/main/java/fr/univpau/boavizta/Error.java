package fr.univpau.boavizta;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.TextView;

public class Error extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_error);
    }

    @Override
    protected void onStart() {
        super.onStart();
        TextView textView = findViewById(R.id.test_error);
        Intent mIntent = getIntent();
        String errorlog = mIntent.getStringExtra("error_info");
        textView.setText(errorlog);
    }
}