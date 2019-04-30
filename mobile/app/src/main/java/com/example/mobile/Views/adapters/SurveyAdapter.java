package com.example.mobile.Views.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.mobile.R;
import com.example.mobile.Repositories.models.Enquete;
import com.example.mobile.Repositories.models.Enquete;

import java.util.ArrayList;

public class SurveyAdapter extends RecyclerView.Adapter<SurveyAdapter.ViewHolder> {

    private ArrayList<Enquete> enquetes;
    private Context context;
    
    public SurveyAdapter(Context context, ArrayList<Enquete> enquetes){
        this.enquetes = enquetes;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view  = LayoutInflater.from(context).inflate(R.layout.item_survey, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Enquete enquete = enquetes.get(i);
        viewHolder.intituleView.setText(enquete.getIntituleEnquete());
        viewHolder.sendButton.setOnClickListener(
                v -> {
                    // TODO send Answer
                }
        );

    }

    @Override
    public int getItemCount() {
        return enquetes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView intituleView;

        private Button sendButton;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            intituleView = itemView.findViewById(R.id.item_survey_intitule);
            sendButton = itemView.findViewById(R.id.item_survey_send);
        }

        public String getChosenOption () {
            return "";
        }
    }
}
