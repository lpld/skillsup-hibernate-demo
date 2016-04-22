package ua.skillsup.javacourse.bookstore.web;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import ua.skillsup.javacourse.bookstore.application.BookSearchService;
import ua.skillsup.javacourse.bookstore.application.EntityNotFoundException;
import ua.skillsup.javacourse.bookstore.domain.book.Book;
import ua.skillsup.javacourse.bookstore.domain.publication.Publication;

/**
 * @author leopold
 * @since 29/03/16
 */
@Controller
@RequestMapping("/books")
public class BookSearchController {

  @Inject
  private BookSearchService bookSearchService;

  @RequestMapping(path = "/allBooks", method = RequestMethod.GET)
  public String getAllBooks(Map<String, Object> model) {
    final List<Publication> publications = bookSearchService.findBestForGenre("Fantasy");

    model.put("publications", publications);

    return "all_books";
  }

  @RequestMapping(path = "/recommendations", method = RequestMethod.GET)
  public String getRecommendations(Map<String, Object> model) {
    final List<Publication> publications = bookSearchService.findBestForGenre("Fantasy");

    model.put("publications", publications);

    return "books";
  }

  @RequestMapping(path = "/{id}", method = RequestMethod.GET)
  public ModelAndView getBook(@PathVariable("id") Long id) throws EntityNotFoundException {
    SecurityContextHolder.getContext().getAuthentication();

    final Book book = bookSearchService.getBook(id);

    return new ModelAndView("book_view", "book", book);
  }
}
