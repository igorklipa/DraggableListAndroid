package com.scrollablelist;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Display;
import android.view.View;
import android.widget.RelativeLayout;

/**
 * Created by igor on 6/15/17.
 */

public class UtilHelper {

  public static float convertDpToPixel(float dp, Context context){
    Resources resources = context.getResources();
    DisplayMetrics metrics = resources.getDisplayMetrics();
    float px = dp * ((float)metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
    return px;
  }

  public static float convertPixelsToDp(float px, Context context){
    Resources resources = context.getResources();
    DisplayMetrics metrics = resources.getDisplayMetrics();
    float dp = px / ((float)metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
    return dp;
  }

  public static float getScreenHeight(Activity activity) {
    if(activity == null){
      return 0;
    }
    Display display = activity.getWindowManager().getDefaultDisplay();
    Point size = new Point();
    display.getSize(size);
    float height = size.y;
    return height;
  }

  public static int getActionlbarHeight(Context context) {
    TypedValue tv = new TypedValue();
    int actionBarHeight;
    if (context.getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true))
    {
      actionBarHeight = TypedValue.complexToDimensionPixelSize(tv.data,context.getResources().getDisplayMetrics());
    } else {
      actionBarHeight = 0;
    }

    return actionBarHeight;
  }

  public static int getStatusbarheight(Context context) {
    int statusBarHeight = 0;
    int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
    if (resourceId > 0) {
      statusBarHeight = context.getResources().getDimensionPixelSize(resourceId);
    } else {
      statusBarHeight = 0;
    }

    return statusBarHeight;
  }

  public static void expand(final View v, int lastTouchY) {
    ValueAnimator varl = ValueAnimator.ofInt(lastTouchY, 0);
    varl.setDuration(600);
    varl.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

      @Override
      public void onAnimationUpdate(ValueAnimator animation) {
        RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) v.getLayoutParams();
        lp.setMargins(0, (int) animation.getAnimatedValue(), 0, 0);
        v.setLayoutParams(lp);
      }
    });
    varl.start();
  }

  public static void collapse(final View v, int lastTouchY, int finishPosition) {
    ValueAnimator varl = ValueAnimator.ofInt(lastTouchY, finishPosition);
    varl.setDuration(400);
    varl.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

      @Override
      public void onAnimationUpdate(ValueAnimator animation) {
        RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) v.getLayoutParams();
        lp.setMargins(0, (int) animation.getAnimatedValue(), 0, 0);
        v.setLayoutParams(lp);
      }
    });
    varl.start();
  }
}
