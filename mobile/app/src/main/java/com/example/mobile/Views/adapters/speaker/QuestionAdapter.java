package com.example.mobile.Views.adapters.speaker;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mobile.R;
import com.example.mobile.Repositories.models.Question;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.ViewHolder> {

    private List<Question> quests;
    private Context context;

    public QuestionAdapter(Context context, List<Question> quests){
        this.context = context;
        this.quests = quests;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(
                LayoutInflater.from(context).inflate(R.layout.item_question, viewGroup, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Question quest = quests.get(i);
        // TODO enable user to customize his username
        viewHolder.usernameV.setText("Anonymous");

        SimpleDateFormat formatter = new SimpleDateFormat("MMM dd YYYY", Locale.ENGLISH);

        Calendar questTimestamp = Calendar.getInstance();
        questTimestamp.setTimeInMillis(quest.getTimestamp().getTime());

        Calendar now = Calendar.getInstance();
        now.getTime();

        if(now.get(Calendar.DAY_OF_MONTH) == questTimestamp.get(Calendar.DAY_OF_MONTH) && now.get(Calendar.MONTH) == questTimestamp.get(Calendar.MONTH) && now.get(Calendar.YEAR) == questTimestamp.get(Calendar.YEAR)){
            formatter.applyPattern("HH:mm a");
            viewHolder.timestampV.setText(formatter.format(quest.getTimestamp()));
        }
        else{
            viewHolder.timestampV.setText(formatter.format(quest.getTimestamp()));
        }


        viewHolder.contentV.setText(quest.getContent());
    }

    public void clear(){
        quests.clear();
        notifyDataSetChanged();
    }

    public void addAll(List<Question> list){
        quests.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return quests.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView usernameV;
        private TextView timestampV;
        private TextView contentV;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            usernameV = itemView.findViewById(R.id.textview_question_username);
            timestampV = itemView.findViewById(R.id.textview_question_timestamp);
            contentV = itemView.findViewById(R.id.textview_question_content);

        }
    }
}
