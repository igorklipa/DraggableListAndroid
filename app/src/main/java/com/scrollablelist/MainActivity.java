package com.scrollablelist;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.scrollablelist.listeners.ListViewOnTouchListener;
import com.scrollablelist.listeners.ListViewScrollListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {


  @BindView(R.id.listView)
  ListView listView;

  RelativeLayout rootLayout;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);
    rootLayout = (RelativeLayout) findViewById(R.id.activity_main);
    populateList();
  }

  public void populateList() {
    ArrayList<Item> list = new ArrayList<Item>();
    list.add(new Item("1"));
    list.add(new Item("2"));
    list.add(new Item("3"));
    list.add(new Item("4"));
    list.add(new Item("5"));
    list.add(new Item("6"));
    list.add(new Item("7"));
    list.add(new Item("8"));
    list.add(new Item("9"));
    list.add(new Item("10"));
    list.add(new Item("11"));
    list.add(new Item("12"));

    listView.setAdapter(new com.scrollablelist.ListAdapter(MainActivity.this, R.layout.single_item, list));

    ListViewOnTouchListener touchListener = new ListViewOnTouchListener(listView, rootLayout, getApplicationContext(), this);
    ListViewScrollListener scrollListener = new ListViewScrollListener(listView, touchListener);
  }
}
