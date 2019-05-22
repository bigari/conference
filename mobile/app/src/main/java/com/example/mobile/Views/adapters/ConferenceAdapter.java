package com.example.mobile.Views.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.mobile.R;
import com.example.mobile.Repositories.models.Conference;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

public class ConferenceAdapter extends ArrayAdapter<Conference>
{

    public ConferenceAdapter(Context context, ArrayList<Conference> confs) {
        super(context, 0, confs);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        Conference conf = getItem(position);

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_conference_info, parent, false);
        }

        TextView titleV = convertView.findViewById(R.id.textview_item_conf_title);
        TextView accessCodeV = convertView.findViewById(R.id.textview_item_conf_access_code);
        TextView dateIntV = convertView.findViewById(R.id.textview_item_date_interval);

        titleV.setText(conf.getTitle());
        accessCodeV.setText(conf.getAccessCode());

        Date startDate = conf.getStartDate();
        Date endDate = conf.getEndDate();

        if(startDate.getMonth() == endDate.getMonth()){
            dateIntV.setText(
                    new SimpleDateFormat("dd - ", Locale.ENGLISH).format(startDate) +
                            new SimpleDateFormat("dd MMM YYYY", Locale.ENGLISH).format(endDate)
            );
        }
        else{
            dateIntV.setText(
                    new SimpleDateFormat("dd MMM", Locale.ENGLISH).format(startDate)
                            + " - "
                            + new SimpleDateFormat("dd MMM YYYY", Locale.ENGLISH).format(endDate)
            );
        }

        return convertView;
    }
}
