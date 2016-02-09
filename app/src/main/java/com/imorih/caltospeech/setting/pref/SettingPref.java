package com.imorih.caltospeech.setting.pref;

import org.androidannotations.annotations.sharedpreferences.SharedPref;

/**
 * Created by hiromi on 2/10/16.
 */
@SharedPref(value = SharedPref.Scope.UNIQUE)
public interface SettingPref {
  String calendars();

  String eventNameRegex();

  boolean speakEnglishIfOnlyAlphabet();

  boolean speakLaterAllDayEvent();

}
