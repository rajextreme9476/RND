package com.rj.eventapp.activity;

import android.os.Bundle;
import android.util.Log;

import com.alamkanak.weekview.WeekViewEvent;
import com.rj.eventapp.calendar.BaseActivity;
import com.rj.eventapp.model.EventModel;
import com.rj.eventapp.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class CalendarViewActivity extends BaseActivity {

    ArrayList<EventModel> eventList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

         eventList =  (ArrayList<EventModel>)getIntent().getSerializableExtra("eventList");

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private Long convertDate(String eventDate , String eventTime){

        try {
            String dateString = eventDate+" "+eventTime;

            SimpleDateFormat sdf = new SimpleDateFormat("dd-MMMM-yyyy HH:mm:ss a");

            Date date = sdf.parse(dateString);

            long startDate = date.getTime();

            return startDate;
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return 0L;

    }

    @Override
    public List<? extends WeekViewEvent> onMonthChange(int newYear, int newMonth) {
        // Populate the week view with some events.
        List<WeekViewEvent> events = new ArrayList<WeekViewEvent>();

        for(int i =0 ; i < eventList.size() ; i++ )
        {

            Date d = new Date(convertDate(eventList.get(i).getEventDate(),eventList.get(i).getEventTime()));

            Calendar startTimeTest = Calendar.getInstance();
            startTimeTest.setTime(d);

            startTimeTest.set(Calendar.HOUR_OF_DAY, d.getHours());
            startTimeTest.set(Calendar.MINUTE, 00);
            startTimeTest.set(Calendar.MONTH, newMonth - 2);
            startTimeTest.set(Calendar.YEAR, newYear);

            Calendar endTimeTest = (Calendar) startTimeTest.clone();
            endTimeTest.set(Calendar.HOUR_OF_DAY, d.getHours()+1);
            endTimeTest.set(Calendar.MINUTE, 00);
            endTimeTest.set(Calendar.MONTH, newMonth - 2);
            WeekViewEvent event = new WeekViewEvent(i,eventList.get(i).getEventAgenda(),eventList.get(i).getEventAgenda(), startTimeTest, endTimeTest);
            event.setColor(getResources().getColor(R.color.bg_main));
            events.add(event);

        }

        return events;
    }




}
