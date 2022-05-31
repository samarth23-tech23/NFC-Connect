package com.example.nfcconnect;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.sql.Array;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;


public class regActivity extends AppCompatActivity {
    EditText reguName,regemail,regPass,regnPass;
    Button rButton;
    FirebaseDatabase rootNode;
    DatabaseReference reference;
    OkHttpClient okHttpClient=new OkHttpClient();
    TextView textView,nfcView,passwordView,txtviw;
    String encPassword,encNFCpass,Key,name,emailId;
    String txtResponse;
    String password;
    String NFC_Password;

    ResponseBody txtResponse2;
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



        //Getting all the values


        //Saving data into the database
       rButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name=reguName.getText().toString();
                emailId =regemail.getText().toString();

                rootNode=FirebaseDatabase.getInstance();
                reference=rootNode.getReference("app");

                //Firebase
                UserHelperClass helperClass =new UserHelperClass(name,emailId,encPassword,encNFCpass,Key);
                reference.child(name).setValue(helperClass);
                Toast.makeText(regActivity.this, "Successfully Registered with Name "+ name, Toast.LENGTH_SHORT).show();
                reference.child(name).setValue(helperClass);

            regnPass.clearFocus();
            }
       });



        regnPass.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // If the event is a key-down event on the "enter" button
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    // Perform action on key press
                    password=regPass.getText().toString();
                    NFC_Password=regnPass.getText().toString();



                    RequestBody formbody=new FormBody.Builder().add("password",password).add("nfcpassword",NFC_Password).add("activity","register").build();
                    Request request=new Request.Builder().url("https://karthik022.pythonanywhere.com/encrypt").post(formbody).build();
                    Toast.makeText(regActivity.this, "Encrypting...", Toast.LENGTH_SHORT).show();
                    okHttpClient.newCall(request).enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(regActivity.this, "Flask response error", Toast.LENGTH_LONG).show();
                                }
                            });
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {


                            runOnUiThread(new Runnable() {

                                @Override
                                public void run() {

                                    try {
                                        txtResponse= (response.body().string());
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }

                                    String in=txtResponse;
                                    try {
                                        JSONObject reader = new JSONObject(in);

                                        encPassword = reader.getString("password");
                                        encNFCpass=reader.getString("nfcpassword");
                                        Key=reader.getString("key");

                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }


                                }
                            });

                        }
                    });




                    return true;
                }
                return false;
            }
        });





                 // Code for onBlur
                regnPass.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View v, boolean gainFocus) {
                        //onFocus
                        if (gainFocus) {
                            Toast.makeText(regActivity.this, "Focused", Toast.LENGTH_SHORT).show();
                        }
                        //onBlur
                        else {



                        }
                    }
                });







    }
}