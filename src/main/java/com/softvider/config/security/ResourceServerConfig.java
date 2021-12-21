package com.softvider.config.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.inject.Inject;
import java.util.List;
import java.util.Objects;

@Configuration
@SuppressWarnings("deprecation")
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    private final Environment env;

    @Inject
    public ResourceServerConfig(Environment env) {
        this.env = env;
    }

    @Inject private AccessDeniedHandler accessDeniedHandler;
    @Inject private AuthenticationEntryPoint authenticationEntryPoint;

    private static final List<String> ANONYMOUS_REQUESTS = List.of(
            "/anonymous/**",
            "/security/anonymous/**");

    private static final List<String> SWAGGER_UI = List.of(
            "/swagger-resources/**",
            "/swagger-ui.html",
            "/configuration/**",
            "/webjars/**",
            "/api-docs/**",
            "/v2/api-docs");

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources
                .stateless(true)
                .accessDeniedHandler(this.accessDeniedHandler)
                .authenticationEntryPoint(this.authenticationEntryPoint);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        if(Objects.equals(this.env.getProperty("softvider.oauth2.enable"), "true")){
            http.csrf().disable()
                .authorizeRequests()
                .antMatchers(ANONYMOUS_REQUESTS.toArray(String[]::new)).permitAll()
                .antMatchers(SWAGGER_UI.toArray(String[]::new)).permitAll()
                .antMatchers(HttpMethod.GET, "/*").permitAll()
                .anyRequest().authenticated()
                .and().exceptionHandling()
                    .accessDeniedHandler(this.accessDeniedHandler)
                    .authenticationEntryPoint(this.authenticationEntryPoint);

        }else{
            http.csrf().disable()
                    .authorizeRequests()
                    .antMatchers("*")
                    .permitAll();
        }
    }
}
