package tenpo.api.rest.daniel.leon.configs;

import com.google.common.base.Predicates;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;


@Configuration
@EnableSwagger2
@ComponentScan(basePackages = { "tenpo.api.rest.daniel.leon.controllers" })
public class SpringFoxConfig {

    @Value("${api.title}")
    private String title;

    @Value("${api.description}")
    private String description;

    @Value("${api.version}")
    private String version;

    @Bean
    public Docket usersApi() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(usersApiInfo()).select()
                .paths(Predicates.not(PathSelectors.regex("/error.*"))).apis(RequestHandlerSelectors.any()).build()
                .useDefaultResponseMessages(false);
    }

    private ApiInfo usersApiInfo() {
        return new ApiInfoBuilder().title(title).description(description).version(version).build();
    }

    @Bean
    public RestTemplate restTemplate() {

        RestTemplate objTemplate = new RestTemplate();

        objTemplate.setMessageConverters(Arrays.asList(new MappingJackson2HttpMessageConverter()));

        return objTemplate;
    }

}


