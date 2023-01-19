package fr.univpau.boavizta;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Error extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_error);

        Button mButton = findViewById(R.id.btn_calculate);
        mButton.setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        TextView textView = findViewById(R.id.test_error);
        Intent mIntent = getIntent();
        String errorlog = mIntent.getStringExtra("error_info");
        textView.setText(errorlog);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(Error.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}