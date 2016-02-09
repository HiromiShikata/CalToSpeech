package com.imorih.common;

import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import org.androidannotations.annotations.EActivity;

import java.util.ArrayList;

@EActivity
public abstract class BaseActivity extends AppCompatActivity {

  void showFragment(Fragment f) {
    getFragmentManager()
        .beginTransaction()
        .add(android.R.id.content, f, f.getClass().getName())
        .addToBackStack(null)
        .commit();
    ArrayList list = new ArrayList();
    list.add(1);
  }

}
