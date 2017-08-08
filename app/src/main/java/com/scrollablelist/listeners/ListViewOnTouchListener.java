package com.scrollablelist.listeners;

import android.app.Activity;
import android.content.Context;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.scrollablelist.UtilHelper;

/**
 * Created by igor on 6/21/17.
 */

public class ListViewOnTouchListener implements View.OnTouchListener {

  public static final float MARGIN_HEIGHT_VALUE = 200; //marginTop value which will be used in expanding and collapsing the view
  private static float heightList;
  private static float heightDiff;
  private boolean isExpanded = false;
  private float mLastTouchY;
  private float mPosY;
  private int actionBarHeight;

  private RecyclerView listView;
  private RelativeLayout rootLayout;

  private ListViewOnTouchListener thisListener;
  public View.OnTouchListener extendedHelperListener;

  public ListViewOnTouchListener(RecyclerView listView, RelativeLayout rootLayout, Context context, Activity activity) {
    this.listView = listView;
    this.rootLayout = rootLayout;
    isExpanded = false;
    thisListener = this;
    listView.setOnTouchListener(thisListener);

    actionBarHeight = UtilHelper.getActionlbarHeight(context);
    heightList = UtilHelper.convertDpToPixel(MARGIN_HEIGHT_VALUE, context);
    // heightDiff [px] - Height of remaining part of screen without list height and actionbar height [px]
    heightDiff = (UtilHelper.getScreenHeight(activity) - heightList) - this.actionBarHeight;
    initHelperListener();
  }

  @Override
  public boolean onTouch(View v, MotionEvent ev) {
    final int action = MotionEventCompat.getActionMasked(ev);
    float y = ev.getRawY();

    switch (action) {
      case MotionEvent.ACTION_DOWN: {
        mLastTouchY = y;
        break;
      }

      case MotionEvent.ACTION_MOVE: {

        mPosY = (y - mLastTouchY);

        if (isExpanded && mPosY < heightDiff) {
          RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
              ViewGroup.LayoutParams.MATCH_PARENT,
              ViewGroup.LayoutParams.MATCH_PARENT
          );
          params.setMargins(0, (int) mPosY, 0, 0); //left,top,right,bottom
          listView.setLayoutParams(params);
        } else if (!isExpanded && y < heightDiff) {
          RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
              ViewGroup.LayoutParams.MATCH_PARENT,
              ViewGroup.LayoutParams.MATCH_PARENT
          );
          params.setMargins(0, (int) (heightDiff + mPosY), 0, 0); //left,top,right,bottom
          listView.setLayoutParams(params);
        }
        break;
      }

      case MotionEvent.ACTION_UP: {
        if (isExpanded && mPosY > 100) {
          UtilHelper.collapse(listView, (int) mPosY, (int) heightList);
          this.isExpanded = false;
        } else if (!isExpanded && mPosY < -100) {
          UtilHelper.expand(listView, (int) (heightDiff + mPosY));
          this.isExpanded = true;
          listView.setOnTouchListener(null);
          switchToHelperListener();
        } else {
          if (mLastTouchY > heightDiff) {
            UtilHelper.collapse(listView, (int) (heightList), (int) heightList);
            isExpanded = false;
          } else {
            if (isExpanded) {
              UtilHelper.expand(listView, (int) mPosY);
              listView.setOnTouchListener(null);
              switchToHelperListener();
            } else {
              UtilHelper.collapse(listView, (int) (heightDiff + mPosY), (int) heightList);
              this.isExpanded = false;
            }
          }
        }
        mLastTouchY = y;
        break;
      }

      case MotionEvent.ACTION_POINTER_UP: {
        mLastTouchY = y;
        break;
      }
    }
    rootLayout.invalidate();
    return true;
  }

  public void switchToHelperListener() {
    listView.setOnTouchListener(extendedHelperListener);
  }

  // This touch listener will be used when the list is expanded
  public void initHelperListener() {
    extendedHelperListener = new View.OnTouchListener() {
      float lastTouchY;
      float posY;

      @Override
      public boolean onTouch(View v, MotionEvent ev) {
        final int action = MotionEventCompat.getActionMasked(ev);
        float y = ev.getRawY();

        switch (action) {
          case MotionEvent.ACTION_DOWN: {
            lastTouchY = y;
            break;
          }

          case MotionEvent.ACTION_MOVE: {

            posY = (y - lastTouchY);

            if (posY < 0) {
              listView.setOnTouchListener(null);
              return false;
            } else {
              RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                  ViewGroup.LayoutParams.MATCH_PARENT,
                  ViewGroup.LayoutParams.MATCH_PARENT
              );
              params.setMargins(0, (int) posY, 0, 0); //left,top,right,bottom
              listView.setLayoutParams(params);
            }
            break;
          }

          case MotionEvent.ACTION_UP: {
            if (posY > 0) {
              UtilHelper.collapse(listView, (int) posY, (int) heightList);
              isExpanded = false;
              listView.setOnTouchListener(thisListener);
            } else {
              UtilHelper.expand(listView, (int) posY);
              isExpanded = true;
              listView.setOnTouchListener(null);
            }
            break;
          }
        }
        rootLayout.invalidate();
        return true;
      }
    };
  }

}
