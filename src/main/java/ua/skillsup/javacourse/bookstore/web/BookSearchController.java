package ua.skillsup.javacourse.bookstore.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.stream.Collectors;

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

  @RequestMapping(method = RequestMethod.GET)
  @ResponseBody
  public String getRecommendations() {
    final List<Publication> publications = bookSearchService.findBestForGenre("Fantasy");

    return publications.stream().map(Publication::toString).collect(Collectors.joining(" "));
  }

}
