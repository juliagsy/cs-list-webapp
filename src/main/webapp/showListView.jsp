<%@ page import="java.util.ArrayList" %>
<%@ page import="cw3.pckg.model.DataFrame" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <title>Viewing Data in List</title>
  </head>
  <body>
    <%
      String name = (String) request.getAttribute("viewlist");
    %>
    <h1>"<%=name%>" List</h1>
    <p>
      <b>Sequence of each row: </b>
    <%
      DataFrame frame = (DataFrame) request.getAttribute("listFrame");
      ArrayList<String> allColName = frame.getColumnNames();
      int rowCount = frame.getRowCount();

      String firstCol = allColName.get(0);
    %>
      <%=firstCol%>
    <%
      for (int index=1;index<allColName.size();index++)
      {
        String colName = allColName.get(index);
    %>
      , <%=colName%>
    <%
      }
    %>
    </p>

    <ol>
      <%
        for (int count=0;count<rowCount;count++)
        {
      %>
      <li>
      <%
          String firstElem = frame.getValue(allColName.get(0),count);
      %>
        <%=firstElem%>
      <%
          for (int index=1;index<allColName.size();index++)
          {
            String elem = frame.getValue(allColName.get(index),count);
      %>
        , <%=elem%>
      <%
          }
      %>
      </li>
      <%
        }
      %>
    </ol>
  </body>
</html>
