package com.example.masteradar.diabetesdataholder;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Environment;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    static int currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final EditText usernameText = (EditText) findViewById(R.id.userName);
        Button loginButton = (Button) findViewById(R.id.loginButton);
        Button createIdButton = (Button) findViewById(R.id.signUpButton);

        SharedPreferences preferences1 = getPreferences(MODE_PRIVATE);
        if (preferences1.getBoolean("firstrun", true)) {
            // Do first run stuff here then set 'firstrun' as false
            // using the following line to edit/commit prefs
            ArrayList<UserProfile> user = new ArrayList<>();
            saveArray(user);

            preferences1.edit().putBoolean("firstrun", false).commit();
        }

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean found = false;
                for (int i = 0;i < getArray().size();i++){
                    if (usernameText.getText().toString().equals(getArray().get(i).getUsername())){
                        found = true;
                        currentUser = i;
                        break;

                    }

                }
                if(found==true){
                    Intent intent = new Intent(view.getContext(),records.class);
                    startActivity(intent);

                }
                else{
                    Toast.makeText(MainActivity.this, "Username doesn't exist!", Toast.LENGTH_SHORT).show();
                    usernameText.setText("");

                }

            }
        });



        createIdButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<UserProfile> users = getArray();
                for (int i =0;i<users.size();i++){
                    Log.d("tag2",users.get(i).getUsername());
                }


                //checking first run
                UserProfile newUser = new UserProfile();
                boolean found = false;
                for (int i = 0;i < getArray().size();i++){
                    if (usernameText.getText().toString().equals(getArray().get(i).getUsername())){
                        found = true;
                        break;

                    }

                }
                if(found==false){
                    newUser.setUsername(usernameText.getText().toString());

                    users.add(newUser);
                    saveArray(users);
                    currentUser = users.size()-1;
                    Log.d("tag3",users.get(currentUser).getUsername() );
                    Intent intent = new Intent(view.getContext(),records.class);
                    startActivity(intent);

                }
                else{
                    Toast.makeText(MainActivity.this, "Username already exists!", Toast.LENGTH_SHORT).show();
                    usernameText.setText("");

                }



            }
        });


    }
    //saving the arraylist
    void saveArray(ArrayList<UserProfile> user) {
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            try {
                // create a file in downloads directory
                FileOutputStream fos =
                        new FileOutputStream(
                                new File(Environment.getExternalStorageDirectory(), "/data.dat")
                        );
                ObjectOutputStream os = new ObjectOutputStream(fos);
                os.writeObject(user);
                os.close();
                Log.v("MyApp", "File has been written");
            } catch (Exception ex) {
                ex.printStackTrace();
                Log.v("MyApp", "File didn't write");
            }
        }
    }
    //getting the arraylist
    ArrayList<UserProfile> getArray() {
        ArrayList<UserProfile> userlist = new ArrayList<>();
        FileInputStream inStream;
        try {
            File f = new File(Environment.getExternalStorageDirectory(), "/data.dat");
            inStream = new FileInputStream(f);
            ObjectInputStream objectInStream = new ObjectInputStream(inStream);

            userlist = ((ArrayList<UserProfile>) objectInStream.readObject());
            objectInStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return userlist;
    }
    boolean doubleBackToExitPressedOnce = false;
    //clicking back button twice will exit the app
    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            Intent intent = new Intent(Intent.ACTION_MAIN);//going to home
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }
}
