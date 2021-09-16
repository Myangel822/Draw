package com.example.draw;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView txtFunction = (TextView) findViewById(R.id.txtFunction);
        final CustomView customView = (CustomView) findViewById(R.id.plotview);
        Button buttonPlot = (Button) findViewById(R.id.buttonPlot);

        buttonPlot.setOnClickListener(v -> {
            if (customView != null) {
                String strFunction = txtFunction.getText().toString();
                customView.setStrFunction(strFunction);
                customView.invalidate();
            }
        });
    }
}