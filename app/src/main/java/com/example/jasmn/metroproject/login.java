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

public class login extends AppCompatActivity {
   EditText username,pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username=findViewById(R.id.nametext);
        pass=findViewById(R.id.passtext);
    }

    public void login(View view) {
        BackendlessUser user=new BackendlessUser();
       String name =username.getText().toString();
       String password= pass.getText().toString();
        Backendless.UserService.login(name, password, new AsyncCallback<BackendlessUser>() {
            @Override
            public void handleResponse(BackendlessUser response) {
                Intent in=new Intent(login.this,MainActivity.class);
                startActivity(in);

            }

            @Override
            public void handleFault(BackendlessFault fault) {
                if (fault.equals("3045")){
                    Toast.makeText(login.this, "please enter values", Toast.LENGTH_SHORT).show();

                }
                if (fault.getCode().equals("3003")) {
                    Toast.makeText(login.this, "invalid data", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public void regester(View view) {
        Intent in=new Intent(login.this,register.class);
        startActivity(in);
    }
}
