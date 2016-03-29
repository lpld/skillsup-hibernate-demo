package ua.skillsup.javacourse.bookstore.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author leopold
 * @since 29/03/16
 */
@WebServlet(name = "bookServlet3", urlPatterns = "/books3")
public class HtmlBookServlet extends AbsBookServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    req.getRequestDispatcher("html/books_static.html").include(req, resp);
  }

}
