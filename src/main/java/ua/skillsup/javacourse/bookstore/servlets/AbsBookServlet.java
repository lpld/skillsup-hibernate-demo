package ua.skillsup.javacourse.bookstore.servlets;

import org.springframework.context.ApplicationContext;

import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import ua.skillsup.javacourse.bookstore.application.BookSearchService;
import ua.skillsup.javacourse.bookstore.domain.publication.Publication;

/**
 * @author leopold
 * @since 29/03/16
 */
public abstract class AbsBookServlet extends HttpServlet {
  private BookSearchService bookSearchService;

  @Override
  public void init(ServletConfig cfg) throws ServletException {
    final ApplicationContext appCtx = getAppCtx(cfg.getServletContext());
    bookSearchService = appCtx.getBean(BookSearchService.class);
  }

  final List<Publication> getPublicationsFromDb() {
    return bookSearchService.findBestForGenre("Fantasy");
  }

  private static ApplicationContext getAppCtx(ServletContext ctx) {
    return ((ApplicationContext) ctx.getAttribute("appCtx"));
  }

}
