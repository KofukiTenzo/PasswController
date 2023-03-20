package com.projects.passwc.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.InMemoryTokenRepositoryImpl;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource dataSource;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery(
                        "select username, password, true from user where username=?")
                .authoritiesByUsernameQuery(
                        "select username, 'ROLE_USER' from user where username=?")
                .passwordEncoder(passwordEncoder());
    }

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//
//        http.authorizeRequests()
//                .antMatchers("/", "/homepage").permitAll()
//                .antMatchers("/profile").authenticated()
//                .and()
//                .formLogin().permitAll()
//                .loginPage("/login")
//                .loginProcessingUrl("/perform-login")
//                .usernameParameter("username")
//                .passwordParameter("password")
//                .defaultSuccessUrl("/profile");
//    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                    .formLogin()
                        .loginPage("/login")
                .and()
                    .logout()
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                        .logoutSuccessUrl("/").deleteCookies("passwcKey")
                        .invalidateHttpSession(true)
                .and()
                    .rememberMe()
                        .tokenRepository(new InMemoryTokenRepositoryImpl())
                        .tokenValiditySeconds(2419200)
                        .key("passwcKey")
                .and()
                    .httpBasic()
                        .realmName("Passwc")
                .and()
                    .authorizeRequests()
                        .antMatchers(HttpMethod.POST, "/passwds").authenticated()
                        .antMatchers(HttpMethod.POST, "/passwds").authenticated()
                        .antMatchers("/user/**").authenticated()
                        .anyRequest().permitAll();
//                .and()
//                    .requiresChannel()
//                        .antMatchers("/user/register").requiresSecure();
    }
}
