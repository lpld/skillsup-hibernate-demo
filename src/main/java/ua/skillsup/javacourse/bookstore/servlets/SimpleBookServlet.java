package ua.skillsup.javacourse.bookstore.servlets;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.skillsup.javacourse.bookstore.application.BookSearchService;
import ua.skillsup.javacourse.bookstore.SpringConfig;
import ua.skillsup.javacourse.bookstore.demo.DatabaseInitializer;
import ua.skillsup.javacourse.bookstore.domain.publication.Publication;

/**
 * @author leopold
 * @since 28/03/16
 */
@WebServlet(name = "bookServlet", urlPatterns = "/books")
public class SimpleBookServlet extends HttpServlet {

  private ConfigurableApplicationContext appCtx;
  private BookSearchService bookSearchService;

  @Override
  public void init() throws ServletException {
    appCtx = new AnnotationConfigApplicationContext(SpringConfig.class);
    appCtx.getBean(DatabaseInitializer.class).initDb();
    bookSearchService = appCtx.getBean(BookSearchService.class);
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    final List<Publication> books = bookSearchService.findBestForGenre("Fantasy");

    resp.getWriter().println(
        books.stream()
            .map(Publication::toString)
            .collect(Collectors.joining())
    );
  }

  @Override
  public void destroy() {
    appCtx.close();
  }
}
