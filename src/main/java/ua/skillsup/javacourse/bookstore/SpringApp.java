package ua.skillsup.javacourse.bookstore;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author leopold
 * @since 13/03/16
 */
public class SpringApp {

  public static void main(String[] args) {
    final ApplicationContext appCtx =
        new AnnotationConfigApplicationContext(SpringConfig.class);


  }

}
