package com.scrollablelist.listeners;

import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;

/**
 * Created by igor on 6/21/17.
 */

public class ListViewScrollListener implements AbsListView.OnScrollListener {

  private ListView listView;
  private int mLastFirstVisibleItem;
  private boolean isScrollingUp = false;
  private View.OnTouchListener touchListener;

  // Set List Item Header TODO

  public ListViewScrollListener(ListView listView, View.OnTouchListener touchListener) {
    this.listView = listView;
    this.touchListener = touchListener;
    listView.setOnScrollListener(this);

    mLastFirstVisibleItem = -1;
  }

  @Override
  public void onScrollStateChanged(AbsListView view, int scrollState) {
    final int currentFirstVisibleItem = listView.getFirstVisiblePosition();
    if (currentFirstVisibleItem > mLastFirstVisibleItem) {
      isScrollingUp = false;
    } else if (currentFirstVisibleItem <= mLastFirstVisibleItem) {
      isScrollingUp = true;
    }
    mLastFirstVisibleItem = currentFirstVisibleItem;
  }

  @Override
  public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
    if (firstVisibleItem == 0 && isScrollingUp) {
      View v = listView.getChildAt(0);
      int offset = (v == null) ? 0 : v.getTop();
      if (offset == 0) {
        listView.setOnTouchListener(touchListener);
        isScrollingUp = false;
        return;
      }
    }
  }

}