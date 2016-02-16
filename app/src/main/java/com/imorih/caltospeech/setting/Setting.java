package com.imorih.caltospeech.setting;

import com.google.gson.Gson;
import com.imorih.caltospeech.setting.pref.SettingPref_;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.sharedpreferences.Pref;
import org.apache.commons.lang3.StringUtils;

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
    String str = settingPref.calendars().getOr("");
    if (StringUtils.isEmpty(str)) {
      targetCalendars = new ArrayList<>();
      return;
    }
    targetCalendars = new Gson().fromJson(str, ArrayList.class);

  }

  public List<String> targetCalendars() {
    return targetCalendars;
  }

  public boolean isSpeakEnglishIfOnlyAlphabet() {
    return settingPref.speakEnglishIfOnlyAlphabet().getOr(true);
  }

  public void setSpeakEnglishIfOnlyAlphabet(boolean speakEnglish) {
    settingPref.speakEnglishIfOnlyAlphabet().put(speakEnglish);
  }

  public void setSpeakLastAllDayEvent(boolean speakLastAllDayEvent) {
    settingPref.speakLaterAllDayEvent().put(speakLastAllDayEvent);
  }

  public String targetEventNameRegex() {
    return settingPref.eventNameRegex().getOr("");
  }

  public void setTargetEventNameRegex(String regex) {
    settingPref.eventNameRegex().put(regex);
  }

  public void setTargetCalendars(List<String> targetCalendars) {
    String str = new Gson().toJson(targetCalendars);
    settingPref.calendars().put(str);
  }

  public boolean isTargetCalendar(String calendarId) {
    for (String current : targetCalendars) {
      if (current.equals(calendarId)) {
        return true;
      }
    }
    return false;
  }

  public boolean isSpeakLastAllDayEvent() {
    return settingPref.speakLaterAllDayEvent().getOr(true);
  }


}
