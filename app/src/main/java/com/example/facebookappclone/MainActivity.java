package com.example.facebookappclone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashSet;

public class MainActivity extends AppCompatActivity {

    private Button btn1, btn2;
private EditText logmail, logpass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //Removing Title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn1 = (Button) findViewById(R.id.button);
        btn2 = (Button) findViewById(R.id.button2);

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,MainActivity2.class);
                startActivity(intent);

            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logmail = (EditText) findViewById(R.id.logmail);
                logpass = (EditText) findViewById(R.id.logpass);
                String s1 = logmail.getText().toString().trim();
                String s2 = logpass.getText().toString().trim();
                String [] arr = s1.split("@");
                String node = arr[0]+s2;
                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Database");
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot Datasnapshot) {
                        if(Datasnapshot.getValue().toString().trim().contains("Password ="+s2+",") && Datasnapshot.getValue().toString().trim().contains("Email ="+s1+",")){
                            Toast.makeText(MainActivity.this, "Welcome", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(MainActivity.this,MainActivity3.class);
                            intent.putExtra("Node",node);
                            startActivity(intent);

                        }
                        else{
                            Toast.makeText(MainActivity.this, "Wrong Input, Try Again", Toast.LENGTH_LONG).show();
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {


                    }

                });
//                for (String x : al){
//                    Toast.makeText(MainActivity.this, x, Toast.LENGTH_LONG).show();
//                }
//if(al.contains("This is Raw")){
//    Toast.makeText(MainActivity.this, "Found", Toast.LENGTH_LONG).show();
//}else{
//    Toast.makeText(MainActivity.this, "Not Found", Toast.LENGTH_LONG).show();
//}

            }

        });

    }
}