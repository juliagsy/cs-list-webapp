package cw3.pckg.model;

import java.util.ArrayList;

import java.io.File;
import java.io.IOException;

public class Model
{
  private ArrayList<String> allLabels;
  private ArrayList<DataFrame> allDataFrames;
  private int listCount;

  public Model()
  {
    allLabels = new ArrayList<String>();
    allDataFrames = new ArrayList<DataFrame>();
    listCount = 0;
  }

  private void deleteFile(String name) throws IOException
  {
    File file = new File("./data/"+name+".csv");
    file.delete();
  }

  private void renameFile(String oldname, String newname) throws IOException
  {
    File oldpath = new File("./data/"+oldname+".csv");
    File newpath = new File("./data/"+newname+".csv");
    oldpath.renameTo(newpath);
  }

  private void addCol(String listname, String direction, String colIndex) throws IOException
  {
    DataFrame targetFrame = allDataFrames.get(allLabels.indexOf(listname));
    if (direction.compareTo("left") == 0)
    {
      targetFrame.insertCol(Integer.valueOf(colIndex));
    }
    else
    {
      targetFrame.insertCol(Integer.valueOf(colIndex)+1);
    }
    autosave(listname,targetFrame);
  }

  private void addRow(String listname, String direction, String rowIndex) throws IOException
  {
    DataFrame targetFrame = allDataFrames.get(allLabels.indexOf(listname));
    if (direction.compareTo("up") == 0)
    {
      targetFrame.insertRow(Integer.valueOf(rowIndex));
    }
    else
    {
      targetFrame.insertRow(Integer.valueOf(rowIndex)+1);
    }
    autosave(listname,targetFrame);
  }

  private void removeCol(String listname, String colIndex) throws IOException
  {
    DataFrame targetFrame = allDataFrames.get(allLabels.indexOf(listname));
    targetFrame.removeCol(Integer.valueOf(colIndex));
    autosave(listname,targetFrame);
  }

  private void removeRow(String listname, String rowIndex) throws IOException
  {
    DataFrame targetFrame = allDataFrames.get(allLabels.indexOf(listname));
    targetFrame.removeRow(Integer.valueOf(rowIndex));
    autosave(listname,targetFrame);
  }

  private void editGrid(String listname, String rowIndex, String colIndex, String newItem) throws IOException
  {
    DataFrame targetFrame = allDataFrames.get(allLabels.indexOf(listname));
    targetFrame.setGrid(Integer.valueOf(rowIndex), Integer.valueOf(colIndex),newItem);
    autosave(listname,targetFrame);
  }

  private void autosave(String filename, DataFrame saveFrame) throws IOException
  {
    CSVWriter csv = new CSVWriter(filename);
    csv.write(saveFrame);
  }

  public ArrayList<String> getLabels()
  {
    return allLabels;
  }

  public int getColCount(String listname)
  {
    DataFrame frame = allDataFrames.get(allLabels.indexOf(listname));
    return frame.getColCount();
  }

  public void loadDataDirectory(File dataFolder) throws IOException
  {
    for (File dataFile : dataFolder.listFiles())
    {
      if (dataFile.isDirectory())
      {
        loadDataDirectory(dataFile);
      }
      else
      {
        loadFile(dataFile);
      }
    }
  }

  public void loadFile(File dataFile) throws IOException
  {
    String filename = dataFile.getName();
    int length = filename.length();

    if (filename.substring(length-3,length).compareTo("csv") == 0)
    {
      CSVLoader csv = new CSVLoader();
      DataFrame newFrame = csv.load(dataFile);
      newFrame.checkEmpty();
      String label = filename.substring(0,length-4);
      allLabels.add(label);
      allDataFrames.add(newFrame);
      listCount ++;
    }
  }

  public void createNewList(String listname) throws IOException
  {
    DataFrame newFrame = new DataFrame();
    newFrame.checkEmpty();
    allLabels.add(listname);
    allDataFrames.add(newFrame);
    listCount ++;
    autosave(listname,newFrame);
  }

  public void deleteList(String listname) throws IOException
  {
    int listIndex = allLabels.indexOf(listname);
    allLabels.remove(listIndex);
    allDataFrames.remove(listIndex);
    listCount --;
    deleteFile(listname);
  }

  public void renameList(String listname, String newname) throws IOException
  {
    int listIndex = allLabels.indexOf(listname);
    allLabels.add(listIndex,newname);
    allLabels.remove(listIndex+1);
    renameFile(listname,newname);
  }

  public ArrayList<ArrayList<String>> viewList(String listname)
  {
    int listIndex = allLabels.indexOf(listname);
    DataFrame frame = allDataFrames.get(listIndex);
    ArrayList<ArrayList<String>> allDataByRow = new ArrayList<ArrayList<String>>();
    int rowCount = frame.getRowCount();
    for (int index=0;index<rowCount;index++)
    {
      ArrayList<String> rowData = frame.getRow(index);
      allDataByRow.add(rowData);
    }
    return allDataByRow;
  }

  public void add(String target, String type, String direction) throws IOException
  {
    String[] targetArray = target.split("@"); // format = [name, rowIndex, colIndex]
    String listname = targetArray[0];

    if (type.compareTo("col") == 0)
    {
      addCol(listname,direction,targetArray[2]);
    }
    else
    {
      addRow(listname,direction,targetArray[1]);
    }
  }

  public void remove(String target, String type) throws IOException
  {
    String[] targetArray = target.split("@"); // format = [name, rowIndex, colIndex]
    String listname = targetArray[0];

    if (type.compareTo("col") == 0)
    {
      removeCol(listname,targetArray[2]);
    }
    else
    {
      removeRow(listname,targetArray[1]);
    }
  }

  public void edit(String target, String newItem) throws IOException
  {
    String[] targetArray = target.split("@"); // format = [listname, rowIndex, colIndex]
    editGrid(targetArray[0], targetArray[1], targetArray[2], newItem);
  }

  public int searchList(String listname)
  {
    return allLabels.indexOf(listname);
  }

  public ArrayList<Object> searchItem(String itemname)
  {
    ArrayList<Object> collections = new ArrayList<Object>();
    ArrayList<String> labels = new ArrayList<String>();
    ArrayList<ArrayList<ArrayList<String>>> results = new ArrayList<ArrayList<ArrayList<String>>>();

    for (int index=0;index<listCount;index++)
    {
      DataFrame dataframe = allDataFrames.get(index);
      ArrayList<ArrayList<String>> rowResult = dataframe.search(itemname);
      if (rowResult.size() == 0)
      {
        continue;
      }
      labels.add(allLabels.get(index));
      results.add(rowResult);
    }

    collections.add(labels);
    collections.add(results);
    return collections;
  }
}
