package com.example.mobile.Views.adapters.speaker;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;

import com.example.mobile.R;
import com.example.mobile.Repositories.models.Enquete;
import com.example.mobile.Repositories.models.Option;
import com.example.mobile.Views.activities.ManageSurveyActivity;
import com.example.mobile.presenters.speaker.SpeakerSurveyPresenter;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class SpeakerSurveyAdapter extends RecyclerView.Adapter<SpeakerSurveyAdapter.ViewHolder> {

    private List<Enquete> enquetes;
    private Context context;
    private SpeakerSurveyPresenter presenter;

    public SpeakerSurveyAdapter(Context context, List<Enquete> enquetes, SpeakerSurveyPresenter presenter){
        this.enquetes = enquetes;
        this.context = context;
        this.presenter = presenter;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view  = LayoutInflater.from(context).inflate(R.layout.item_survey_speaker, viewGroup, false);
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

        viewHolder.btnChart.setOnClickListener(v->{
            enquete.setAnimate(true);
           showChart(viewHolder, enquete);
        });

        viewHolder.btnDetails.setOnClickListener(v->{
            enquete.setStatsVisible(false);
            viewHolder.chartContainer.setVisibility(View.GONE);
            viewHolder.optionsContainer.setVisibility(View.VISIBLE);
        });

        viewHolder.btnEdit.setOnClickListener(v->{
            Gson gson = new Gson();
            String optionsJson = gson.toJson(enquete.getOptions());
            String enqueteJson = gson.toJson(enquete);
            Intent intent = new Intent(context, ManageSurveyActivity.class);
            intent.putExtra("confId", enquete.getConferenceId());
            intent.putExtra("type", "update");
            intent.putExtra("survey", enqueteJson);
            intent.putExtra("options", optionsJson);
            context.startActivity(intent);
        });

       // viewHolder.visibilitySwitch.setActivated(enquete.isVisible());

        viewHolder.visibilitySwitch.setChecked(enquete.isVisible());

        viewHolder.visibilitySwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            enquete.setVisible(isChecked);
            presenter.updateSurvey(enquete);
        });


        for (Option option : enquete.getOptions()) {

            LinearLayout layout = new LinearLayout(this.context);
            layout.setOrientation(LinearLayout.HORIZONTAL);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);

           // layout.setGravity(Gravity.CENTER);
            layout.setLayoutParams(lp);

            TextView tv = new TextView(this.context);
            tv.setText(option.getIntituleOption());
            layout.addView(tv);
            viewHolder.optionsContainer.addView(layout);
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
        private ImageButton btnChart, btnDetails, btnEdit;
        private Switch visibilitySwitch;

        private Button sendButton, btnAddOption;

        private PieChart pieChart;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            intituleView = itemView.findViewById(R.id.item_survey_speaker_intitule);
            optionsContainer = itemView.findViewById(R.id.item_survey_speaker_options);
            chartContainer = itemView.findViewById(R.id.item_survey_speaker_chart_container);
            btnChart = itemView.findViewById(R.id.item_survey_speaker_action_chart);
            btnEdit = itemView.findViewById(R.id.item_survey_speaker_action_edit);
            btnDetails = itemView.findViewById(R.id.item_survey_speaker_action_details);
            pieChart = itemView.findViewById(R.id.item_survey_speaker_chart);
            visibilitySwitch = itemView.findViewById(R.id.item_survey_speaker_public);
        }


        public String getChosenOption () {
            return "";
        }
    }
}
