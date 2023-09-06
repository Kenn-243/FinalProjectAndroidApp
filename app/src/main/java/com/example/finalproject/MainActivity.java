package com.example.finalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();


        SharedPreferences sp = getSharedPreferences("Data", MODE_PRIVATE);
        Button login = findViewById(R.id.loginButton);
        EditText email = findViewById(R.id.email);
        EditText password = findViewById(R.id.password);

        TextView toSignUp = findViewById(R.id.signUp);
        toSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toSignUpIntent =new Intent(MainActivity.this, Register.class);
                startActivity(toSignUpIntent);
            }
        });



        login.setOnClickListener(new View.OnClickListener() {
            String spEmail = sp.getString("email", null);
            String spPassword = sp.getString("password", null);

            @Override
            public void onClick(View view) {
                String idEmail = email.getText().toString();
                String idPassword = password.getText().toString();

                if(idEmail.isEmpty()){
                    Snackbar.make(view, "Email must not be empty!", Snackbar.LENGTH_SHORT).show();
                    return;
                }else if(!idEmail.equals(spEmail)){
                    Snackbar.make(view, "Email dpesn't exist!", Snackbar.LENGTH_SHORT).show();
                }

                if(idPassword.isEmpty()){
                    Snackbar.make(view, "Password must not be empty!", Snackbar.LENGTH_SHORT).show();
                    return;
                }else if(idPassword.equals(spPassword)){
                    Snackbar.make(view, "Password is incorrect!", Snackbar.LENGTH_SHORT).show();
                }

                mAuth.signInWithEmailAndPassword(idEmail, idPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Intent toLoginPage = new Intent(MainActivity.this, Home.class);
                            startActivity(toLoginPage);
                        }else{
                            Snackbar.make(view, "There was an error, try again!", Snackbar.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}