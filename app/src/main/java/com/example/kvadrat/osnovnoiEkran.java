package com.example.kvadrat;

import static androidx.core.content.PackageManagerCompat.LOG_TAG;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.SearchManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import com.example.kvadrat.Message;

public class osnovnoiEkran extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MyAdapter myAdapter;
    private List<MyItem> itemList;
    private DatabaseReference databaseReference;
    private String timestamp;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_osnovnoi_ekran);

        // Инициализация UI элементов
        recyclerView = findViewById(R.id.recycler);
        itemList = new ArrayList<>();
        myAdapter = new MyAdapter(itemList, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(myAdapter);

        databaseReference = FirebaseDatabase.getInstance().getReference("note");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                itemList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    MyItem myItem = snapshot.getValue(MyItem.class);
                    if (myItem != null) {
                        itemList.add(myItem);
                    }
                }
                myAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Обработка ошибок
                Toast.makeText(osnovnoiEkran.this, "Ошибка получения данных: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void onClick1(View view) {
        showDataEntryDialog();
    }


    public void showDataEntryDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog, null);
        builder.setView(dialogView);
        AlertDialog dialog = builder.create();
        dialog.show();

        EditText editTextEmail = dialogView.findViewById(R.id.editTextTextEmailAddress);
        EditText editTextMessage = dialogView.findViewById(R.id.editTextMessage);
        Button buttonSend = dialogView.findViewById(R.id.buttonSend);
        Button buttonCancel = dialogView.findViewById(R.id.buttonCancel);

        buttonSend.setOnClickListener(v -> {
            String email = editTextEmail.getText().toString();
            String message = editTextMessage.getText().toString();
            writeToFirebase(email, message, dialog); // передаём диалог в метод
        });

        buttonCancel.setOnClickListener(v -> dialog.dismiss()); // просто закрываем диалог
    }

    private void writeToFirebase(String email, String message, AlertDialog dialog) {
        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference("messages");
        String key = myRef.push().getKey();
        Message msg = new Message(email, message);
        myRef.child(key).setValue(msg).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Toast.makeText(this, "Сообщение отправлено!", Toast.LENGTH_SHORT).show();
                dialog.dismiss(); // закрываем диалог после успешной отправки
                Toast.makeText(this, "C вами скоро свяжутся", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Ошибка отправки сообщения.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
