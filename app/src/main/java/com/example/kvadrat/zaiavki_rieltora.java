package com.example.kvadrat;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;

public class zaiavki_rieltora extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ZaiavkiAdapter adapter;
    private List<Zaiavka> messages;
    private DatabaseReference databaseReference;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zaiavki_rieltora);

        recyclerView = findViewById(R.id.rec);
        messages = new ArrayList<>();
        adapter = new ZaiavkiAdapter(messages, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        // Инициализация базы данных
        databaseReference = FirebaseDatabase.getInstance().getReference("messages");

        Button clearButton = findViewById(R.id.clear);
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearTable();
            }
        });

        // Слушатель для получения данных
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                messages.clear();  // очищаем список перед обновлением
                for (DataSnapshot messageSnapshot : snapshot.getChildren()) {
                    String userEmail = messageSnapshot.child("email").getValue(String.class);
                    String messageText = messageSnapshot.child("message").getValue(String.class);
                    messages.add(new Zaiavka(messageText, userEmail, messageSnapshot.getKey())); // используем ключ как id
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(zaiavki_rieltora.this, "Ошибка получения данных", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void clearTable() {
        databaseReference.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(zaiavki_rieltora.this, "Удаление прошло успешно", Toast.LENGTH_SHORT).show();
                } else {
                    Log.e("Firebase", "Failed to clear table: " + task.getException().getMessage());
                }
            }
        });
    }
}
