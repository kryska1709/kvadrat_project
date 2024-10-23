package com.example.kvadrat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class registr extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private EditText editTextEmail;
    private EditText editTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registr);

        mAuth = FirebaseAuth.getInstance();

        editTextEmail = findViewById(R.id.mailRegPols);
        editTextPassword = findViewById(R.id.парольРегистрация);

        Button registerButton = findViewById(R.id.сохранитьТелИПароль);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editTextEmail.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();

                // Проверка на пустые поля
                if (email.isEmpty()) {
                    Toast.makeText(registr.this, "Введите электронную почту", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (password.isEmpty()) {
                    Toast.makeText(registr.this, "Введите пароль", Toast.LENGTH_SHORT).show();
                    return;
                }

                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(registr.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    Intent intent = new Intent(registr.this, osnovnoiEkran.class);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    Toast.makeText(registr.this, "Ошибка регистрации", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }
}
