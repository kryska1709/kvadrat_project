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
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class voiti extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private Button buttonLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voiti);
        FirebaseApp.initializeApp(this);

        mAuth = FirebaseAuth.getInstance();
        editTextEmail = findViewById(R.id.почтаВход);
        editTextPassword = findViewById(R.id.парольВойти);
        buttonLogin = findViewById(R.id.войти);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String email = editTextEmail.getText().toString();
                    String password = editTextPassword.getText().toString();

                    mAuth.signInWithEmailAndPassword(email, password)
                            .addOnCompleteListener(voiti.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        FirebaseUser user = mAuth.getCurrentUser();
                                        Intent intent = new Intent(voiti.this, osnovnoiEkran.class);
                                        startActivity(intent);
                                        finish();
                                    } else {
                                        Toast.makeText(voiti.this, "Ошибка ввода данных", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                } catch (Exception e) {
                    Toast.makeText(voiti.this, "Ошибка ввода данных", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

