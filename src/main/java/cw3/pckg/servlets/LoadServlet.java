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

@WebServlet("/load.html")
public class LoadServlet extends HttpServlet
{
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
  {
    Model model = ModelFactory.getModel();
    String filepath = "./data/" + request.getParameter("selectedFile");

    ServletContext context = getServletContext();
    RequestDispatcher dispatch;
    try
    {
      model.loadFile(filepath);
      dispatch = context.getRequestDispatcher("/loaded.jsp");
    }
    catch (IOException e)
    {
      dispatch = context.getRequestDispatcher("/loadError.html");
    }
    dispatch.forward(request,response);
  }
}
