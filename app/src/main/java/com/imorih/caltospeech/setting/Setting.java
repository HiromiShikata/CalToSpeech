package com.imorih.caltospeech.setting;

import com.imorih.caltospeech.setting.pref.SettingPref_;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.sharedpreferences.Pref;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hiromi on 2/10/16.
 */
@EBean(scope = EBean.Scope.Singleton)
public class Setting {
  @Pref
  SettingPref_ settingPref;

  List<String> targetCalendars;

  @AfterInject
  void afterInject() {
    targetCalendars = new ArrayList<>();

  }

  public List<String> targetCalendars() {
    return targetCalendars;
  }

  public boolean isSpeakEnglishIfOnlyAlphabet() {
    return settingPref.speakEnglishIfOnlyAlphabet().getOr(true);
  }

  public String targetEventNameRegex() {
    return settingPref.eventNameRegex().getOr("");
  }

  public boolean isTargetCalendar(String calendarName) {
    for (String current : targetCalendars) {
      if (current.equals(calendarName)) {
        return true;
      }
    }
    return false;
  }

  public boolean isSpeakLastAllDayEvent() {
    return settingPref.speakLaterAllDayEvent().getOr(true);
  }


}
