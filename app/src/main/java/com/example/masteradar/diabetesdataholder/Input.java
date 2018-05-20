package com.example.masteradar.diabetesdataholder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.ArrayList;

public class Input extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);
        final EditText diabteseLevel = (EditText) findViewById(R.id.editText);
        final EditText BPhigh = (EditText) findViewById(R.id.highText);
        final EditText BPlow = (EditText) findViewById(R.id.lowText);
        final RadioButton radioButton1 = (RadioButton) findViewById(R.id.beforeEating);
        final RadioButton radioButton2 = (RadioButton) findViewById(R.id.afterEating);
        final MainActivity mainActivity = new MainActivity();
        final ArrayList<UserProfile> user = mainActivity.getArray(); //retrieving data
        final ArrayList<UserDetails> userProfile = user.get(MainActivity.currentUser).getDetails();//retrieving all the saved record of current username
        Button saveButton = (Button) findViewById(R.id.saveButton);

        radioButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(radioButton1.isChecked()){
                    radioButton2.setChecked(false);
                }
                else{
                    radioButton1.setChecked(false);
                }
            }
        });
        radioButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(radioButton2.isChecked()){
                    radioButton1.setChecked(false);
                }
                else{
                    radioButton2.setChecked(false);
                }
            }
        });
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("tagSave","");
                UserDetails userDetails = new UserDetails();
                userDetails.setDiabetesLevel(Float.parseFloat((diabteseLevel.getText()).toString()));
                userDetails.setBPHigh(Integer.parseInt(BPhigh.getText().toString()));
                userDetails.setBPLow(Integer.parseInt(BPlow.getText().toString()));
                userDetails.setAte(radioButton1.isChecked());
                userDetails.setTime(userDetails.getTime());
                userProfile.add(userDetails);//saving the current entry to the record!

                user.get(MainActivity.currentUser).setDetails(userProfile);
                mainActivity.saveArray(user);//saving in file

                Toast.makeText(Input.this, "Information saved!\n"+user.get(MainActivity.currentUser).getDetails().get(userProfile.size()-1).getTime(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(view.getContext(),records.class);
                startActivity(intent);
            }
        });
    }
}
