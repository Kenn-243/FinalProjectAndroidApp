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
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Register extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();

        TextView toLogin = findViewById(R.id.login);
        toLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toSignUpIntent =new Intent(Register.this, MainActivity.class);
                startActivity(toSignUpIntent);
            }
        });

        Button signUp = findViewById(R.id.signUpButton);
        EditText nama = findViewById(R.id.name);
        EditText emailSignUp = findViewById(R.id.emailSignUp);
        EditText passwordSignUp = findViewById(R.id.passwordSignUp);
        EditText confirmPassword = findViewById(R.id.confirmPassword);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String idName = nama.getText().toString();
                String idEmailSignUp = emailSignUp.getText().toString();
                String idPasswordSignUp = passwordSignUp.getText().toString();
                String idConfirmPassword = confirmPassword.getText().toString();

                if(idName.isEmpty()){
                    Snackbar.make(view, "Name must not be empty!", Snackbar.LENGTH_SHORT).show();
                    return;
                }else if(idName.length() < 5){
                    Snackbar.make(view, "Name must have a minimum of 5 characters", Snackbar.LENGTH_SHORT).show();
                    return;
                }

                if(idEmailSignUp.isEmpty()){
                    Snackbar.make(view, "Email must not be empty!", Snackbar.LENGTH_SHORT).show();
                    return;
                }else if(!idEmailSignUp.contains("@")){
                    Snackbar.make(view, "Must contain an @", Snackbar.LENGTH_SHORT).show();
                    return;
                }else if(!idEmailSignUp.endsWith(".com")){
                    Snackbar.make(view, "Must ends with .com", Snackbar.LENGTH_SHORT).show();
                    return;
                }

                if(idPasswordSignUp.isEmpty()){
                    Snackbar.make(view, "Password must not be empty!", Snackbar.LENGTH_SHORT).show();
                    return;
                }else if(idPasswordSignUp.length() < 6){
                    Toast.makeText(Register.this, "lolz", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(!idConfirmPassword.equals(idPasswordSignUp)){
                    Snackbar.make(view, "Password must be the same!", Snackbar.LENGTH_SHORT).show();
                    return;
                }

                SharedPreferences sp = getSharedPreferences("Data", MODE_PRIVATE);
                SharedPreferences.Editor spEditor = sp.edit();
                spEditor.putString("name", idName);
                spEditor.putString("email", idEmailSignUp);
                spEditor.putString("password", idPasswordSignUp);
                spEditor.apply();

                mAuth.createUserWithEmailAndPassword(idEmailSignUp, idPasswordSignUp)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Intent toLoginPage = new Intent(Register.this, MainActivity.class);
                                startActivity(toLoginPage);
                            } else {
                                Snackbar.make(view, "There was an error, try again!", Snackbar.LENGTH_SHORT).show();
                            }
                        }
                    });
            }
        });
    }
}
