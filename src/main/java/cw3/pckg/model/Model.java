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

  public ArrayList<String> getLabels()
  {
    return allLabels;
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
      String label = filename.substring(0,length-4);
      allLabels.add(label);
      allDataFrames.add(newFrame);
      listCount ++;
    }
  }

  public void writeFile(String filename, DataFrame newFrame) throws IOException
  {
    CSVWriter csv = new CSVWriter(filename);
    csv.write(newFrame);
  }

  public void createNewList(String listname) throws IOException
  {
    DataFrame newFrame = new DataFrame();
    allLabels.add(listname);
    allDataFrames.add(newFrame);
    listCount ++;
    writeFile(listname,newFrame);
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
    ArrayList<ArrayList<String>> allData = new ArrayList<ArrayList<String>>();
    allData.add(frame.getColumnNames());
    int rowCount = frame.getRowCount();
    for (int count=0;count<rowCount;count++)
    {
      allData.add(frame.getRow(count));
    }
    return allData;
  }
}
