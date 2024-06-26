package com.example.mybicycle;

import android.app.AlertDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.widget.Button;
import android.widget.Chronometer;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Locale;

public class rtn_bicycle extends AppCompatActivity {

    private Chronometer chronometer;
    private AlertDialog alertDialog;
    private final Handler handler = new Handler();
    private Runnable updateTimeTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rtn_bicycle);

        chronometer = findViewById(R.id.chronometer);
        Button rtnButton = findViewById(R.id.rtnButton);

        // Start the chronometer when the activity is created
        startChronometer();

        // Set up the return button
        rtnButton.setOnClickListener(v -> showReturnDialog());
    }

    @Override
    protected void onPause() {
        super.onPause();
        chronometer.stop();
        stopUpdatingTime();
    }

    @Override
    protected void onResume() {
        super.onResume();
        startChronometer();
    }

    private void startChronometer() {
        long startTime = SystemClock.elapsedRealtime();
        chronometer.setBase(startTime);
        chronometer.start();

        // Custom tick listener to format time as HH:MM:SS
        chronometer.setOnChronometerTickListener(chronometer -> {
            long elapsedMillis = SystemClock.elapsedRealtime() - chronometer.getBase();
            int hours = (int) (elapsedMillis / 3600000);
            int minutes = (int) (elapsedMillis % 3600000 / 60000);
            int seconds = (int) (elapsedMillis % 60000 / 1000);
            String time = String.format(Locale.getDefault(), "%02d:%02d:%02d", hours, minutes, seconds);
            chronometer.setText(time);
        });
    }

    private void showReturnDialog() {
        // Initial message
        String initialMessage = "You have been riding for " + chronometer.getText().toString() + ". Are you ready to return the bike?";

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Return Bike")
                .setMessage(initialMessage)
                .setPositiveButton("QR Code Scanner", (dialog, which) -> {
                    // Pause the chronometer
                    chronometer.stop();
                    stopUpdatingTime();
                    // TODO: Launch QR Code Scanner activity or functionality here
                })
                .setNegativeButton("Cancel", (dialog, which) -> {
                    // Dismiss the dialog and continue chronometer
                    dialog.dismiss();
                    stopUpdatingTime();
                });

        alertDialog = builder.create();
        alertDialog.show();

        updateTimeTask = new Runnable() {
            @Override
            public void run() {
                if (alertDialog != null && alertDialog.isShowing()) {
                    String currentTime = chronometer.getText().toString();
                    alertDialog.setMessage("You have been riding for " + currentTime + ". Are you ready to return the bike?");
                    handler.postDelayed(this, 10); // Update every second
                }
            }
        };

        handler.post(updateTimeTask);
    }

    private void stopUpdatingTime() {
        handler.removeCallbacks(updateTimeTask);
    }
}
