package com.imorih.caltospeech.activity.main;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.imorih.caltospeech.R;
import com.imorih.caltospeech.service.GCalendarService;

import org.apache.commons.lang3.StringUtils;

import java.util.Date;
import java.util.List;


public class MyScheduleRecyclerViewAdapter extends RecyclerView.Adapter<MyScheduleRecyclerViewAdapter.ViewHolder> {

  private final List<GCalendarService.GEvent> mValues;
  private final ScheduleFragment.OnListFragmentInteractionListener mListener;

  public MyScheduleRecyclerViewAdapter(List<GCalendarService.GEvent> items, ScheduleFragment.OnListFragmentInteractionListener listener) {
    mValues = items;
    mListener = listener;
  }

  @Override
  public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.fragment_schedule, parent, false);
    return new ViewHolder(view);
  }

  @Override
  public void onBindViewHolder(final ViewHolder holder, int position) {
    GCalendarService.GEvent event = mValues.get(position);


    holder.mItem = event;
    String text = event.getTitle() + " " + new Date(event.getBegin());
    if (!StringUtils.isEmpty(event.getEventLocation())) {
      text += " @" + event.getEventLocation();
    }
    holder.mIdView.setText(text);
    holder.mContentView.setText(event.getEventLocation());

    holder.mView.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if (null != mListener) {
          mListener.onListFragmentInteraction(holder.mItem);
        }
      }
    });
  }

  @Override
  public int getItemCount() {
    return mValues.size();
  }

  public class ViewHolder extends RecyclerView.ViewHolder {
    public final View mView;
    public final TextView mIdView;
    public final TextView mContentView;
    public GCalendarService.GEvent mItem;

    public ViewHolder(View view) {
      super(view);
      mView = view;
      mIdView = (TextView) view.findViewById(R.id.id);
      mContentView = (TextView) view.findViewById(R.id.content);
    }

    @Override
    public String toString() {
      return super.toString() + " '" + mContentView.getText() + "'";
    }
  }
}
