package com.example.mobile.presenters;

import android.content.SharedPreferences;
import android.util.Log;

import com.example.mobile.Callback;
import com.example.mobile.Repositories.ConfListCache;
import com.example.mobile.Repositories.ConferenceRepository;
import com.example.mobile.Repositories.QuestionRepository;
import com.example.mobile.Repositories.SpeakerRepository;
import com.example.mobile.Repositories.models.Conference;
import com.example.mobile.Views.ViewInterfaces.ConferenceListView;

import java.net.ConnectException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ConferenceListPresenter {

    private ConferenceListView view;
    private ConferenceRepository confRepo;
    private SpeakerRepository speakerRepo;
    private ConfListCache confListCache;

    private int cid;
    private int index;


    public ConferenceListPresenter(ConferenceListView view, ConferenceRepository confRepo, SpeakerRepository speakerRepo){
        this.view = view;
        this.confRepo = confRepo;
        this.speakerRepo = speakerRepo;
        this.confListCache = ConfListCache.getInstance();
    }

    public void loadConfs(int uid, String token){

        view.showLoading();
        if(confListCache.getActiveConfs().isEmpty() && confListCache.getPastConfs().isEmpty()){

            confRepo.getConferences(uid, token, new Callback<List<Conference>>() {
                @Override
                public void onSuccess(List<Conference> confs) {
                    List<Conference> activeConfs = new ArrayList<>();
                    List<Conference> pastConfs = new ArrayList<>();

                    if(confs.isEmpty()){
                        view.hideLoading();
                        view.showEmptyListView();
                        return;
                    }

                    int confsSize = confs.size();

                    Date now = new Date(Calendar.getInstance().getTimeInMillis());
                    int i = 0;
                    while(i < confsSize && confs.get(i).getEndDate().after(now)){
                        activeConfs.add(confs.get(i));
                        i++;
                    }
                    pastConfs = confs.subList(i, confs.size());

                    confListCache.setActiveConfs(activeConfs);
                    confListCache.setPastConfs(pastConfs);


                    if(activeConfs.isEmpty()){
                        view.hideLoading();
                        view.showConfs(pastConfs, "past");
                        return;
                    }
                    else if(pastConfs.isEmpty()){
                        view.hideLoading();
                        view.showConfs(activeConfs, "active");
                        return;
                    }
                    view.hideLoading();
                    view.showConfs(activeConfs, pastConfs);
                }

                @Override
                public void onError(Throwable error) {
                    view.hideLoading();
                    if(error instanceof ConnectException){
                        view.showErrorView("Network error, please check your internet connection.");
                    }
                    else{
                        view.showErrorView("An error occurred.");
                    }
                }
            });
        }
        else{
            loadConfs();
        }
    }

    public void delete(int index, int cid){
        view.showDeleteDialog();
        this.cid = cid;
        this.index = index;
    }

    public void loadConfs(){
        view.showLoading();
        if(confListCache.getActiveConfs().isEmpty() && confListCache.getPastConfs().isEmpty()){
            view.hideLoading();
            view.showEmptyListView();
        }
        else if(confListCache.getActiveConfs().isEmpty()){
            view.hideLoading();
            view.showConfs(confListCache.getPastConfs(), "past");
            return;
        }
        else if(confListCache.getPastConfs().isEmpty()){
            view.hideLoading();
            view.showConfs(confListCache.getActiveConfs(), "active");
            return;
        }
        view.showConfs(confListCache.getActiveConfs(), confListCache.getPastConfs());
        view.hideLoading();
    }

    public void confirm(String token){
        view.showLoading();
        confRepo.deleteConference(cid, token, new Callback<Void>() {
            @Override
            public void onSuccess(Void value) {
                List<Conference> activeConfs = confListCache.getActiveConfs();
                List<Conference> pastConfs = confListCache.getPastConfs();

                if(activeConfs.size() - 1 >= index && pastConfs.size() - 1 >= index){
                    if(activeConfs.get(index).getId() == cid){
                        activeConfs.remove(index);
                    }
                    else{
                        pastConfs.remove(index);
                    }
                }
                else if(pastConfs.size() - 1 >= index){
                    pastConfs.remove(index);
                }
                else{
                    activeConfs.remove(index);
                }

                loadConfs();

                view.hideLoading();
            }

            @Override
            public void onError(Throwable error) {
                if(error instanceof ConnectException){
                    view.showErrorSnackbar("Network error, Check your internet connection.");
                }
                else{
                    view.showErrorSnackbar("An error occurred.");
                }
                view.hideLoading();
            }
        });
    }

    public void cancel(){
        this.cid = 0;
        this.index = 0;
    }

    public void logout(String token){
        view.showLoading();
        speakerRepo.logout(token, new Callback<Void>() {
            @Override
            public void onSuccess(Void value) {
                view.hideLoading();
                view.navToLandingView();
            }

            @Override
            public void onError(Throwable error) {
                view.hideLoading();
                view.showErrorSnackbar("Network error, check your internet connection.");
            }
        });
    }

    public void clearSharedPrefs(SharedPreferences sp){
        sp.edit().clear().apply();
    }
}
