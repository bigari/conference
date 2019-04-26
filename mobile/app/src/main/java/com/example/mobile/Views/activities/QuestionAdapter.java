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
import com.example.mobile.Repositories.models.Question;

import java.util.ArrayList;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.ViewHolder> {

    private ArrayList<Question> questions;
    private Context context;

    public QuestionAdapter(Context context, ArrayList<Question> questions){
        this.questions = questions;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view  = LayoutInflater.from(context).inflate(R.layout.item_question, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Question Question = questions.get(i);
        viewHolder.intituleView.setText(Question.getIntitule());

    }

    @Override
    public int getItemCount() {
        return questions.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView intituleView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            intituleView = itemView.findViewById(R.id.item_question_intitule);

        }
    }
}
