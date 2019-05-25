package com.example.mobile;

import com.example.mobile.Repositories.ConferenceRepository;
import com.example.mobile.Repositories.models.Conference;
import com.example.mobile.Views.ViewInterfaces.ConferenceListView;
import com.example.mobile.presenters.ConferenceListPresenter;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ConferenceListPresenterTest {

    @Captor
    private ArgumentCaptor<Callback<List<Conference>>> responseCaptor;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    private ConferenceListView conferenceListView;

    @Mock
    private ConferenceRepository conferenceRepository;

    @Test
    public void showConferenceList(){

        ConferenceListPresenter presenter = new ConferenceListPresenter(conferenceListView, conferenceRepository);


        presenter.loadConferenceList();

        verify(conferenceRepository).getConferences(responseCaptor.capture());
        Callback<List<Conference>> callback = responseCaptor.getValue();

        callback.onSuccess(Arrays.asList(new Conference(), new Conference()));

        verify(conferenceListView).showConferences(any(List.class));
    }

    @Test
    public void showError(){
        ConferenceListPresenter conferenceListPresenter = new ConferenceListPresenter(conferenceListView, conferenceRepository);

        conferenceListPresenter.loadConferenceList();

        verify(conferenceRepository).getConferences(responseCaptor.capture());
        Callback<List<Conference>> callback = responseCaptor.getValue();

        callback.onError(new Exception("An error occurred while loading the conference list"));

        verify(conferenceListView).showError();
    }




}
