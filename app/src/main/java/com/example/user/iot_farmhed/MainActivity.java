package com.example.user.iot_farmhed;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by user on 3/5/2560.
 */

public class MainActivity extends AppCompatActivity{
    private Button adva;
    private TextView rhum, rtemp;
    private static  final String TAG = "MainActivity";

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        adva = (Button) findViewById(R.id.proc);

        Intent in = getIntent();
        rhum = (TextView) findViewById(R.id.rhum);
        rtemp = (TextView) findViewById(R.id.rtemp);
        adva.setOnClickListener(new View.OnClickListener(){
            public  void  onClick(View view) {
                Intent in = new Intent(getApplicationContext(),MainActivity2.class);
                startActivity(in);
            }
        });

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        Query lastQuery = databaseReference.child("logDHT").orderByKey().limitToLast(1);
        lastQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String humidity = dataSnapshot.child("humidity").getValue().toString();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}
