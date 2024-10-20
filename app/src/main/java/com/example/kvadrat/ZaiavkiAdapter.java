package com.example.kvadrat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ZaiavkiAdapter extends RecyclerView.Adapter<ZaiavkiAdapter.ViewHolder> {

    private final List<Zaiavka> messages; // исправлено на Zaiavka
    private final Context context;

    public ZaiavkiAdapter(List<Zaiavka> messages, Context context) { // исправлено на Zaiavka
        this.messages = messages;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_zaiavka, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Zaiavka zaiavka = messages.get(position); // исправлено на Zaiavka
        holder.emailTextView.setText(zaiavka.getUserEmail());
        holder.messageTextView.setText(zaiavka.getMessage()); // исправлено на getMessage()
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView emailTextView;
        TextView messageTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            emailTextView = itemView.findViewById(R.id.emailTextView);
            messageTextView = itemView.findViewById(R.id.messageTextView);
        }
    }
}
