package com.scrollablelist;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.RelativeLayout;

import com.scrollablelist.listeners.ListViewOnTouchListener;
import com.scrollablelist.listeners.ListViewScrollListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

  @BindView(R.id.listView)
  RecyclerView listView;
  @BindView(R.id.activity_main)
  RelativeLayout rootLayout;
  ArrayList<Item> list = new ArrayList<>();
  ListAdapter listAdapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);
    listAdapter = new ListAdapter(this.list, getApplicationContext());
    initList();
  }

  public void initList() {
    LinearLayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
    DividerItemDecoration mDividerItemDecoration = new DividerItemDecoration(listView.getContext(),
        mLayoutManager.getOrientation());
    listView.setLayoutManager(mLayoutManager);
    listView.addItemDecoration(mDividerItemDecoration);
    listView.setAdapter(listAdapter);

    populateList();

    ListViewOnTouchListener touchListener = new ListViewOnTouchListener(listView, rootLayout, getApplicationContext(), this);
    ListViewScrollListener scrollListener = new ListViewScrollListener(listView, touchListener);
  }

  public void populateList() {
    // Just example list
    for (int i = 0; i < 15; i++) {
      list.add(new Item(i + ".", "Desc for " + i));
    }
    listAdapter.notifyDataSetChanged();
  }
}