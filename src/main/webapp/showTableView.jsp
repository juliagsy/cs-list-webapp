<%@ page import="java.util.ArrayList" %>
<%@ page import="cw3.pckg.model.DataFrame" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <title>Viewing Data in Table</title>
  </head>
  <body>
    <%
      String name = (String) request.getAttribute("viewlist");
    %>
    <h1>"<%=name%>" Table</h1>

    <table>
      <thead>
        <tr>
          <%
            DataFrame frame = (DataFrame) request.getAttribute("listFrame");
            ArrayList<String> allColName = frame.getColumnNames();
            int rowCount = frame.getRowCount();

            for (String colName : allColName)
            {
          %>
          <th scope="col"><%=colName%></th>
          <%
            }
          %>
        </tr>
      </thead>
      <tbody>
        <%
          for (int count=0;count<rowCount;count++)
          {
        %>
        <tr>
          <%
            for (String colName : allColName)
            {
              String elem = frame.getValue(colName,count);
          %>
          <td><%=elem%></td>
          <%
            }
          %>
        </tr>
        <%
          }
        %>
      </tbody>
    </table>
  </body>
</html>
