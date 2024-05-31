package com.example.mybicycle; // Assuming this is your package name

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;


public class mamap extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mamap);

        Button button1 = findViewById(R.id.mabutton1);
        Button button2 = findViewById(R.id.mabutton2);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlertDialog("Management College Parking Lot 1\n"+
                        "Total parking spaces: 15\n" +
                        "Currently available: 6\n" +
                        "Please click the button below to scan the QR code.");
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlertDialog("Management College Parking Lot 2\n" +
                        "Total parking spaces: 15\n" +
                        "Currently available: 3\n" +
                        "Please click the button below to scan the QR code.");
            }
        });
    }

    private void showAlertDialog(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message);
        builder.setPositiveButton("scan", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // 在這裡處理跳轉到掃描介面的動作
            }
        });
        builder.setNegativeButton("cancel", null);
        builder.show();
    }
}