package com.example.mobile.Views.activities;

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
import com.example.mobile.Repositories.models.Conference;
import com.example.mobile.Repositories.models.Questionnaire;

import java.util.ArrayList;
import java.util.List;

public class QuizAdapter extends RecyclerView.Adapter<QuizAdapter.ViewHolder> {

    private ArrayList<Questionnaire> questionnaires;
    private Context context;

    public QuizAdapter(Context context, ArrayList<Questionnaire> questionnaires){
        this.questionnaires = questionnaires;
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
        Questionnaire questionnaire = questionnaires.get(i);
        viewHolder.intituleView.setText(questionnaire.getIntituleQuestionnaire());
        //viewHolder.sendButton.setOnClickListener();

    }

    @Override
    public int getItemCount() {
        return questionnaires.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        // TODO add like dislike button

        private TextView intituleView;
        private EditText answerEditText;
        private Button sendButton;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            intituleView = itemView.findViewById(R.id.item_quiz_intitule);
            answerEditText = (EditText) itemView.findViewById(R.id.item_quiz_answer);
            sendButton = itemView.findViewById(R.id.item_quiz_send);
        }
    }
}
