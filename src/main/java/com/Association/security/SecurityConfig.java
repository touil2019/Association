package com.Association.security;

import com.Association.security.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    private final UserService userDetailsService;

    @Autowired
    public SecurityConfig(UserService userDetailsService){
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){

        return new BCryptPasswordEncoder();
    }


    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    protected void configure(HttpSecurity httpSecurity) throws Exception{
        httpSecurity
                .authorizeRequests()
                .anyRequest().permitAll()
                .and()
                .formLogin().loginPage("/login")
                .defaultSuccessUrl("/home")
                .failureUrl("/login?error=true")
                .usernameParameter("username").passwordParameter("password")
                .and()
                .logout().invalidateHttpSession(true)
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout=true")
                .and()
                .csrf()
                .and()
                .sessionManagement().maximumSessions(1).expiredUrl("/login");
    }

}
