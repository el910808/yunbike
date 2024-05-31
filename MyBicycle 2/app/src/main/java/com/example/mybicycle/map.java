package com.example.mybicycle;

import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.view.View;

public class map extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map);

        // Find the button by its id after setContentView is called
        Button mamap_button = findViewById(R.id.mamap1_button);

        mamap_button.setOnClickListener(this); // Use 'this' as the listener
    }

    @Override
    public void onClick(View v) {
        // Handle click events for any view that has this listener attached
        if (v.getId() == R.id.mamap1_button) {
            Intent intent = new Intent(this, mamap.class);
            startActivity(intent);
        }
    }
}
