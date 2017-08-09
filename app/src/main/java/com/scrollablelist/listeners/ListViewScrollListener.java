package com.scrollablelist.listeners;

import android.support.v7.widget.RecyclerView;

/**
 * Created by igor on 6/21/17.
 */

public class ListViewScrollListener extends RecyclerView.OnScrollListener {

  private RecyclerView listView;
  private boolean isScrollingUp = false;
  private ListViewOnTouchListener touchListener;

  public ListViewScrollListener(RecyclerView listView, ListViewOnTouchListener touchListener) {
    this.listView = listView;
    listView.addOnScrollListener(this);
    this.touchListener = touchListener;
  }

  @Override
  public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
    super.onScrolled(recyclerView, dx, dy);
    if (dy > 0) {
      isScrollingUp = false;
      listView.setOnTouchListener(null);
    } else if (dy < 0) {
      isScrollingUp = true;
    }
  }

  @Override
  public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
    super.onScrollStateChanged(recyclerView, newState);
    if (recyclerView.getLayoutManager().getChildAt(0).getTop() == 0 && isScrollingUp) {
      listView.setOnTouchListener(touchListener.extendedHelperListener);
      isScrollingUp = false;
      return;
    }
  }
}