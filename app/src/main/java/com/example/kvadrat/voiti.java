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
                                        Intent intent;

                                        // Сравниваем строки с помощью .equals()
                                        if ((email.equals("mariapigina6162@gmail.com") && password.equals("17082006")) ||
                                                (email.equals("ternovaver4321@gmail.com") && password.equals("82612499")) ||
                                                (email.equals("gorunovigor6211@gmail.com") && password.equals("76239014")) ||
                                                (email.equals("portidovroman7711@gmail.com") && password.equals("83940162"))) {
                                            intent = new Intent(voiti.this, zaiavki_rieltora.class);
                                        } else {
                                            intent = new Intent(voiti.this, osnovnoiEkran.class);
                                        }

                                        startActivity(intent);
                                        finish(); // Завершаем текущую активность
                                    } else {
                                        Toast.makeText(voiti.this, "Ошибка ввода данных: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });

                } catch (Exception e) {
                    Toast.makeText(voiti.this, "Ошибка ввода данных", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public  void onClick(View view){
        Intent intent = new Intent(voiti.this, registr.class);
        startActivity(intent);
    }
}

