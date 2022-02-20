package com.example.nfcconnect;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

public class Homepage extends AppCompatActivity {
  TextView Tmonth,Tday,Tyear;

    @Override


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        Tmonth=findViewById(R.id.month);
        Tday=findViewById(R.id.date);
        Tyear=findViewById(R.id.year);

        Date currentTime=Calendar.getInstance().getTime();
        String formattedDate=DateFormat.getDateInstance(DateFormat.FULL).format(currentTime);
        String[] splitDate=formattedDate.split(",");

        Tmonth.setText(splitDate[1]);
        Tday.setText(splitDate[0]);
        Tyear.setText((splitDate[2]));

    }
    public void logout(View v){
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        finish();
    }
}