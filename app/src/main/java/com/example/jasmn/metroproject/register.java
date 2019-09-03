package com.example.jasmn.metroproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;

public class register extends AppCompatActivity implements AsyncCallback<BackendlessUser> {
  EditText email,name1,pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        email=findViewById(R.id.emailtxt);
        name1=findViewById(R.id.editTextname);
        pass=findViewById(R.id.editTextpass);
    }

    public void register(View view) {
        BackendlessUser user = new BackendlessUser();
        user.setEmail(email.getText().toString());
        user.setPassword(pass.getText().toString());

        user.setProperty("username", name1.getText().toString());
        Backendless.UserService.register(user, this);

    }
    @Override
    public void handleResponse(BackendlessUser response) {
        Intent in=new Intent(this,MainActivity.class);
        startActivity(in);

    }

    @Override
    public void handleFault(BackendlessFault fault) {
        if (fault.equals("3018")){
            Toast.makeText(register.this, "user already has the specified email", Toast.LENGTH_SHORT).show();
        }
        if (fault.equals("3045")){
            Toast.makeText(register.this, "please enter values", Toast.LENGTH_SHORT).show();

        }
    }
    }

