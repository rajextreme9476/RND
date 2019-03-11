package com.rj.eventapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.rj.eventapp.R;
import com.rj.eventapp.RecyclerItemClickListener;
import com.rj.eventapp.model.EmailModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class InivtePeopleListAdapter extends RecyclerView.Adapter<InivtePeopleListAdapter.ViewHolder> {


    private Context context;
    private ArrayList<EmailModel> friendsArrayList;
    private ArrayList<EmailModel> searchArraylist;
    RecyclerItemClickListener.OnItemClickListener onItemClickListener;
    private Map<String, Boolean> selectedContactIds = new HashMap<>();

    public InivtePeopleListAdapter(Context context, ArrayList<EmailModel> friendsArrayList,Map<String, Boolean> selectedContactIds) {
        this.context = context;
        this.friendsArrayList = friendsArrayList;
        this.searchArraylist = new ArrayList<EmailModel>();
        this.searchArraylist.addAll(friendsArrayList);
        this.selectedContactIds = selectedContactIds;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.inflate_event_row, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        EmailModel friendData = getItem(position);

        viewHolder.txtDisplayName.setText(friendData.getName()+"\n"+friendData.getEmail());

        if (selectedContactIds.size() > 0) {
            if (selectedContactIds.containsKey(friendData.getEmail()) && selectedContactIds.get(friendData.getEmail())) {
                selectedContactIds.put(friendData.getEmail(), true);
                viewHolder.btn_green_check.setChecked(true);
            } else {
                //selected_map.put(position, false);
                viewHolder.btn_green_check.setChecked(false);
            }
        } else {
            //selected_map.put(holder.getAdapterPosition(), false);
            viewHolder.btn_green_check.setChecked(false);
        }


    }
    // inner class to hold a reference to each item of RecyclerView
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView txtDisplayName;
        public TextView txtLastMsgText;
        public CheckBox btn_green_check;

        public ViewHolder(View view) {
            super(view);

            txtDisplayName = (TextView) view.findViewById(R.id.chat_row_name);
            txtLastMsgText = (TextView) view.findViewById(R.id.chat_row_last_msg);
            btn_green_check = (CheckBox) view.findViewById(R.id.btn_green_check);

            view.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(v, getPosition());
            }
        }
    }


    public void setOnItemClickListener(final RecyclerItemClickListener.OnItemClickListener mItemClickListener) {
        this.onItemClickListener = mItemClickListener;
    }


    @Override
    public int getItemCount() {
        return friendsArrayList.size();
    }

    public EmailModel getItem(int position) {
        return friendsArrayList.get(position);
    }


}


