
package com.example.nfcconnect;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class loginActivity extends AppCompatActivity {
    EditText mUsername,mPassword;
    Button mLoginBtn;
    ProgressBar progressBar;
    FirebaseDatabase rootNode;
    DatabaseReference reference=FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mUsername=findViewById(R.id.l_uName);
        mPassword=findViewById(R.id.l_password);
        mLoginBtn=findViewById(R.id.loginBtn);

        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    String l_Name=mUsername.getText().toString();
                    String l_password=mPassword.getText().toString();

                    if(l_Name.isEmpty()){
                        Toast.makeText(loginActivity.this, "Please enter username !", Toast.LENGTH_SHORT).show();
                    }
                    else{}
                    if(l_password.isEmpty()){
                        Toast.makeText(loginActivity.this,"Please enter password !",Toast.LENGTH_SHORT).show();
                    }
                    else{}

                reference.child("app").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.hasChild(l_Name)) {
                                final String getPassword=snapshot.child(l_Name).child("password").getValue(String.class);
                                if(getPassword.equals(l_password))
                                {
                                    Toast.makeText(loginActivity.this, "Successfully logged in!", Toast.LENGTH_SHORT).show();
                                    //opening new Activity
                                    startActivity(new Intent(loginActivity.this, Homepage.class));
                                    finish();
                                }
                        else {
                                    Toast.makeText(loginActivity.this, "Wrong Password !", Toast.LENGTH_SHORT).show();
                                }
                        }
                        else{
                            Toast.makeText(loginActivity.this, "Wrong Username !", Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });
            }
        });
    }


}