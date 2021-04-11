package fi.jussi.harjoitustyo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import fi.jussi.harjoitustyo.web.UserDetailServiceImpl;

import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailServiceImpl userDetailsService;	
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        /*.authorizeRequests().antMatchers("/h2-console/**").permitAll()  // kommentoi h-2 consolen käyttö permitall pois lopullisesta sovelluksesta
        .and().csrf().ignoringAntMatchers("/h2-console/**") // -- 
        .and().headers().frameOptions().sameOrigin() // -- 
        .and()*/
        .authorizeRequests().antMatchers("/css/**", "/", "/index", "/images/**").permitAll() // Enable /css/**  when logged out
        .and()
        .authorizeRequests()
        .anyRequest().authenticated()
        .and()
      .formLogin()
          .defaultSuccessUrl("/index", true)
          .permitAll()
          .and()
      .logout()
          .logoutSuccessUrl("/index")
          .permitAll();
    }
    
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }
}

