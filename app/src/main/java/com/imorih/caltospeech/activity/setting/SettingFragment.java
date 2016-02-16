package com.imorih.caltospeech.activity.setting;

import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;

import com.imorih.caltospeech.R;
import com.imorih.caltospeech.activity.BaseAppFragment;
import com.imorih.caltospeech.service.GCalendarService;
import com.imorih.caltospeech.setting.Setting;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.List;

@EFragment(R.layout.fragment_setting)
public class SettingFragment
    extends BaseAppFragment {
  public interface OnFragmentInteractionListener extends BaseOnFragmentInteractionListener {
  }

  private OnFragmentInteractionListener mListener;

  @Bean
  Setting setting;

  @Bean
  GCalendarService gCalendarService;

  @ViewById(R.id.fragment_setting_calendars)
  ListView calendarList;

  @ViewById(R.id.fragment_setting_event_name_regex)
  EditText eventNameRegex;

  @ViewById(R.id.fragment_setting_speak_english_if_only_alphabet)
  Switch speakEnglish;

  @ViewById(R.id.fragment_setting_speak_later_all_day_event)
  Switch speakLaterAllDayEvent;

  GCalendarAdapter calendarAdapter;


  public static SettingFragment newInstance() {
    return SettingFragment_
        .builder()
        .build();
  }

  @Override
  public void onResume() {
    super.onResume();
    eventNameRegex.setText(setting.targetEventNameRegex());
    speakEnglish.setChecked(setting.isSpeakEnglishIfOnlyAlphabet());
    speakLaterAllDayEvent.setChecked(setting.isSpeakLastAllDayEvent());
    calendarAdapter.setSelecttedCalndarIds(setting.targetCalendars());

  }

  public void onDestroy() {
    setting.setSpeakEnglishIfOnlyAlphabet(speakEnglish.isChecked());

    setting.setSpeakLastAllDayEvent(speakLaterAllDayEvent.isChecked());
    setting.setTargetCalendars(calendarAdapter.getSelectedalenarIds());
    setting.setTargetEventNameRegex(eventNameRegex.getText().toString());

    super.onDestroy();
  }

  @AfterViews
  void afterViews() {

    calendarAdapter = new GCalendarAdapter(getActivity());
    List<GCalendarService.GCalendar> gCalendars = gCalendarService.getCalendars();
    calendarAdapter.addAll(gCalendars);
    calendarList.setAdapter(calendarAdapter);
  }


  @Override
  protected <T extends BaseOnFragmentInteractionListener> void setListener(T listener) {
    mListener = (OnFragmentInteractionListener) listener;
  }

  @Override
  protected Class<? extends BaseOnFragmentInteractionListener> getListenerClass() {
    return OnFragmentInteractionListener.class;
  }
}
