package com.ntt.questionados.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.ntt.questionados.config.security.common.Role;
import com.ntt.questionados.config.security.filter.CustomAccessDeniedHandler;
import com.ntt.questionados.config.security.filter.CustomAuthenticationEntryPoint;
import com.ntt.questionados.config.security.filter.JwtRequestFilter;

@SuppressWarnings("deprecation")
@EnableWebSecurity
@Configuration
@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private static final String ID_PATH = "{id:[\\d+]}";
	private static final String PAGE_QUERY_PARAM = "page={page:[\\d+]}&size={size:[\\d+]}";

	private static final String CATEGORIES_URL = "/categories";
	private static final String CATEGORIES_ID_URL = "/categories/" + ID_PATH;
	private static final String CATEGORIES_PAGING_URL = "/categories?" + PAGE_QUERY_PARAM;

	@Autowired
	private JwtRequestFilter jwtRequestFilter;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Bean
	public AccessDeniedHandler accessDeniedHandler() {
		return new CustomAccessDeniedHandler();
	}

	@Bean
	public AuthenticationEntryPoint authenticationEntryPoint() {
		return new CustomAuthenticationEntryPoint();
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	public void configure(AuthenticationManagerBuilder managerBuilder) throws Exception {
		managerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().cors().and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and().authorizeRequests()

				.antMatchers(HttpMethod.POST, CATEGORIES_URL).hasAnyRole(Role.ADMIN.name())
				.antMatchers(HttpMethod.GET, CATEGORIES_URL).hasAnyRole(Role.ADMIN.name())
				.antMatchers(HttpMethod.GET, CATEGORIES_PAGING_URL).hasAnyRole(Role.ADMIN.name())
				.antMatchers(HttpMethod.GET, CATEGORIES_ID_URL).hasAnyRole(Role.ADMIN.name())
				.antMatchers(HttpMethod.PUT, CATEGORIES_ID_URL).hasAnyRole(Role.ADMIN.name())
				.antMatchers(HttpMethod.PATCH, CATEGORIES_ID_URL).hasAnyRole(Role.ADMIN.name())
				.antMatchers(HttpMethod.DELETE, CATEGORIES_ID_URL).hasAnyRole(Role.ADMIN.name())

				.anyRequest().authenticated().and()
				.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class).exceptionHandling()
				.accessDeniedHandler(accessDeniedHandler()).authenticationEntryPoint(authenticationEntryPoint());
	}

}
