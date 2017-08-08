package com.scrollablelist;


public class Item {

  private String name;
  private String desc;

  public Item(String name, String desc) {
    this.name = name;
    this.desc = desc;
  }

  public String getDesc() {
    return desc;
  }

  public void setDesc(String desc) {
    this.desc = desc;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}