package com.projects.passwc.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.rememberme.InMemoryTokenRepositoryImpl;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .formLogin(formLogin ->
                        formLogin
                                .loginPage("/login")
                                .defaultSuccessUrl("/user/profile", true)
                )
                .logout(logout ->
                        logout
                                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                                .logoutSuccessUrl("/").deleteCookies("passwcKey")
                                .invalidateHttpSession(true)
                )
                .rememberMe(rememberMe ->
                        rememberMe
                                .userDetailsService(userDetailsService)
                                .tokenRepository(new InMemoryTokenRepositoryImpl())
                                .tokenValiditySeconds(2419200)
                                .key("passwcKey")
                )
                .httpBasic(httpBasic ->
                        httpBasic
                                .realmName("Passwc")
                )
                .authorizeRequests(authorizeRequests ->
                        authorizeRequests
                                .antMatchers("/passwds/**").authenticated()
                                .antMatchers("/user/**").authenticated()
                                .anyRequest().permitAll()
                );

        return http.build();
    }
}
