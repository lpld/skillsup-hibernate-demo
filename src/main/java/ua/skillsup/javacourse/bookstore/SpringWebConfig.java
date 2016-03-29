package ua.skillsup.javacourse.bookstore;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

/**
 * @author leopold
 * @since 29/03/16
 */
@Configuration
@ComponentScan("ua.skillsup.javacourse.bookstore.web")
@EnableWebMvc
@Import(SpringConfig.class)
public class SpringWebConfig extends WebMvcConfigurerAdapter {

}
