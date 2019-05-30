package com.example.mobile.presenters;

import android.content.SharedPreferences;
import android.util.Log;

import com.example.mobile.Callback;
import com.example.mobile.Repositories.ConfListCache;
import com.example.mobile.Repositories.ConferenceRepository;
import com.example.mobile.Repositories.models.Conference;
import com.example.mobile.Views.ViewInterfaces.ConferenceListView;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ConferenceListPresenter {

    private ConferenceListView view;
    private ConferenceRepository repository;
    private ConfListCache confListCache;

    private int cid;
    private int index;


    public ConferenceListPresenter(ConferenceListView view, ConferenceRepository repository){
        this.view = view;
        this.repository = repository;
        this.confListCache = ConfListCache.getInstance();
    }

    public void loadConfs(int uid, String token){

        view.showLoading();
        if(confListCache.getActiveConfs().isEmpty() && confListCache.getPastConfs().isEmpty()){

            repository.getConferences(uid, token, new Callback<List<Conference>>() {
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
                    Log.e("conference list", "loading conference list failed", error);
                    view.hideLoading();
                    view.showErrorView();
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

        repository.deleteConference(cid, token, new Callback<Void>() {
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
                view.showErrorView();
                view.hideLoading();
            }
        });


    }

    public void cancel(){
        this.cid = 0;
        this.index = 0;
    }


}
