package ua.skillsup.javacourse.bookstore.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author leopold
 * @since 29/03/16
 */
@Controller
@RequestMapping(path = "/")
public class HelloController {

  @RequestMapping(method = RequestMethod.GET)
  @ResponseBody
  public String hello() {
    return "hello";
  }

}
