package ua.skillsup.javacourse.bookstore.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import ua.skillsup.javacourse.bookstore.application.BookSearchService;
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

  @RequestMapping(path = "/recommendations", method = RequestMethod.GET)
  public String getRecommendations(Map<String, Object> model) {
    final List<Publication> publications = bookSearchService.findBestForGenre("Fantasy");

    model.put("publications", publications);

    return "books";
  }

}
