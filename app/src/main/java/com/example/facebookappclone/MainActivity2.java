package com.example.facebookappclone;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.FirebaseDatabase;

import java.util.LinkedHashMap;

public class MainActivity2 extends AppCompatActivity {

private Button btn3;
private EditText ed1,ed2,ed3,ed4,ed5,ed6;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        btn3 = (Button) findViewById(R.id.regsignup);
        ed1 = (EditText)findViewById(R.id.regname);
        ed2 = (EditText)findViewById(R.id.regemail);
        ed3 = (EditText)findViewById(R.id.regphone);
        ed4 = (EditText)findViewById(R.id.regprof);
        ed5 = (EditText)findViewById(R.id.regedu);
        ed6 = (EditText)findViewById(R.id.regbio);


        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String s1 = ed1.getText().toString().trim();
                String s2 = ed2.getText().toString().trim();
                String s3 = ed3.getText().toString().trim();
                String s4 = ed4.getText().toString().trim();
                String s5 = ed5.getText().toString().trim();
                String s6 = ed6.getText().toString().trim();


                if(s1.isEmpty()||s2.isEmpty()||s3.isEmpty()||s4.isEmpty()||s5.isEmpty()||s6.isEmpty()){
                    Toast.makeText(MainActivity2.this, "Fill All Boxes", Toast.LENGTH_LONG).show();
                }else {

                    LinkedHashMap<String,Object> hm = new LinkedHashMap<String,Object>();
                    hm.put("Name ",s1);
                    hm.put("Email ",s2);
                    hm.put("Password ",s3);
                    hm.put("Profession ",s4);
                    hm.put("Education ",s5);
                    hm.put("Bio ",s6);
                    String [] arr = s2.split("@");
                    String node = arr[0]+s3;
                    hm.put("Node ",node);
                    FirebaseDatabase.getInstance().getReference().child("Database").child(node).updateChildren(hm);
                    Toast.makeText(MainActivity2.this, "Registration Successful", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(MainActivity2.this,MainActivity.class);
                    startActivity(intent);


                }

            }
        });




    }
}