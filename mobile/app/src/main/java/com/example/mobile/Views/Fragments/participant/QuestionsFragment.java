package com.example.mobile.Views.Fragments.participant;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.mobile.R;
import com.example.mobile.Repositories.ConferenceRepository;
import com.example.mobile.Repositories.QuestionRepository;
import com.example.mobile.Repositories.models.Question;
import com.example.mobile.Views.ViewInterfaces.speaker.QuestionListView;
import com.example.mobile.Views.activities.CreateQuestionActivity;
import com.example.mobile.Views.adapters.participant.QuestionAdapter;
import com.example.mobile.presenters.speaker.QuestionListPresenter;

import java.util.List;

public class QuestionsFragment extends Fragment implements QuestionListView {

    private RecyclerView rv;
    private TextView questionCountV;
    private LinearLayout emptyListView;
    private Button reloadButton;
    private RelativeLayout listView;
    private SwipeRefreshLayout srl;
    private FloatingActionButton addQuestBtn;
    private ProgressBar progressBar;
    private ViewGroup errorView;
    private TextView errorText;
    private Button errorViewReload;

    private QuestionListPresenter presenter;
    private Context ctx;
    private int confId;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        Activity a;

        if (context instanceof Activity){
            a=(Activity) context;
            this.ctx = a;
            this.confId = a.getIntent().getExtras().getInt("confId");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.fragment_questions_participant, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        emptyListView = view.findViewById(R.id.layout_emptylist);
        listView = view.findViewById(R.id.layout_questlist);
        reloadButton = view.findViewById(R.id.button_questlist_reload);
        questionCountV = view.findViewById(R.id.textview_questionlist_count);
        addQuestBtn = view.findViewById(R.id.button_questlist_addquest);
        progressBar = view.findViewById(R.id.progressbar);
        errorView = view.findViewById(R.id.layout_errorview);
        errorText = view.findViewById(R.id.textview_error);
        errorViewReload = view.findViewById(R.id.button_reload);

        rv = view.findViewById(R.id.recyclerview_questionlist);
        srl = view.findViewById(R.id.swipecontainer_questionlist);

        rv.setLayoutManager(new LinearLayoutManager(ctx));

        presenter = new QuestionListPresenter(this, new QuestionRepository(), new ConferenceRepository());
        presenter.loadQuestions(confId);

        srl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.reloadQuestions(confId);
            }
        });

        reloadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.loadQuestions(confId);
            }
        });

        addQuestBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ctx, CreateQuestionActivity.class);
                intent.putExtra("confId", confId);
                startActivity(intent);
            }
        });


    }

    @Override
    public void showList(List<Question> quests) {
        emptyListView.setVisibility(View.GONE);
        listView.setVisibility(View.VISIBLE);

        int size = quests.size();
        if(size == 1){
            questionCountV.setText(Integer.toString(size) + " question");
        }else{
            questionCountV.setText(Integer.toString(size) + " questions");
        }
        rv.setAdapter(
                new QuestionAdapter(ctx, quests)
        );
    }

    @Override
    public void showEmptyListView() {
        emptyListView.setVisibility(View.VISIBLE);
        listView.setVisibility(View.GONE);
    }

    @Override
    public void showErrorView(String message) {
        emptyListView.setVisibility(View.GONE);
        listView.setVisibility(View.GONE);
        errorView.setVisibility(View.VISIBLE);
        errorText.setText(message);

        errorViewReload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                errorView.setVisibility(View.GONE);
                presenter.loadQuestions(confId);
            }
        });
    }

    @Override
    public void stopRefreshingView() {
        srl.setRefreshing(false);
    }

    @Override
    public void showDeleteDialog() {

    }

    @Override
    public void showProgress() {
        this.progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        this.progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showErrorSnackbar(String message) {

    }
}
