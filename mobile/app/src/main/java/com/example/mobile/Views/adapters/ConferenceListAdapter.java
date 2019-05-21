package com.example.mobile.Views.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mobile.R;
import com.example.mobile.Repositories.models.Conference;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;


public class ConferenceListAdapter extends RecyclerView.Adapter<ConferenceListAdapter.ConfViewHolder> {

    private List<Conference> confs;
    private Context context;

    public ConferenceListAdapter(List<Conference> confs, Context context){
        this.confs = confs;
        this.context = context;
    }

    @NonNull
    @Override
    public ConfViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_conference_info, parent, false);
        return new ConfViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ConfViewHolder holder, int position) {
        Conference conf = confs.get(position);

        holder.titleV.setText(conf.getTitle());
        holder.accessCodeV.setText(conf.getAccessCode());

        Date startDate = conf.getStartDate();
        Date endDate = conf.getEndDate();

        if(startDate.getMonth() == endDate.getMonth()){
            holder.dateIntV.setText(
                    new SimpleDateFormat("dd - ", Locale.ENGLISH).format(startDate) +
                    new SimpleDateFormat("dd MMM YYYY", Locale.ENGLISH).format(endDate)
            );
        }
        else{
            holder.dateIntV.setText(
                    new SimpleDateFormat("dd MMM", Locale.ENGLISH).format(startDate)
                            + " - "
                            + new SimpleDateFormat("dd MMM YYYY", Locale.ENGLISH).format(endDate)
            );
        }
    }

    @Override
    public int getItemCount() {
        return confs.size();
    }

    public class ConfViewHolder extends RecyclerView.ViewHolder{

        private TextView titleV;
        private TextView accessCodeV;
        private TextView dateIntV;


        public ConfViewHolder(@NonNull View itemView) {
            super(itemView);
            titleV = itemView.findViewById(R.id.textview_item_conf_title);
            accessCodeV = itemView.findViewById(R.id.textview_item_conf_access_code);
            dateIntV = itemView.findViewById(R.id.textview_item_date_interval);
        }
    }
}
