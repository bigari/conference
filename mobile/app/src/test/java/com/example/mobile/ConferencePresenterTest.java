package com.example.mobile;

import com.example.mobile.Repositories.ConferenceRepository;
import com.example.mobile.Repositories.models.Conference;
import com.example.mobile.Views.ConferenceListView;
import com.example.mobile.Views.ConferenceView;
import com.example.mobile.presenters.ConferenceListPresenter;
import com.example.mobile.presenters.ConferencePresenter;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;

public class ConferencePresenterTest {
    @Captor
    private ArgumentCaptor<Callback<Conference>> captor;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    private ConferenceView conferenceView;

    @Mock
    private ConferenceRepository conferenceRepository;

    @Test
    public void showConference(){

        ConferencePresenter presenter = new ConferencePresenter(conferenceView, conferenceRepository);

        presenter.loadConference(5);

        verify(conferenceRepository).getConference(anyInt(), captor.capture());

        Callback<Conference> callback = captor.getValue();

        callback.onSuccess(new Conference());

        verify(conferenceView).showConference(any(Conference.class));

    }


    @Test
    public void showError(){}
}
