package com.zensark.springdemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import javax.sql.DataSource;

@Configuration
public class AuthConfig extends WebSecurityConfigurerAdapter {
    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/h2-console/**");
        web.ignoring().antMatchers("/user/**");
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
//    	http.csrf().disable();
    	http.logout().logoutUrl("/logout").and()
    	.authorizeRequests()
        .antMatchers("/resources/**").permitAll()
        .antMatchers("/login*").permitAll()
        .anyRequest().authenticated()
        .and()
        .formLogin()
        .loginPage("/login")
        .defaultSuccessUrl("/home");
//        .permitAll();
//    	super.configure(http);
    }

    @Bean
    public UserDetailsService userDetailsService(DataSource dataSource) {
        String userQuery = "select username, password, enabled from users where username =?";
        String rolesQuery = "select username, authority from authorities where username =?";
        JdbcUserDetailsManager userDetailsManager = new JdbcUserDetailsManager(dataSource);
        userDetailsManager.setUsersByUsernameQuery(userQuery);
        userDetailsManager.setAuthoritiesByUsernameQuery(rolesQuery);
        return userDetailsManager;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
