package com.imorih.caltospeech.activity.setting;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;

import com.imorih.caltospeech.R;
import com.imorih.caltospeech.service.GCalendarService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hiromi on 2/15/16.
 */
public class GCalendarAdapter
    extends ArrayAdapter<GCalendarService.GCalendar> {

  List<String> calendarIds = new ArrayList<>();
  LayoutInflater mInflator;

  public GCalendarAdapter(Context context) {
    super(context, R.layout.fragment_setting_calendar_item);
    mInflator = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
  }

  public void setSelecttedCalndarIds(List<String> selectedCalendarIds) {
    calendarIds = selectedCalendarIds;
  }

  public List<String> getSelectedalenarIds() {
    return calendarIds;
  }


  public View getView(int position, View convertView, ViewGroup parent) {
    SelectCalendarView view;
    if (convertView == null) {
      view = (SelectCalendarView) mInflator.inflate(R.layout.fragment_setting_calendar_item, null);
    } else {
      view = (SelectCalendarView) convertView;
    }
    final GCalendarService.GCalendar gcal = getItem(position);
    view.bindView(getItem(position), calendarIds, new CompoundButton.OnCheckedChangeListener() {
      @Override
      public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
          calendarIds.add(gcal.getName());

        } else {
          calendarIds.remove(gcal.getName());
        }
      }
    });

    return view;

  }

}
