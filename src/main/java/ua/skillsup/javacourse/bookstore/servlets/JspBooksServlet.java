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
@WebServlet(name = "bookServlet4", urlPatterns = "/books4")
public class JspBooksServlet extends AbsBookServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    req.setAttribute("publications", getPublicationsFromDb());

    req.getRequestDispatcher("jsp/books.jsp").forward(req, resp);
  }
}
