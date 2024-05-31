package com.example.mybicycle;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class personbalance extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_balance);

        // 获取布局中的 ImageView 和 TextView
        ImageView easyCardImage = findViewById(R.id.easyCardImage);
        TextView nameTextView = findViewById(R.id.nameTextView);
        TextView balanceTextView = findViewById(R.id.balanceTextView);

        // 设置图片资源
        easyCardImage.setImageResource(R.drawable.easycard);

        // 设置文本内容
        nameTextView.setText("Jerry");
        balanceTextView.setText("balance: 25 dollars");

        // 显示警告对话框
        showAlertDialog();
    }

    private void showAlertDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Error")
                .setMessage("Insufficient balance, please go to recharge.")
                .setPositiveButton("OK", (dialog, which) -> dialog.dismiss())
                .show();
    }
}
