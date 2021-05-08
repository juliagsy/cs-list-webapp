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

  private void setColumnNames(String[] colNameList)
  {
    for (String name : colNameList)
    {
      dataframe.addColumn(name);
    }
  }

  private void addData(String[] colNameList, String[] patientDataList)
  {
    int index = 0;
    for (String patientData : patientDataList)
    {
      dataframe.addValue(colNameList[index],patientData);
      index ++;
    }
    if (index == colNum - 1) // zipcode (or last column) is not stated, add empty string in
    {
      dataframe.addValue(colNameList[index],"");
    }
  }

  DataFrame load(File filename) throws IOException
  {
    FileReader file = new FileReader(filename);
    Scanner incoming = new Scanner(file);
    String columnName = incoming.nextLine(); // first line of the file = column names
    String[] colNames = columnName.split(",");
    colNum = colNames.length;
    setColumnNames(colNames);
    while (incoming.hasNextLine()) // remaining are patient datas
    {
      String patient = incoming.nextLine();
      String[] patientList = patient.split(",");
      addData(colNames,patientList);
    }
    return dataframe;
  }
}

class CSVWriter
{
  private String newline = System.getProperty("line.separator"); // get newline on different systems
  private DataFrame dataframe;
  private FileWriter csvFile;
  private String filename;
  private int colNum;
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
    colNum = dataframe.getColCount();
    if (colNum == 0)
    {
      rowNum = 0;
      return;
    }
    rowNum = dataframe.getRowCount();
  }

  private void addColNames(ArrayList<String> allColNames) throws IOException
  {
    if (colNum != 0)
    {
      csvFile.write(allColNames.get(0));
      int index;
      for (index=1;index<colNum;index++)
      {
        csvFile.write(","+allColNames.get(index));
      }
    }
    csvFile.write(newline);
  }

  public void write(DataFrame dataframe) throws IOException
  {
    openFile();
    initDataFrame(dataframe);
    addColNames(dataframe.getColumnNames());
    csvFile.close();
  }
}
