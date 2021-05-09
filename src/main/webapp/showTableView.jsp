<%@ page import="java.util.ArrayList" %>
<%@ page import="cw3.pckg.model.Model" %>
<%@ page import="cw3.pckg.model.ModelFactory" %>
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

      Model model = ModelFactory.getModel();
      ArrayList<ArrayList<String>> allDataByRow = model.viewList(name);
    %>
    <h1>"<%=name%>" Table</h1>

    <table>
      <thead>
        <tr>
          <%
            ArrayList<String> allColName = allDataByRow.get(0);

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
          for (int count=1;count<allDataByRow.size();count++)
          {
        %>
        <tr>
          <%
            for (String colName : allDataByRow.get(count))
            {
          %>
          <td><%=colName%></td>
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
