package com.imorih.common;

import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import org.androidannotations.annotations.EActivity;

@EActivity
public abstract class BaseActivity extends AppCompatActivity {

  protected void showFragment(Fragment f) {
    showFragment(f, android.R.id.content);
  }

  protected void showFragment(Fragment f, int contentId) {
    getFragmentManager()
        .beginTransaction()
        .add(contentId, f, f.getClass().getName())
        .addToBackStack(null)
        .commit();
  }

}
