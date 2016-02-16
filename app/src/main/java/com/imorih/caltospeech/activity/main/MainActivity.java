package com.imorih.caltospeech.activity.main;

import android.speech.tts.TextToSpeech;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.imorih.caltospeech.R;
import com.imorih.caltospeech.activity.BaseAppActivity;
import com.imorih.caltospeech.activity.setting.SettingActivity;
import com.imorih.caltospeech.service.GCalendarService;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.ViewById;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@EActivity(R.layout.activity_main)
@OptionsMenu(R.menu.menu_main)
public class MainActivity extends BaseAppActivity
    implements ScheduleFragment.OnListFragmentInteractionListener,
    TextToSpeech.OnInitListener {

  private static final Pattern PAT = Pattern.compile("^[a-zA-Z0-9\\s:\\-\\?!]+$");

  @ViewById(R.id.toolbar)
  Toolbar toolbar;

  @ViewById(R.id.fab)
  FloatingActionButton fab;

  List<GCalendarService.GEvent> events;

  int ttsStatus = TextToSpeech.ERROR;

  @AfterViews
  void afterViews() {
    setSupportActionBar(toolbar);

    ScheduleFragment f = ScheduleFragment.newInstance();
    showFragment(f, R.id.activity_main_schedule_list);
    textToSpeech = new TextToSpeech(this, this);
  }

  TextToSpeech textToSpeech;

  @Click(R.id.fab)
  void onClickFab(View view) {
    Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
        .setAction("Action", null).show();
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    int id = item.getItemId();

    if (id == R.id.action_settings) {
      SettingActivity.startActivity(this);
      return true;
    }

    return super.onOptionsItemSelected(item);
  }


  @Override
  public void onListFragmentInteraction(GCalendarService.GEvent item) {
    String target = item.getTitle();
    toast(target);
    speak(target);
  }

  @Override
  public void onFindEvents(List<GCalendarService.GEvent> events) {
    this.events = events;
    startSpeak();
  }

  @Override
  public void speak(String text) {
    Matcher mat = PAT.matcher(text);
    if (mat.find()
        && textToSpeech.isLanguageAvailable(Locale.ENGLISH) >= TextToSpeech.LANG_AVAILABLE) {
      textToSpeech.setLanguage(Locale.ENGLISH);
    } else {
      textToSpeech.setLanguage(Locale.JAPANESE);
    }
    String utteranceId = this.hashCode() + "";
    textToSpeech.speak(text, TextToSpeech.QUEUE_ADD, null, utteranceId);
  }

  @Override
  public void onDestroy() {
    textToSpeech.shutdown();
    super.onDestroy();
  }


  @Override
  public void onInit(int status) {
    toast(status == TextToSpeech.SUCCESS ? "initialized" : "initialized fail");
    ttsStatus = status;
    startSpeak();

  }

  @Background
  void startSpeak() {
    if (events == null
        || ttsStatus != TextToSpeech.SUCCESS) {
      return;
    }
    LinkedHashMap<String, List<GCalendarService.GEvent>> map = new LinkedHashMap<>();
    for (GCalendarService.GEvent event : events) {
      String calendarName = event.getCalendarDisplayName();
      if (!map.containsKey(calendarName)) {
        map.put(calendarName, new ArrayList<GCalendarService.GEvent>());
      }
      map.get(calendarName).add(event);
    }

    long start = System.currentTimeMillis();
    long end = start + (24 * 60 * 60 * 1000);

    for (String calendarName : map.keySet()) {
      speak("today schedule type is ");
      speak(calendarName);
      sleep(2000);
      List<GCalendarService.GEvent> list = map.get(calendarName);
      long startTime = 0;
      for (GCalendarService.GEvent event : list) {
        if (event.getBegin() < start
            || event.getBegin() > end) {
          continue;
        }
        if (startTime != event.getBegin()) {
          startTime = event.getBegin();
          sleep(2000);
          SimpleDateFormat sdf = new SimpleDateFormat("hh:mm");
          speak("it begins at " + sdf.format(new Date(event.getBegin())));
        }
        String text = event.getTitle();
//        if (!StringUtils.isEmpty(event.getEventLocation())) {
//          text += " at " + event.getEventLocation();
//        }
        speak(text);
      }
      sleep(5000);
      speak("next ");
    }

  }

}
