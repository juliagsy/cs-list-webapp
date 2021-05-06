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

  public void loadFile(String pathname) throws IOException
  {
    String label;
    DataFrame newFrame;
    int length = pathname.length();
    File file = new File(pathname);
    if (!file.exists())
    {
      throw new IOException();
    }
    else if (pathname.charAt(length-1) == 'v')
    {
      CSVLoader csv = new CSVLoader();
      newFrame = csv.load(file);
      label = pathname.substring(7,length-4);
    }
    else if (pathname.charAt(length-1) == 'n')
    {
      JSONLoader json = new JSONLoader();
    }
  }
}
