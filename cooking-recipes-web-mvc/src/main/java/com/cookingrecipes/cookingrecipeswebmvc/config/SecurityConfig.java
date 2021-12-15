package com.cookingrecipes.cookingrecipeswebmvc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.cookingrecipes.cookingrecipeswebmvc.service.UserService;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// http.csrf().disable().authorizeRequests().antMatchers("/").hasAnyRole("ADMIN", "USER").anyRequest().authenticated().and()
		// .formLogin().loginPage("/auth/login") // default is /login with an HTTP get
		// .and().logout().logoutUrl("/auth/logout").permitAll();

		// @formatter:off
		http.csrf().disable()
				.authorizeRequests()
					.antMatchers("/","/auth/register")
					.permitAll()
					.anyRequest()
					.authenticated()
					.and()
				.formLogin()
					.loginPage("/auth/login")
					.permitAll()
					.defaultSuccessUrl("/recipes")
					.and()
				.logout()
					.logoutUrl("/auth/logout")
					.permitAll();
		// @formatter:on
	}

	@Bean
	public UserDetailsService userDetailsService(UserService userService) {
		return userService::findByUsername;
	}
}
