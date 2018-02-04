package TaskTracker.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@EnableWebMvc
@Configuration
@ComponentScan(basePackages = {"TaskTracker.controller"})
public class WebConfig extends WebMvcConfigurerAdapter{

}
