package cw3.pckg.model;

import java.util.ArrayList;

class Row
{
  private ArrayList<String> columns;

  protected Row()
  {
    columns = new ArrayList<String>();
  }

  protected int getSize()
  {
    return columns.size();
  }

  protected ArrayList<String> getCol()
  {
    return columns;
  }

  protected String getColValue(int index)
  {
    return columns.get(index);
  }

  protected void setColValue(int index, String data)
  {
    columns.set(index,data);
  }

  protected void addColValue(String data)
  {
    columns.add(data);
  }

  protected void insertColValue(int index, String data)
  {
    columns.add(index,data);
  }

  protected void deleteCol(int index)
  {
    columns.remove(index);
  }

  protected boolean search(String item)
  {
    boolean result = false;
    for (String value : columns)
    {
      if (value.compareTo(item) == 0)
      {
        result = true;
        break;
      }
    }
    return result;
  }
}
