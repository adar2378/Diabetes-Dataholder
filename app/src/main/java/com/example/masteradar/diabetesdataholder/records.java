package com.example.masteradar.diabetesdataholder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.MissingFormatArgumentException;

public class records extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_records);
        final MainActivity mainActivity = new MainActivity();
        final boolean isEmpty = new MainActivity().getArray().get(MainActivity.currentUser).getDetails().isEmpty();
        Button addButton = (Button) findViewById(R.id.addButton);
        Button logOutButton = (Button) findViewById(R.id.logoutButton);
        Button viewRecord = (Button) findViewById(R.id.recordButton);
        TextView currentUserText = (TextView) findViewById(R.id.currentUserText);
        currentUserText.setText("Logged in as:\n"+mainActivity.getArray().get(MainActivity.currentUser).getUsername().toString());

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(),Input.class);
                startActivity(intent);
            }
        });

        logOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.currentUser = -1;
                Intent intent = new Intent(view.getContext(),MainActivity.class);
                startActivity(intent);
            }
        });
        viewRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(isEmpty){
                    Toast.makeText(records.this, "No entry yet!", Toast.LENGTH_SHORT).show();
                }
                else{
                    Intent intent = new Intent(view.getContext(), RecordList.class);
                    startActivity(intent);
                }


            }
        });

    }
    @Override
    public void onBackPressed() {
        Toast.makeText(this, "Press logout!", Toast.LENGTH_SHORT).show();
    }
}
