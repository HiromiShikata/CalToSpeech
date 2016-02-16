package com.imorih.caltospeech.activity.main;

import android.support.v7.widget.RecyclerView;

import com.imorih.caltospeech.R;
import com.imorih.caltospeech.activity.BaseAppFragment;
import com.imorih.caltospeech.service.GCalendarService;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;
import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

@EFragment(R.layout.fragment_schedule_list)
public class ScheduleFragment extends BaseAppFragment {

  public interface OnListFragmentInteractionListener extends BaseOnFragmentInteractionListener {
    void onListFragmentInteraction(GCalendarService.GEvent item);
    void onFindEvents(List<GCalendarService.GEvent> events);

    void speak(String text);
  }

  private OnListFragmentInteractionListener mListener;

  @ViewById(R.id.list)
  RecyclerView listView;

  @Bean
  GCalendarService gCalendarService;


  MyScheduleRecyclerViewAdapter adapter;

  public static ScheduleFragment newInstance() {
    return ScheduleFragment_
        .builder()
        .build();
  }


  @Override
  public void onResume() {
    super.onResume();
    List<GCalendarService.GEvent> events =
        gCalendarService.findTargetEvent();

    Collections.sort(events, new Comparator<GCalendarService.GEvent>() {
      @Override
      public int compare(GCalendarService.GEvent lhs, GCalendarService.GEvent rhs) {
        return (int) (lhs.getBegin() - rhs.getBegin());
      }
    });
    mListener.onFindEvents(events);
    adapter = new MyScheduleRecyclerViewAdapter(events, mListener);
    listView.setAdapter(adapter);


  }

  @Override
  protected <T extends BaseOnFragmentInteractionListener> void setListener(T listener) {
    mListener = (OnListFragmentInteractionListener) listener;
  }

  @Override
  protected Class<? extends BaseOnFragmentInteractionListener> getListenerClass() {
    return OnListFragmentInteractionListener.class;
  }


}
