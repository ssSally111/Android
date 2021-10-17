package com.example.fragment;

import android.app.Notification;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MyViewHolder> {
    List<Hobbies> hobbies = new ArrayList<>();
    MainActivity act;
    public MenuAdapter(List<Hobbies> hobbies, MainActivity act) {
        this.hobbies = hobbies;
        this.act =act;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                String synopsis = hobbies.get(position).getSynopsis();
                FragmentManager manager = act.getSupportFragmentManager();
                ContentFragment contentFragment =(ContentFragment) manager.findFragmentByTag("contentFragment");
                contentFragment.showSynopsis(synopsis);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MenuAdapter.MyViewHolder holder, int position) {
        Hobbies hobbies = this.hobbies.get(position);
        holder.img.setImageResource(hobbies.getImg());
        holder.tvName.setText(hobbies.getName());
        holder.tvTag.setText(hobbies.getTag());
        holder.tvType.setText(hobbies.getType());
        holder.tvRecommend.setText(hobbies.getRecommend());
    }

    @Override
    public int getItemCount() {
        return hobbies.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvName;
        private final TextView tvRecommend;
        private final TextView tvTag;
        private final TextView tvType;
        private final ImageView img;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tvName);
            tvRecommend = itemView.findViewById(R.id.tvRecommend);
            tvTag = itemView.findViewById(R.id.tvTag);
            tvType = itemView.findViewById(R.id.tvType);
            img = itemView.findViewById(R.id.img);
        }
    }
}
