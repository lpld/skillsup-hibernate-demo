package ua.skillsup.javacourse.bookstore.servlets;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import ua.skillsup.javacourse.bookstore.SpringConfig;
import ua.skillsup.javacourse.bookstore.demo.DatabaseInitializer;

/**
 * @author leopold
 * @since 29/03/16
 */
@WebListener
public class BookAppServletListener implements ServletContextListener {

  @Override
  public void contextInitialized(ServletContextEvent sce) {
    final ConfigurableApplicationContext appCtx =
        new AnnotationConfigApplicationContext(SpringConfig.class);
    appCtx.getBean(DatabaseInitializer.class).initDb();
    sce.getServletContext().setAttribute("appCtx", appCtx);
  }

  @Override
  public void contextDestroyed(ServletContextEvent sce) {
    final Object appCtx = sce.getServletContext().getAttribute("appCtx");
    if (appCtx != null) {
      ((ConfigurableApplicationContext) appCtx).close();
    }
  }
}
