package com.rj.eventapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.rj.eventapp.model.EventModel;
import com.rj.eventapp.R;

import java.util.List;

public class EventListAdapter extends RecyclerView.Adapter<EventListAdapter.ViewHolder> {

    private Context applicationContext;
    private List<EventModel> list;

    public EventListAdapter(Context applicationContext, List<EventModel> list) {
        this.applicationContext = applicationContext;
        this.list = list;
    }

    @NonNull
    @Override
    public EventListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.include_events, parent, false);
        return new EventListAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull EventListAdapter.ViewHolder holder, int position) {

        holder.eventTitle.setText(list.get(position).getEventAgenda());
        holder.dateTimeByName.setText(list.get(position).getEventDate()+" "+list.get(position).getEventTime());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView eventTitle, dateTimeByName, event_type,tv_participant;

        ViewHolder(View view) {
            super(view);
            eventTitle = (TextView) view.findViewById(R.id.eventTitle);
            dateTimeByName = (TextView) view.findViewById(R.id.dateTime);
            event_type = (TextView) view.findViewById(R.id.event_type);
            tv_participant = (TextView) view.findViewById(R.id.tv_participant);

        }
    }
}
