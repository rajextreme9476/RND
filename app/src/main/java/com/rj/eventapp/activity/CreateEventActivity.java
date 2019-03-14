package com.rj.eventapp.activity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rj.eventapp.R;
import com.rj.eventapp.utils.Utils;
import com.rj.eventapp.model.EventModel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

public class CreateEventActivity extends AppCompatActivity {

    EditText etDate,etTime,etEventAgenda,etParticipants;
    Button btnCreate;
    int daySelected, monthSelected, yearSelected;

    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;
    private ArrayList<EventModel> modelArrayList;
    ArrayList<String> arrayList = new ArrayList<>();

    List<String> emailParticipants = new ArrayList<>();
    // ...
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);
        getSupportActionBar().setTitle(getString(R.string.createEvent));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        modelArrayList = new ArrayList<>();
        mFirebaseInstance = FirebaseDatabase.getInstance();
        mFirebaseDatabase = mFirebaseInstance.getReference("Event");

        etDate = findViewById(R.id.etDate);
        etTime = findViewById(R.id.etTime);
        btnCreate = findViewById(R.id.btnCreate);
        etEventAgenda = findViewById(R.id.etEventAgenda);
        etParticipants = findViewById(R.id.etParticipants);

        etDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePicker();
            }
        });

        etTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePicker();
            }
        });

        etParticipants.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(CreateEventActivity.this, AddParticipantsActivity.class);
                startActivityForResult(intent, 1);
            }
        });

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                EventModel eventModel = new EventModel();
                eventModel.setEventId(System.currentTimeMillis());
                eventModel.setEventAgenda(etEventAgenda.getText().toString());
                eventModel.setEventDate(etDate.getText().toString());
                eventModel.setEventTime(etTime.getText().toString());
               /* emailParticipants.add("ravirajdesai501@gamil.com");
                emailParticipants.add("suresh@gamil.com");*/
                eventModel.setEmailParticipants(emailParticipants);

                saveFirebase(eventModel);

                finish();
            }
        });
      //  getAllEvents();
    }


    private void saveFirebase(EventModel event) {
        mFirebaseDatabase.child("Event").child(""+System.currentTimeMillis()).setValue(event);
    }

    private void getAllEvents() {

        mFirebaseDatabase.child("Event").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                modelArrayList.clear();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    //getting artist
                    EventModel eventModel = postSnapshot.getValue(EventModel.class);

                    Log.d("", "User name: " + eventModel.getEmailParticipants() + ", email " + eventModel.getEventDate());

                    //adding artist to the list
                    modelArrayList.add(eventModel);
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("", "Failed to read value.", error.toException());
            }
        });

       }

    public void showDatePicker() {
        Calendar cal = Calendar.getInstance(TimeZone.getDefault()); // Get current date
        DatePickerDialog datePicker = new DatePickerDialog(this, R.style.DatePickerStyle, datePickerListener, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));


        datePicker.setCancelable(false);
        datePicker.setTitle("Select the Event Date");
        DatePicker dp = datePicker.getDatePicker();

        cal = Calendar.getInstance(TimeZone.getDefault()); // Get current date
        dp.setMinDate(cal.getTimeInMillis());

        datePicker.show();
    }

    public void showTimePicker() {
        Calendar cal = Calendar.getInstance(TimeZone.getDefault()); // Get current date
        TimePickerDialog timePicker = new TimePickerDialog(this, R.style.DatePickerStyle, timePickerListener, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), false);

        timePicker.setCancelable(false);
        timePicker.setTitle("Select Time of event");

        timePicker.show();
    }

    private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {

        // when dialog box is closed, below method will be called.
        public void onDateSet(DatePicker view, int selectedYear, int selectedMonth, int selectedDay) {
            etDate.setText(Utils.getFormattedDateUsingDateAndTime(selectedDay, selectedMonth, selectedYear, 0, 0));
        }
    };



    private TimePickerDialog.OnTimeSetListener timePickerListener = new TimePickerDialog.OnTimeSetListener()
    {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            Log.e("TIME", "" + hourOfDay + ":" + minute);

            etTime.setText(Utils.getFormattedTimeUsingDateAndTime(daySelected, monthSelected, yearSelected, hourOfDay, minute));}

        };


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        {
            if (requestCode == 1) {
                if(resultCode == Activity.RESULT_OK){

                     arrayList=data.getExtras().getStringArrayList("list");

                     StringBuilder stringBuilder = new StringBuilder();

                    for(int i=0 ;i<arrayList.size();i++)
                    {
                        emailParticipants.add(arrayList.get(i));
                        stringBuilder.append(arrayList.get(i)+",");
                    }

                    etParticipants.setText(stringBuilder.toString());

                }
                if (resultCode == Activity.RESULT_CANCELED) {
                    //Write your code if there's no result
                }
            }
        }//onAc
    }
}
