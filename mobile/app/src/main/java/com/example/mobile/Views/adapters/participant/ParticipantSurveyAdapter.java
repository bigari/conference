package com.example.mobile.Views.adapters.participant;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.mobile.R;
import com.example.mobile.Repositories.models.Enquete;
import com.example.mobile.Repositories.models.Option;
import com.example.mobile.Repositories.models.Participant;
import com.example.mobile.Repositories.models.Vote;
import com.example.mobile.presenters.participant.SurveyPresenter;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

public class ParticipantSurveyAdapter extends RecyclerView.Adapter<ParticipantSurveyAdapter.ViewHolder> {

    private List<Enquete> enquetes;
    private Context context;
    private SurveyPresenter presenter;
    
    public ParticipantSurveyAdapter(Context context, List<Enquete> enquetes, SurveyPresenter presenter){
        this.enquetes = enquetes;
        this.context = context;
        this.presenter = presenter;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view  = LayoutInflater.from(context).inflate(R.layout.item_survey, viewGroup, false);
        return new ViewHolder(view);
    }

    private void showChart(ViewHolder viewHolder, Enquete enquete) {
        enquete.setStatsVisible(true);
        viewHolder.chartContainer.setVisibility(View.VISIBLE);
        viewHolder.optionsContainer.setVisibility(View.GONE);
        int total = enquete.getTotalVoteCount();
        if (total != 0) {
            ArrayList<PieEntry> stats = new ArrayList();
            List<Option> options = enquete.getOptions();
            for (int j=0; j<options.size(); j++) {
                stats.add(
                        new PieEntry(
                                100 * (float) (options.get(j).getVoteCount()) / total,
                                options.get(j).getIntituleOption()
                        )
                );
            }
            PieDataSet pieDataSet = new PieDataSet(stats, "(%)");
            pieDataSet.setValueTextColor(Color.rgb(254,254,254));
            PieData pieData = new PieData(pieDataSet);
            pieData.setValueTextSize(18f);
            pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
            viewHolder.pieChart.setData(pieData);
            viewHolder.pieChart.getDescription().setText(enquete.getIntituleEnquete());
            viewHolder.pieChart.getDescription().setTextSize(14f);
            viewHolder.pieChart.getLegend().setTextSize(14f);
            viewHolder.pieChart.setEntryLabelColor(Color.rgb(0,0,0));
           // viewHolder.pieChart.spin(500, 0, -360f, Easing.EasingOption.EaseInOutBounce);
            if (enquete.isAnimate()) {
                viewHolder.pieChart.animateXY(2000, 2000);
            }

            enquete.setAnimate(false);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Enquete enquete = enquetes.get(i);
        viewHolder.intituleView.setText(enquete.getIntituleEnquete());
        if (enquete.isStatsVisible()) {
            showChart(viewHolder, enquete);
        } else {
            viewHolder.chartContainer.setVisibility(View.GONE);
            viewHolder.optionsContainer.setVisibility(View.VISIBLE);
        }

        viewHolder.btnBack.setOnClickListener(v->{
            enquete.setStatsVisible(false);
            viewHolder.chartContainer.setVisibility(View.GONE);
            viewHolder.optionsContainer.setVisibility(View.VISIBLE);

        });

        viewHolder.btnChart.setOnClickListener(v->{
            enquete.setAnimate(true);
            showChart(viewHolder, enquete);
        });
        for (Option option : enquete.getOptions()) {
            RadioButton optionRadio = new RadioButton(context);
            optionRadio.setText(option.getIntituleOption());
            optionRadio.setId(option.getId());
            viewHolder.optionGroup.addView(optionRadio);


            optionRadio.setOnClickListener(v-> {
                this.presenter.vote(
                        i,
                        new Vote(Participant.current.getId(),
                        Participant.current.getAccessKey(),
                        option.getId()
                    )
                );
            });
        }

    }

    @Override
    public int getItemCount() {
        return enquetes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView intituleView;
        private RadioGroup optionGroup;
        private LinearLayout optionsContainer, chartContainer;
        private ImageButton btnChart, btnBack;

        private Button sendButton;

        private PieChart pieChart;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            intituleView = itemView.findViewById(R.id.item_survey_intitule);
            optionGroup = itemView.findViewById(R.id.item_survey_options);
            optionsContainer = itemView.findViewById(R.id.item_survey_options_container);
            chartContainer = itemView.findViewById(R.id.item_survey_chart_container);
            btnBack = itemView.findViewById(R.id.item_survey_action_back);
            btnChart = itemView.findViewById(R.id.item_survey_action_chart);
            pieChart = itemView.findViewById(R.id.item_survey_chart);

        }


        public String getChosenOption () {
            return "";
        }
    }
}
