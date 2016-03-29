package ua.skillsup.javacourse.bookstore.servlets;

import java.io.IOException;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.skillsup.javacourse.bookstore.domain.publication.Publication;

/**
 * @author leopold
 * @since 29/03/16
 */
@WebServlet(name = "bookServlet1", urlPatterns = "/books1")
public class StringBookServlet extends AbsBookServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    resp.getWriter().println(
        getPublicationsFromDb().stream()
            .map(Publication::toString)
            .collect(Collectors.joining())
    );
  }
}
