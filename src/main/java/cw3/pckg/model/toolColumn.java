package cw3.pckg.model;

import java.util.ArrayList;

class Column
{
  private String colName;
  private ArrayList<Object> rows;

  protected Column()
  {
    rows = new ArrayList<Object>();
    colName = "";
  }

  protected Column(String name)
  {
    rows = new ArrayList<Object>();
    colName = name;
  }

  protected String getName()
  {
    return colName;
  }

  protected int getSize()
  {
    return rows.size();
  }

  protected Object getRowValue(int index)
  {
    return rows.get(index);
  }

  protected void setRowValue(int index, Object data)
  {
    rows.set(index,data);
  }

  protected void addRowValue(Object data)
  {
    rows.add(data);
  }
}
