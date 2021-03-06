package cw3.pckg.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/runlink.html")
public class LinkServlet extends HttpServlet
{
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
  {
    String target = (String) request.getParameter("rowCol");
    String type = request.getParameter("linkType");

    ServletContext context = getServletContext();
    RequestDispatcher dispatch;

    if (type.compareTo("image") == 0)
    {
      dispatch = context.getRequestDispatcher("/imageList.jsp");
    }
    else
    {
      dispatch = context.getRequestDispatcher("/url.jsp");
    }

    request.setAttribute("rowCol",target);
    dispatch.forward(request,response);
  }
}
