package com.example.mybicycle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    EditText username;
    EditText password;
    Button loginButton;

    ConnectionClass connectionClass;
    Connection con;
    String str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        connectionClass = new ConnectionClass();

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        loginButton = findViewById(R.id.loginButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                checkLogin(user, pass);
            }
        });
    }

    private void checkLogin(String user, String pass) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(() -> {
            try {
                con = connectionClass.CONN();
                if (con != null) {
                    String query = "SELECT * FROM accounts WHERE username = ? AND password = ?";
                    PreparedStatement stmt = con.prepareStatement(query);
                    stmt.setString(1, user);
                    stmt.setString(2, pass);

                    ResultSet rs = stmt.executeQuery();
                    if (rs.next()) {
                        str = "Login Successful!";
                    } else {
                        str = "Login Failed!";
                    }
                    rs.close();
                    stmt.close();
                } else {
                    str = "Connection Failed!";
                }
                con.close();
            } catch (SQLException e) {
                str = "Login Failed: " + e.getMessage();
            }

            runOnUiThread(() -> Toast.makeText(MainActivity.this, str, Toast.LENGTH_SHORT).show());
        });
    }
}
