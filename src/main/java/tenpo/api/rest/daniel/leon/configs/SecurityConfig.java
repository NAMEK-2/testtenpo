package tenpo.api.rest.daniel.leon.configs;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import tenpo.api.rest.daniel.leon.filter.CustomAuthorizationFilter;
import tenpo.api.rest.daniel.leon.services.AuthenticationService;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final AuthenticationService authenticationService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.addFilterBefore(new CustomAuthorizationFilter(authenticationService),
                UsernamePasswordAuthenticationFilter.class);
        http.authorizeRequests().antMatchers("math/**").authenticated()
        .anyRequest().permitAll();

    }

}
