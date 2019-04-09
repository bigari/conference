package com.example.mobile.activities;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mobile.R;
import com.example.mobile.models.Conference;

import java.util.List;

public class ConferenceListAdapter extends RecyclerView.Adapter<ConferenceListAdapter.ViewHolder> {

    private List<Conference> conferences;
    private Context context;

    public ConferenceListAdapter(Context context, List<Conference> conferences){
        this.conferences = conferences;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view  = LayoutInflater.from(context).inflate(R.layout.item_conference_info, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Conference conference = conferences.get(i);
        viewHolder.conferenceNameView.setText(conference.getNom());
        viewHolder.conferenceDescriptionView.setText(conference.getDescription());
        viewHolder.conferenceDateView.setText(conference.getDate().toString());
        viewHolder.conferenceLocationView.setText(conference.getLieu());
    }

    @Override
    public int getItemCount() {
        return conferences.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        // TODO add like dislike button

        private TextView conferenceNameView;
        private TextView conferenceDescriptionView;
        private TextView conferenceDateView;
        private TextView conferenceLocationView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            conferenceNameView = itemView.findViewById(R.id.item_conference_title);
            conferenceDescriptionView = itemView.findViewById(R.id.item_conference_description);
            conferenceDateView = itemView.findViewById(R.id.item_conference_date);
            conferenceLocationView = itemView.findViewById(R.id.item_conference_location);
        }
    }
}
