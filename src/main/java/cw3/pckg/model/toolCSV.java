package cw3.pckg.model;

import java.util.Scanner;
import java.util.ArrayList;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

class CSVLoader
{
  private DataFrame dataframe;
  private int colNum;

  CSVLoader()
  {
    dataframe = new DataFrame();
    colNum = 0;
  }

  private void addData(String[] dataList)
  {
    int index = 0;
    Row row = new Row();
    for (String data : dataList)
    {
      row.addColValue(data);
      index ++;
    }
    if (index > colNum) // new longest row
    {
      colNum = index;
    }
    dataframe.addRow(row);
  }

  DataFrame load(File filename) throws IOException
  {
    FileReader file = new FileReader(filename);
    Scanner incoming = new Scanner(file);
    while (incoming.hasNextLine())
    {
      String data = incoming.nextLine();
      String[] dataList = data.split(",");
      addData(dataList);
    }
    dataframe.setColCount(colNum);
    return dataframe;
  }
}

class CSVWriter
{
  private String newline = System.getProperty("line.separator"); // get newline on different systems
  private DataFrame dataframe;
  private FileWriter csvFile;
  private String filename;
  private int rowNum;

  CSVWriter(String filename)
  {
    this.filename = filename;
  }

  private void openFile() throws IOException
  {
    csvFile = new FileWriter("./data/"+filename+".csv", false);
  }

  private void initDataFrame(DataFrame dataframe)
  {
    this.dataframe = dataframe;
    rowNum = dataframe.getRowCount();
  }

  private void addData() throws IOException
  {
    for (int index=0;index<rowNum;index++)
    {
      ArrayList<String> rowData = dataframe.getRow(index);
      String line = String.join(",",rowData);
      csvFile.write(line);
      csvFile.write(newline);
    }
  }

  public void write(DataFrame dataframe) throws IOException
  {
    openFile();
    initDataFrame(dataframe);
    addData();
    csvFile.close();
  }
}
