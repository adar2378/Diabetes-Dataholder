package com.example.masteradar.diabetesdataholder;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;


public class RecordList extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MyAdapter adapter;
    MainActivity mainActivity;
    ArrayList<UserDetails> userDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        int VERTICAL_ITEM_SPACE = 48;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_list);
        mainActivity = new MainActivity();
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        userDetails = mainActivity.getArray().get(MainActivity.currentUser).getDetails();



        adapter = new MyAdapter(getApplicationContext(), userDetails);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.addItemDecoration(new VerticalSpaceItemDecoration(VERTICAL_ITEM_SPACE));
        recyclerView.setAdapter(adapter);

    }
}
