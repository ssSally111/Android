package com.example.tallybook;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

public class RecordActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);

        Button btnAdd = findViewById(R.id.btnAdd);
        Button btnGoBack = findViewById(R.id.btnGoBack);

        btnAdd.setOnClickListener(this);
        btnGoBack.setOnClickListener(this);
    }

    @SuppressLint("NonConstantResourceId")
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.btnAdd:
                EditText etContent = findViewById(R.id.etContent);
                EditText etDetail = findViewById(R.id.etDetail);
                RadioButton radIncome = findViewById(R.id.radIncome);
                RadioButton radSpending = findViewById(R.id.radSpending);

                String content = etContent.getText().toString();
                String detail = etDetail.getText().toString();
                Record.Type type = radIncome.isChecked() ? Record.Type.income : Record.Type.spending;

                if (content.equals("")) {
                    Toast.makeText(this, "请正确输入金额!", Toast.LENGTH_SHORT).show();
                    return;
                }

                String date = DateFormat.getDateInstance(DateFormat.SHORT, Locale.CHINA).format(new Date()) + '\u0020' + DateFormat.getTimeInstance(DateFormat.SHORT, Locale.CHINA).format(new Date());

                new MyHelper(this).insert(type, content, detail, date, new GenerateRandomKey().randomKey(8));
                Toast.makeText(this, "添加成功", Toast.LENGTH_SHORT).show();
                etContent.setText("");
                etDetail.setText("");
                radIncome.setChecked(false);
                radSpending.setChecked(true);
                break;
            case R.id.btnGoBack:
                goBank();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        goBank();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK)
            goBank();
        return super.onKeyDown(keyCode, event);
    }

    private void goBank() {
        startActivity(new Intent(RecordActivity.this, MainActivity.class));
    }
}