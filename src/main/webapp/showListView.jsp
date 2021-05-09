<%@ page import="java.util.ArrayList" %>
<%@ page import="cw3.pckg.model.Model" %>
<%@ page import="cw3.pckg.model.ModelFactory" %>
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

      Model model = ModelFactory.getModel();
      ArrayList<ArrayList<String>> allDataByRow = model.viewList(name);
    %>
    <h1>"<%=name%>" List</h1>
    <p>
      <b>Sequence of each row: </b>
    <%
      ArrayList<String> allColName = allDataByRow.get(0);

      String firstCol = allColName.get(0);
    %>
      <%=firstCol%>
    <%
      for (String colName : allColName.subList(1,allColName.size()))
      {
    %>
      , <%=colName%>
    <%
      }
    %>
    </p>

    <ol>
      <%
        for (ArrayList<String> rowData : allDataByRow.subList(1,allDataByRow.size()))
        {
      %>
      <li>
      <%
          String firstRow = rowData.get(0);
      %>
        <%=firstRow%>
      <%
          for (String row : rowData.subList(1,rowData.size()))
          {
      %>
        , <%=row%>
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
