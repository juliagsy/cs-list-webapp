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
    <ol>
      <%
        for (ArrayList<String> rowData : allDataByRow)
        {
      %>
      <li>
      <%
          String row = String.join(", ",rowData);
      %>
        <%=row%>
      </li>
      <%
        }
      %>
    </ol>
    <jsp:include page="/footer.jsp"/>
  </body>
</html>
