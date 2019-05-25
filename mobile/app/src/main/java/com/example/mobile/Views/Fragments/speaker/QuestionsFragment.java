package com.example.mobile.Views.Fragments.speaker;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
import android.widget.TextView;

import com.example.mobile.R;
import com.example.mobile.Repositories.QuestionRepository;
import com.example.mobile.Repositories.models.Question;
import com.example.mobile.Views.adapters.speaker.QuestionAdapter;
import com.example.mobile.Views.ViewInterfaces.speaker.QuestionListView;
import com.example.mobile.presenters.speaker.QuestionListPresenter;

import java.util.List;

public class QuestionsFragment extends Fragment implements QuestionListView {

    private QuestionListPresenter presenter;
    private RecyclerView rv;
    private TextView questionCountV;
    private LinearLayout emptyListView;
    private Button reloadButton;
    private LinearLayout listView;
    private SwipeRefreshLayout srl;
    private Context ctx;
    private int confId;

    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);

        this.ctx = activity;
        this.confId = activity.getIntent().getExtras().getInt("confId");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.fragment_questions_speaker, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        emptyListView = view.findViewById(R.id.layout_emptylist);
        listView = view.findViewById(R.id.layout_questionlist);
        reloadButton = view.findViewById(R.id.button_questlist_reload);
        questionCountV = view.findViewById(R.id.textview_questionlist_count);
        rv = view.findViewById(R.id.recyclerview_questionlist);
        srl = view.findViewById(R.id.swipecontainer_questionlist);

        rv.setLayoutManager(new LinearLayoutManager(ctx));
        rv.addItemDecoration(new DividerItemDecoration(ctx, RecyclerView.VERTICAL));

        presenter = new QuestionListPresenter(this, new QuestionRepository());
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
                presenter.reloadQuestions(confId);
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
    public void showErrorView() {

    }

    @Override
    public void stopRefreshingView() {
        srl.setRefreshing(false);
    }
}
