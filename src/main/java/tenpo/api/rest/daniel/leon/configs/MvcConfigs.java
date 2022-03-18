package tenpo.api.rest.daniel.leon.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import tenpo.api.rest.daniel.leon.interceptors.InterceptorSaveApiUses;
import tenpo.api.rest.daniel.leon.repositories.LogCallApiEntityRepository;
import tenpo.api.rest.daniel.leon.services.AuthenticationService;

@Configuration
@EnableWebMvc
@ComponentScan("tenpo.api.rest.daniel.leon.controllers")
public class MvcConfigs implements WebMvcConfigurer {

    @Autowired
    private LogCallApiEntityRepository repository;

    private static final AuthenticationService authenticationService = new AuthenticationService();

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new InterceptorSaveApiUses(repository, authenticationService));
    }
}
