package com.scrollablelist;


import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListAdapter extends ArrayAdapter<Item> {

  private Context context;
  private int layoutResourceId;
  private ArrayList<Item> items;

  public ListAdapter(Context context, int layoutResourceId, ArrayList<Item> items) {

    super(context, layoutResourceId, items);

    this.layoutResourceId = layoutResourceId;
    this.context = context;
    this.items = items;
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {

    ViewHolder viewHolder;

    if(convertView==null){
      LayoutInflater inflater = ((Activity) context).getLayoutInflater();
      convertView = inflater.inflate(layoutResourceId, parent, false);

      viewHolder = new ViewHolder(convertView);

      convertView.setTag(viewHolder);
    } else {
      viewHolder = (ViewHolder) convertView.getTag();
    }

    Item objectItem = items.get(position);

    viewHolder.name.setText(objectItem.getName());

    return convertView;
  }

  static class ViewHolder {
    @BindView(R.id.name)
    TextView name;

    ViewHolder(View view) {
      ButterKnife.bind(this, view);
    }
  }
}
