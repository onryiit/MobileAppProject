package com.example.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class SignIn extends AppCompatActivity {
    private Toolbar toolbar;

    private EditText email;
    private EditText password;
    private Button btnsigin;
    private Button btnbypass;

    private FirebaseAuth auth;

    ProgressDialog login;

    public  void init() {
        toolbar = findViewById(R.id.action_barSignIn);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Sign In");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btnbypass = findViewById(R.id.bypass);
        btnbypass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignIn.this,owner_saloon.class);
                startActivity(intent);
            }
        });

        email = findViewById(R.id.EmailSıngIn);
        password = findViewById(R.id.PasswordSıgnIn);
        btnsigin = findViewById(R.id.SignInBtn);
        auth = FirebaseAuth.getInstance();

        btnsigin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });
        login = new ProgressDialog(this);
    }

        private void signIn() {
            String Email = email.getText().toString();
            String Password = password.getText().toString();
            if (TextUtils.isEmpty(Email)) {
                Toast.makeText(this, "E-MAİL FIELD CANNOT BE EMPTY!!!", Toast.LENGTH_LONG).show();
            } else if (TextUtils.isEmpty(Password)) {
                Toast.makeText(this, "PASSWORD FIELD CANNOT BE EMPTY!!!", Toast.LENGTH_LONG).show();
            } else {

                //progress
                login.setTitle("Giriş Yapılıyor...");
                login.setMessage("Lütfen Bekleyin");
                login.setCanceledOnTouchOutside(true);
                login.show();

                auth.signInWithEmailAndPassword(Email, Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(SignIn.this, "Sıgn In SUCCESSFUL!!!\n", Toast.LENGTH_LONG).show();


                            Intent intent = new Intent(SignIn.this, owner_saloon.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                            login.dismiss();
                            finish();
                        } else {
                            String message = task.getException().toString();
                            Toast.makeText(SignIn.this, "Sıgn In WRONG!!!\n" + "Erros : " + message, Toast.LENGTH_LONG).show();
                            login.dismiss();
                        }
                    }
                });
            }
        }

        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        init();
    }
}
