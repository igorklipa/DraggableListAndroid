package com.scrollablelist;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

  private List<Item> items;
  private Context context;

  public ListAdapter(List<Item> items, Context context) {
    this.items = items;
    this.context = context;
  }

  @Override
  public int getItemCount() {
    return this.items.size();
  }

  @Override
  public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View itemView = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.single_item, parent, false);

    return new ViewHolder(itemView);
  }

  @Override
  public void onBindViewHolder(ViewHolder holder, int position) {
    Item item = items.get(position);

    if(item != null) {
      holder.name.setText(item.getName());
      holder.name.setOnClickListener(itemClickListener);
      holder.name.setTag(position);
    }
  }

  View.OnClickListener itemClickListener = new View.OnClickListener() {
    @Override
    public void onClick(View v) {
      int rowPosition = (int) v.getTag();
      Toast.makeText(context, items.get(rowPosition).getDesc(), Toast.LENGTH_SHORT).show();
    }
  };

  public class ViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.name)
    public TextView name;

    public ViewHolder(View view) {
      super(view);
      ButterKnife.bind(this, view);
    }
  }

}
