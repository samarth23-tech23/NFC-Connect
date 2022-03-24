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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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
    TextView textView,nfcView,passwordView;
    String encPassword,encNFCpass;
    String txtResponse,substr2,substr;
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
                 substr="abc";
                 substr2="123";


            //flask part
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
                                   String find="password";
                                   String find2="nfcpassword";

                                   int i=txtResponse.lastIndexOf(find);
                                    substr=txtResponse.substring(i+12);
                                  encPassword=substr;

                                    int j=txtResponse.indexOf(find2);
                                     substr2=txtResponse.substring(17,i);
                                     encNFCpass=substr2;

                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        });

                     }
                 });




//Firebase
                UserHelperClass helperClass =new UserHelperClass(name,emailId,substr,substr2);
                reference.child(name).setValue(helperClass);
                Toast.makeText(regActivity.this, "Successfully Registered with Name "+ name, Toast.LENGTH_SHORT).show();
                 reference.child(name).setValue(helperClass);

            }
        });
    }
}