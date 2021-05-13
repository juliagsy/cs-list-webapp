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
      String name = (String) request.getAttribute("editlist");

      Model model = ModelFactory.getModel();
      ArrayList<ArrayList<String>> allDataByRow = model.viewList(name);
      int colCount = model.getColCount(name);

    %>
    <h1>"<%=name%>" Table</h1>
    <form method="POST" action="/runprocess.html">
      <table>
        <tbody>
          <%
          String val;
          int rowNum = 0;
          for (ArrayList<String> row : allDataByRow)
            {
          %>
          <tr>
            <%
              int colNum = 0;
              for (String data : row)
              {
                val = name + "@" + Integer.toString(rowNum) + "@" + Integer.toString(colNum);
            %>
            <td><input type="radio" name="rowCol" value=<%=val%> required><%=data%></td>
            <%
                colNum ++;
              }
              if (colNum < colCount)
              {
                val = name + "@" + Integer.toString(rowNum) + "@" + Integer.toString(colNum);
            %>
            <td><input type="radio" name="rowCol" value=<%=val%> required>.</td>
            <%
              }
            %>
          </tr>
          <%
              rowNum ++;
            }
          %>
        </tbody>
      </table>
      <label>
        <input type="radio" name="editType" value="add@col@left" required>Add Column to the Left<br>
        <input type="radio" name="editType" value="add@col@right">Add Column to the Right<br>
        <input type="radio" name="editType" value="add@row@up">Add Row Above<br>
        <input type="radio" name="editType" value="add@row@down">Add Row Bottom<br>
        <input type="radio" name="editType" value="remove@col">Remove Column<br>
        <input type="radio" name="editType" value="remove@row">Remove Row<br>
        <input type="radio" name="editType" value="edit">Edit this Grid<br>
        <input type="radio" name="editType" value="link" disabled>Link URL, Image or List to this Grid<br>
      </label>
      <input type="submit" value="Process!">
    </form>
    <p>All changes will be <b>saved automatically</b> to its original file</p>
    <jsp:include page="/footer.jsp"/>
  </body>
</html>
