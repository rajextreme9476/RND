package com.rj.eventapp.activity;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rj.eventapp.R;
import com.rj.eventapp.adapter.EventListAdapter;
import com.rj.eventapp.model.EventModel;

import java.util.ArrayList;

public class DashboardActivity extends AppCompatActivity {

    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;
    private ArrayList<EventModel> modelArrayList;
    String userId = "0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_all_events);
        getSupportActionBar().setTitle(getString(R.string.eventList));

        init();

    }

    private void getAllEvents() {

        mFirebaseDatabase.child("Event").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                modelArrayList.clear();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    EventModel eventModel = postSnapshot.getValue(EventModel.class);
                    modelArrayList.add(eventModel);
                }
                setUpRecyclerView();
            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("", "Failed to read value.", error.toException());
            }
        });

    }

    private void setUpRecyclerView(){

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rvEvent);
        EventListAdapter adapter = new EventListAdapter(this, modelArrayList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_day_view) {


            Intent intent = new Intent(this, CalendarViewActivity.class);
            intent.putExtra("eventList",modelArrayList);
            startActivity(intent);
            return true;
        }

       /* if (id == R.id.action_week_view) {

            Intent intent = new Intent(this, EventAgendaActivity.class);
            startActivity(intent);
            return true;
        }

        if (id == R.id.action_month_view) {

            return true;
        }*/

        return super.onOptionsItemSelected(item);
    }

    private void init() {
        modelArrayList = new ArrayList<>();
        mFirebaseInstance = FirebaseDatabase.getInstance();
        mFirebaseInstance.setPersistenceEnabled(true);
        mFirebaseDatabase = mFirebaseInstance.getReference("Event");
        getAllEvents();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Click action
                Intent intent = new Intent(DashboardActivity.this, CreateEventActivity.class);
                startActivity(intent);
            }
        });
    }

}
