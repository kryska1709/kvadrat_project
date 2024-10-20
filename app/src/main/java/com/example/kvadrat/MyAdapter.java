package com.example.kvadrat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private List<MyItem> itemList;
    private Context context;

    public MyAdapter(List<MyItem> itemList, Context context) {
        this.itemList = itemList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.razmet_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MyItem item = itemList.get(position);
        holder.vidSdelki.setText(item.getVidSdelki());
        holder.VidNedvizh.setText(item.getVidNedvizh());
        holder.ploshad.setText(item.getPloshad());
        holder.price.setText(item.getPrice());
        holder.adress.setText(item.getAdress());
        holder.phone.setText(item.getPhone());
        // Установка изображения в ImageView в зависимости от площади
        switch (item.getPloshad()) {
            case "34 квадратных метра":
                holder.imageView.setImageResource(R.drawable.vtorichkup);
                break;
            case "40 квадратных метров":
                holder.imageView.setImageResource(R.drawable.vtorichsniat);
                break;
            case "64 квадратных метра":
                holder.imageView.setImageResource(R.drawable.vtorsnim);
                break;
            case "58 квадратных метров":
                holder.imageView.setImageResource(R.drawable.novostroiki);
                break;
            case "42 квадратных метра":
                holder.imageView.setImageResource(R.drawable.okna_novostroyka4);
                break;
            case "37 квадратных метров":
                holder.imageView.setImageResource(R.drawable.novostr);
                break;
            case "93 квадратных метра":
                holder.imageView.setImageResource(R.drawable.novostroi);
                break;
            case "96 квадратных метров":
                holder.imageView.setImageResource(R.drawable.kypdom);
                break;
            case "85 квадратных метров":
                holder.imageView.setImageResource(R.drawable.domickup);
                break;
            case "200 квадратных метров":
                holder.imageView.setImageResource(R.drawable.snimdom);
                break;
            case "112 квадратных метров":
                holder.imageView.setImageResource(R.drawable.sniatdome);
                break;
        }

    }

        @Override
    public int getItemCount() {
        return itemList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView ;
        TextView vidSdelki, VidNedvizh, ploshad, price, adress, phone ;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.ImageViews); // Инициализация ImageView
            vidSdelki = itemView.findViewById(R.id.vidSdelki);
            VidNedvizh = itemView.findViewById(R.id.VidNedvizh);
            ploshad = itemView.findViewById(R.id.ploshad);
            price = itemView.findViewById(R.id.price);
            adress = itemView.findViewById(R.id.adress);
            phone = itemView.findViewById(R.id.editTextPhone);
        }
    }
}
