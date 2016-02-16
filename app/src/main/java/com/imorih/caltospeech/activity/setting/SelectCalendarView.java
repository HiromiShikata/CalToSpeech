package com.imorih.caltospeech.activity.setting;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.imorih.caltospeech.R;
import com.imorih.caltospeech.service.GCalendarService;

import java.util.List;

/**
 * Created by hiromi on 2/16/16.
 */
public class SelectCalendarView extends LinearLayout {

  CheckBox mCheckView;
  TextView mColorText;

  public SelectCalendarView(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  @Override
  protected void onFinishInflate() {
    super.onFinishInflate();
    mCheckView = (CheckBox) findViewById(R.id.fragment_setting_calendar_item_checkbox);
    mColorText = (TextView) findViewById(R.id.fragment_setting_calendar_item_label);
  }

  public void bindView(GCalendarService.GCalendar gcal, List<String> selectedLists, CompoundButton.OnCheckedChangeListener checkedChangeListener) {
    mCheckView.setOnCheckedChangeListener(null);
    mCheckView.setChecked(selectedLists.contains(gcal.getName()));
    mCheckView.setOnCheckedChangeListener(checkedChangeListener);
    mCheckView.setText(gcal.getName());
    mColorText.setBackgroundColor(gcal.getColor());
  }
}
