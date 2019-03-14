package com.rj.eventapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.rj.eventapp.R;
import com.rj.eventapp.utils.RecyclerItemClickListener;
import com.rj.eventapp.adapter.InivtePeopleListAdapter;
import com.rj.eventapp.model.EmailModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class AddParticipantsActivity extends AppCompatActivity {

    ArrayList<EmailModel> emailModels;
    private Map<String, Boolean> selectedContactIds = new HashMap<>();
    TextView txt_counter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invite_people);
        getSupportActionBar().setTitle(getString(R.string.addParticipants));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

       setDummyUser();
       addContactsData();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private void setDummyUser() {

        emailModels = new ArrayList<>();
        emailModels.add(0,new EmailModel(1,"Raviraj Desai","ravirajdesai9476@gmail.com"));
        emailModels.add(1,new EmailModel(2,"Amol Pawar","amolpawar@gmail.com"));
        emailModels.add(2,new EmailModel(3,"Sachin Patil","amargupta@gmail.com"));
        emailModels.add(3,new EmailModel(4,"Naren Patel","narenan@gmail.com"));
        emailModels.add(4,new EmailModel(5,"Suresh Patil","suresh1245@gmail.com"));
        emailModels.add(5,new EmailModel(6,"Abhiaman Tamu","adminar@gmail.com"));
        emailModels.add(6,new EmailModel(7,"Vega Co.","vega123@gmail.com"));
        emailModels.add(7,new EmailModel(8,"Raj Desai","raj123@gmail.com"));

    }

    private void addContactsData() {

        RecyclerView recyclerView = (findViewById(R.id.rvParticipant));
        final InivtePeopleListAdapter adapter = new InivtePeopleListAdapter(this, emailModels,selectedContactIds);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(AddParticipantsActivity.this, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                if (!selectedContactIds.containsKey(emailModels.get(position).getEmail())){
                    selectedContactIds.put(emailModels.get(position).getEmail(), true);
                    adapter.notifyDataSetChanged();
                }else {
                    selectedContactIds.remove(emailModels.get(position).getEmail());
                    adapter.notifyDataSetChanged();
                }

            }

        }));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add_particapants, menu);
        return true;


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();


        if (id == R.id.action_done) {


            ArrayList<String> arrayList=new ArrayList<String>();

            Iterator i = selectedContactIds.keySet().iterator();

            while(i.hasNext())
            {
                String key = i.next().toString();
                boolean value = selectedContactIds.get(key);

                arrayList.add(key);
                System.out.println(key + " " + value);
            }

            Intent returnIntent = new Intent();
            returnIntent.putStringArrayListExtra("list",arrayList);
            setResult(Activity.RESULT_OK,returnIntent);
            finish();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
