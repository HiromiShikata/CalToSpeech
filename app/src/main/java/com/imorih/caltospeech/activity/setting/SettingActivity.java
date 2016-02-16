package com.imorih.caltospeech.activity.setting;

import android.content.Context;

import com.imorih.caltospeech.R;
import com.imorih.caltospeech.activity.BaseAppActivity;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;

@EActivity(R.layout.activity_setting)
public class SettingActivity extends BaseAppActivity
    implements SettingFragment.OnFragmentInteractionListener {

  public static void startActivity(Context context) {
    SettingActivity_
        .intent(context)
        .start();
  }

  @AfterViews
  void afterViews() {
    SettingFragment f = SettingFragment.newInstance();
    showFragment(f);

  }

}
