package cw3.pckg.model;

import java.util.Scanner;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

class CSVLoader
{
  private DataFrame dataframe;
  int colNum;

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
