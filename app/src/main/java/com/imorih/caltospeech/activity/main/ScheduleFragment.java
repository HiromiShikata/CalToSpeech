package com.imorih.caltospeech.activity.main;

import android.support.v7.widget.RecyclerView;

import com.imorih.caltospeech.R;
import com.imorih.caltospeech.activity.BaseAppFragment;
import com.imorih.caltospeech.service.GCalendarService;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.List;

@EFragment(R.layout.fragment_schedule_list)
public class ScheduleFragment extends BaseAppFragment {
  public interface OnListFragmentInteractionListener extends BaseOnFragmentInteractionListener {
    void onListFragmentInteraction(GCalendarService.GEvent item);
  }

  private OnListFragmentInteractionListener mListener;

  @ViewById(R.id.list)
  RecyclerView listView;

  @Bean
  GCalendarService gCalendarService;

  public static ScheduleFragment newInstance() {
    return ScheduleFragment_
        .builder()
        .build();
  }

  @AfterViews
  void afterViews() {
    List<GCalendarService.GEvent> events =
        gCalendarService.findTargetEvent();
    listView.setAdapter(new MyScheduleRecyclerViewAdapter(events, mListener));

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
