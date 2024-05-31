package com.example.mybicycle;

import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;

public class ConnectionClass {

    protected static String db = "bicycle";
    protected static String ip = "recipeedb-2.mysql.database.azure.com";
    protected static String port = "3306";
    protected static String username = "yuntech";
    protected static String password = "recipeDB@@";

    public Connection CONN() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://" + ip + ":" + port + "/" + db, username, password);
        } catch (ClassNotFoundException | SQLException e) {
            Log.e("ERROR", Objects.requireNonNull(e.getMessage()));
        }
        return conn;
    }
}
