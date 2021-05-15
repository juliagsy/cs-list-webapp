package cw3.pckg.model;

import java.io.File;
import java.io.IOException;

class FileController
{
  void deleteFile(String name) throws IOException
  {
    File file = new File("./data/"+name+".csv");
    file.delete();
  }

  void renameFile(String oldname, String newname) throws IOException
  {
    File oldpath = new File("./data/"+oldname+".csv");
    File newpath = new File("./data/"+newname+".csv");
    oldpath.renameTo(newpath);
  }

  void autosave(String filename, DataFrame saveFrame) throws IOException
  {
    CSVWriter csv = new CSVWriter(filename);
    csv.write(saveFrame);
  }

}
