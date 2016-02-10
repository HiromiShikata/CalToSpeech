package com.imorih.caltospeech.activity.main;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.imorih.caltospeech.R;
import com.imorih.caltospeech.activity.BaseAppActivity;
import com.imorih.caltospeech.service.GCalendarService;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_main)
@OptionsMenu(R.menu.menu_main)
public class MainActivity extends BaseAppActivity
    implements ScheduleFragment.OnListFragmentInteractionListener {

  @ViewById(R.id.toolbar)
  Toolbar toolbar;

  @ViewById(R.id.fab)
  FloatingActionButton fab;

  @AfterViews
  void afterViews() {
    setSupportActionBar(toolbar);

    ScheduleFragment f = ScheduleFragment.newInstance();
    showFragment(f, R.id.activity_main_schedule_list);
  }

  @Click(R.id.fab)
  void onClickFab(View view) {
    Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
        .setAction("Action", null).show();
  }


  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    int id = item.getItemId();

    if (id == R.id.action_settings) {

      return true;
    }

    return super.onOptionsItemSelected(item);
  }

  @Override
  public void onListFragmentInteraction(GCalendarService.GEvent item) {
    toast(item.getTitle());
  }
}
