package com.ymmihw.springframework.webservlet.websocket.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import com.ymmihw.springframework.webservlet.websocket.security.CustomAccessDeniedHandler;
import com.ymmihw.springframework.webservlet.websocket.security.CustomLoginSuccessHandler;
import com.ymmihw.springframework.webservlet.websocket.security.CustomLogoutSuccessHandler;

/**
 * @EnableGlobalAuthentication annotates:
 * @EnableWebSecurity
 * @EnableWebMvcSecurity
 * @EnableGlobalMethodSecurity Passing in 'prePostEnabled = true' allows:
 *                             <p>
 *                             Pre/Post annotations such as: @PreAuthorize("hasRole('ROLE_USER')")
 */

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  /**
   * Login, Logout, Success, and Access Denied beans/handlers
   */

  @Bean
  public AccessDeniedHandler accessDeniedHandler() {
    return new CustomAccessDeniedHandler();
  }

  @Bean
  public LogoutSuccessHandler logoutSuccessHandler() {
    return new CustomLogoutSuccessHandler();
  }

  @Bean
  public AuthenticationSuccessHandler loginSuccessHandler() {
    return new CustomLoginSuccessHandler();
  }

  /**
   * Authentication beans
   */

  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  /**
   * Order of precedence is very important.
   * <p>
   * Matching occurs from top to bottom - so, the topmost match succeeds first.
   */
  @Override
  protected void configure(HttpSecurity http) throws Exception {
    // @formatter:off
        http.authorizeRequests()
            .antMatchers("/", "/index", "/authenticate")
            .permitAll()
            .antMatchers("/secured/**/**", "/secured/**/**/**", "/secured/socket", "/secured/success")
            .authenticated()
            .anyRequest().authenticated()
            .and()
            .formLogin()
            .loginPage("/login").permitAll()
            .usernameParameter("username")
            .passwordParameter("password")
            .loginProcessingUrl("/authenticate")
            .successHandler(loginSuccessHandler())
            .failureUrl("/denied").permitAll()
            .and()
            .logout()
            .logoutSuccessHandler(logoutSuccessHandler())
            .and()
            .exceptionHandling()
            .accessDeniedHandler(accessDeniedHandler());
        /** Disabled for local testing */
        http.csrf().disable();
        /** This is solely required to support H2 console viewing in Spring MVC with Spring Security */
        http.headers()
            .frameOptions()
            .disable();
        // @formatter:on
  }

  @Override
  protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
    auth.inMemoryAuthentication().passwordEncoder(passwordEncoder()).withUser("user")
        .password(passwordEncoder().encode("password")).roles("USER").and().withUser("admin")
        .password(passwordEncoder().encode("password")).roles("ADMIN");
  }

  @Override
  public void configure(WebSecurity web) throws Exception {
    web.ignoring().antMatchers("/resources/**");
  }

}
