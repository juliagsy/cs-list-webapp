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
      int colCount = model.getColCount(name);
    %>
    <h1>"<%=name%>" Table</h1>

    <table>
      <tbody>
        <%
          for (ArrayList<String> row : allDataByRow)
          {
        %>
        <tr>
          <%
            int index = 0;
            for (String rowData : row)
            {
          %>
          <td><%=rowData%></td>
          <%
              index ++;
            }
            if (index < colCount)
            {
          %>
          <td>.</td>
          <%
            }
          %>
        </tr>
        <%
          }
        %>
      </tbody>
    </table>
    <jsp:include page="/footer.jsp"/>
  </body>
</html>
