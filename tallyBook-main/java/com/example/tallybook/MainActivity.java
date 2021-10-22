package com.example.tallybook;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rv;
    private static double incomeSun = 0;
    private static double spendingSun = 0;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnAccess = findViewById(R.id.btnAccess);
        rv = findViewById(R.id.rv);

        initData();

        btnAccess.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, RecordActivity.class);
            startActivity(intent);
        });
    }

    /**
     * 初始化
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    private void initData() {
        List<Record> records = readData();
        rv.setLayoutManager(new LinearLayoutManager(this));
        MenuAdapter adapter = new MenuAdapter(records,this);
        rv.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        upSunData(records);
    }

    /**
     * readData 读SQLite中储存数据.
     * @return  List<Record>.
     */
    private List<Record> readData() {
        List<Record> records = new ArrayList<>();
        Set<String[]> queryData = new MyHelper(this).queryAll();
        if (queryData != null) {
            for (String[] item : queryData) {
                records.add(new Record(item[0].equals("income")? Record.Type.income: Record.Type.spending,Double.parseDouble(item[1]),item[2],item[3],item[4]));
            }
        }
        return records;
    }

    /**
     * upSunDate 更新面板收入支出总和数据.
     */
    public void upPanelDate(){
        TextView tvTotalR = findViewById(R.id.tvTotalR);
        TextView tvTotalS = findViewById(R.id.tvTotalS);
        tvTotalR.setText(String.valueOf(incomeSun));
        tvTotalS.setText(String.valueOf(spendingSun));
    }

    /**
     * 更新内部收入支出总和数据.
     * @param record getContent.
     */
    public static void contentSun(Record record) {
        double content = record.getContent();
        if (record.getType().equals(Record.Type.income))
            incomeSun += content;
        else
            spendingSun += content;
    }

    /**
     * 封装更新收入支出.
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void upSunData(List<Record> records){
        incomeSun = 0;
        spendingSun = 0;
        records.forEach(MainActivity::contentSun);
        upPanelDate();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        initData();
    }
}
