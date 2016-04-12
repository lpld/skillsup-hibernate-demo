package ua.skillsup.javacourse.bookstore.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.inject.Inject;

import ua.skillsup.javacourse.bookstore.application.BookSearchService;
import ua.skillsup.javacourse.bookstore.application.EntityNotFoundException;
import ua.skillsup.javacourse.bookstore.domain.book.Book;

/**
 * @author leopold
 * @since 12/04/16
 */
@Controller
@RequestMapping(path = "/admin")
public class AdminController {

  @Inject
  private BookSearchService bookSearchService;

  @RequestMapping(path = "books/{id}", method = RequestMethod.POST)
  public String updateBook(@ModelAttribute Book book) throws EntityNotFoundException {
    bookSearchService.updateBookInfo(book);

    return "redirect:/books/recommendations";
  }

  @RequestMapping(path = "books/{id}", method = RequestMethod.GET)
  public ModelAndView edit(@PathVariable("id") Long id) throws EntityNotFoundException {
    final Book book = bookSearchService.getBook(id);

    return new ModelAndView("book_edit", "book", book);
  }
}
