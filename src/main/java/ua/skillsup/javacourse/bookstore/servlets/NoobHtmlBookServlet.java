package ua.skillsup.javacourse.bookstore.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.skillsup.javacourse.bookstore.domain.publication.Publication;

/**
 * @author leopold
 * @since 29/03/16
 */
@WebServlet(name = "bookServlet2", urlPatterns = "/books2")
public class NoobHtmlBookServlet extends AbsBookServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    final PrintWriter writer = resp.getWriter();
    final List<Publication> publications = getPublicationsFromDb();

    writer.println("<html><title>List of recommended publications</title>");
    writer.println("<body>");

    publications.forEach(p -> {
      writer.println(p.toString());
      writer.println("<p>");
    });

    writer.println("</body></html>");
  }
}
