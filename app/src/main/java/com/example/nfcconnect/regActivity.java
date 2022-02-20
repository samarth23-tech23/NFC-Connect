package com.example.nfcconnect;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class regActivity extends AppCompatActivity {
    EditText reguName,regemail,regPass,regnPass;
    Button rButton;
    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);

        reguName=findViewById(R.id.uName);
        regemail=findViewById(R.id.eId);
        rButton=findViewById(R.id.regbtn2);
        regemail=findViewById(R.id.eId);
        regPass=findViewById(R.id.pId);
        regnPass=findViewById(R.id.npId);

        //Saving data into the database
       rButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                rootNode=FirebaseDatabase.getInstance();
                reference=rootNode.getReference("app");
                //Getting all the values
                String name=reguName.getText().toString();
                String emailId =regemail.getText().toString();
                String password=regPass.getText().toString();
                String NFC_Password=regnPass.getText().toString();
                UserHelperClass helperClass =new UserHelperClass(name,emailId,password,NFC_Password);
                reference.child(name).setValue(helperClass);
              //  reference.child(name).setValue(helperClass);


            }
        });
    }
}