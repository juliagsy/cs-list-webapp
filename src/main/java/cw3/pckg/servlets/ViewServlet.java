package cw3.pckg.servlets;

import cw3.pckg.model.Model;
import cw3.pckg.model.ModelFactory;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/runview.html")
public class ViewServlet extends HttpServlet
{
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
  {
    request.setAttribute("viewlist",(String) request.getParameter("list"));

    ServletContext context = getServletContext();
    RequestDispatcher dispatch;
    if (((String)request.getParameter("view")).compareTo("list") == 0)
    {
      dispatch = context.getRequestDispatcher("/showListView.jsp");
    }
    else
    {
      dispatch = context.getRequestDispatcher("/showTableView.jsp");
    }

    dispatch.forward(request,response);
  }
}
