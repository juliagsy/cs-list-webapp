<%@ page import="java.util.ArrayList" %>
<%@ page import="cw3.pckg.model.Model" %>
<%@ page import="cw3.pckg.model.ModelFactory" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
  <head>
    <meta charset="utf-8">
    <title>Search Result</title>
  </head>
  <body>
    <%
      Model model = ModelFactory.getModel();
      ArrayList<Object> result = (ArrayList<Object>) request.getAttribute("result");
      ArrayList<String> labels = (ArrayList<String>) result.get(0);
      ArrayList<ArrayList<ArrayList<String>>> rowData = (ArrayList<ArrayList<ArrayList<String>>>) result.get(1);

      for (String name : labels)
      {
    %>
    <h1>"<%=name%>" Search Result</h1>
    <%
      for (ArrayList<ArrayList<String>> row : rowData)
      {
    %>
    <table>
      <tbody>
        <%
          for (ArrayList<String> dataResult : row)
          {
        %>
        <tr>
          <%
            int index = 0;
            int colCount = model.getColCount(name);
            for (String data : dataResult)
            {
          %>
          <td><%=data%></td>
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
    <%
      }
    }
    %>
    <jsp:include page="/footer.jsp"/>
  </body>
</html>
