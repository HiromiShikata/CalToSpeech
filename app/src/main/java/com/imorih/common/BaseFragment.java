package com.imorih.common;

import android.app.Fragment;
import android.util.Log;
import android.widget.Toast;

import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.UiThread;

@EFragment
public abstract class BaseFragment extends Fragment {
  @UiThread
  protected void toast(String text) {
    if (getActivity() == null) {
      return;
    }
    Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT).show();
    Log.d("test", text);
  }

  protected void sleep(long ms) {
    try {
      Thread.sleep(ms);
    } catch (InterruptedException e) {
    }

  }

}
