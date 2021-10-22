package com.example.tallybook;

import android.app.AlertDialog;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MyViewHolder> {
    List<Record> records;
    MainActivity act;

    @RequiresApi(api = Build.VERSION_CODES.N)
    public MenuAdapter(List<Record> records, MainActivity act) {
        this.records = records;
        this.act=act;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item, parent, false);
        MyViewHolder holder = new MyViewHolder(view);

        view.setOnClickListener(v -> new AlertDialog.Builder(act)
                .setIcon(R.drawable.warning)
                .setTitle("提示")
                .setMessage("是否删除此项？")
                .setPositiveButton("确定", (dialog, which) -> {
                    TextView _key = view.findViewById(R.id._key);
                    String _k = _key.getText().toString();
                    new MyHelper(act).delete(_k);

//                    records.forEach(record -> { // ConcurrentModificationException 并发修改异常
//                        if (record.getDate().equals(time.getText().toString())) {
//                            Log.d("TAGS", "onCreateViewHolder: " + record.getDate());
//                            records.remove(record);
//                            return;
//                        }
//                    });

                    for (Record record : records) {
                        if (record.getKey().equals(_k)) {
                            records.remove(record);
                            act.upSunData(records);
                            this.notifyDataSetChanged();
                            break;
                        }
                    }
                })
                .setNegativeButton("取消", (dialog, which) -> dialog.dismiss())
                .create()
                .show());
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MenuAdapter.MyViewHolder holder, int position) {
        Record record = records.get(position);
        holder.tvType.setText(record.getType().equals(Record.Type.income) ? "收入" : "支出");
        holder.tvContent.setText(String.valueOf(record.getContent()));
        holder.tvDetail.setText(record.getDetail());
        holder.tvTime.setText(record.getDate());
        holder._key.setText(record.getKey());
    }

    @Override
    public int getItemCount() {
        return records.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvType;
        private final TextView tvContent;
        private final TextView tvDetail;
        private final TextView tvTime;
        private final TextView _key;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tvType = itemView.findViewById(R.id.tvType);
            tvContent = itemView.findViewById(R.id.tvContent);
            tvTime = itemView.findViewById(R.id.tvTime);
            tvDetail = itemView.findViewById(R.id.tvDetail);
            _key = itemView.findViewById(R.id._key);
        }
    }
}
