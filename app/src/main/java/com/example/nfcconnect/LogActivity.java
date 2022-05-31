package com.example.nfcconnect;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class LogActivity extends AppCompatActivity {
    Button btn;
    TextView tv1;
    String ma,name;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);
    btn=findViewById(R.id.setBtn);
        tv1=findViewById(R.id.output);
//reference= FirebaseDatabase.getInstance().getReference("raspberry pi").child("Logs").child(sam);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            name = extras.getString("username");
            //The key argument here must match that used in the other activity
        }
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            DatabaseReference reference= FirebaseDatabase.getInstance().getReference("raspberry pi/Logs");
            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                    StringBuilder sb=new StringBuilder();
                    for (DataSnapshot snapshot1: datasnapshot.getChildren())
                    {

                         ma= snapshot1.child(name).getValue().toString();
                        Log.d("siddhesh",ma);
                         sb.append(ma+"\n");
                        Log.d("op",ma);
                        tv1.setText(ma);
                        try {

                            JSONObject reader = new JSONObject(ma);
                            String Time = reader.getString("Time");
                            String Date=reader.getString("Date");
                            Toast.makeText(LogActivity.this, Time, Toast.LENGTH_SHORT).show();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

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