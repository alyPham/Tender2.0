package com.example.tender;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Location extends AppCompatActivity {

    private SeekBar sBar;
    private TextView textView;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.location);
        sBar = (SeekBar) findViewById(R.id.seekBar);
        textView = (TextView) findViewById(R.id.range);
        textView.setText(sBar.getProgress() + " miles away");
        sBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){
            int pval = 0;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser){
                pval = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                sBar.animate();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                textView.setText(pval + "miles away");
            }
        });
    }

    public void goBack(View view){
        Intent i = new Intent(Location.this, NewUserPage.class);
        startActivity(i);
    }
}
