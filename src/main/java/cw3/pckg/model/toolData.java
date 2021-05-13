package cw3.pckg.model;

import java.util.ArrayList;

class DataFrame
{
  private ArrayList<Row> rows;
  private int colCount;

  DataFrame()
  {
    rows = new ArrayList<Row>();
    colCount = 0;
  }

  public void checkEmpty()
  {
    if (colCount == 0 && getRowCount() == 0)
    {
      Row row = new Row();
      row.addColValue(".");
      rows.add(row);
      colCount ++;
    }
  }

  public void addRow(Row newRow)
  {
    rows.add(newRow);
  }

  public void setColCount(int newCount)
  {
    colCount = newCount;
  }

  public int getColCount()
  {
    return colCount;
  }

  public int getRowCount()
  {
    return rows.size();
  }

  public ArrayList<String> getRow(int index)
  {
    Row targetRow = rows.get(index);
    ArrayList<String> rowData = targetRow.getCol();
    return rowData;
  }

  public void removeRow(int index)
  {
    rows.remove(index);
    checkEmpty();
  }

  public void removeCol(int index)
  {
    for (Row row : rows)
    {
      if (index < row.getSize())
      {
        row.deleteCol(index);
      }
    }
    colCount --;
    checkEmpty();
  }

  public void insertRow(int index)
  {
    Row row = new Row();
    for (int count=0;count<colCount;count++)
    {
      row.addColValue(" ");
    }
    rows.add(index,row);
  }

  public void insertCol(int index)
  {
    for (Row row : rows)
    {
      if (index <= row.getSize())
      {
        row.insertColValue(index," ");
        continue;
      }
      row.insertColValue(index-1," ");
      row.insertColValue(index," ");
    }
    colCount ++;
  }

  public void setGrid(int rowIndex, int colIndex, String newItem)
  {
    Row targetRow = rows.get(rowIndex);
    targetRow.setColValue(colIndex, newItem);
  }

  public ArrayList<ArrayList<String>> search(String target)
  {
    ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
    for (int index=0;index<rows.size();index++)
    {
      boolean containItem = rows.get(index).search(target);
      if (containItem)
      {
        result.add(getRow(index));
      }
    }
    return result;
  }

}
