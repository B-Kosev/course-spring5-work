package com.cookingrecipes.cookingrecipeswebmvc.config;

import com.cookingrecipes.cookingrecipeswebmvc.domain.UserService;
import com.cookingrecipes.cookingrecipeswebmvc.entity.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "/api/*").hasAnyRole("ADMIN", "USER")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .and()
                .logout().permitAll();
    }

    @Bean
    public UserDetailsService userDetailsService(UserService userService) {
        return userService::findByUsername;
    }
}