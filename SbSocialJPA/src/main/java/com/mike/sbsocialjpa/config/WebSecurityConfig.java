package com.mike.sbsocialjpa.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.social.security.SpringSocialConfigurer;

import com.mike.sbsocialjpa.entity.AppRole;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.csrf().disable();
		
		// pages for not require login
		http.authorizeRequests().antMatchers("/", "/signup", "/login", "/logout").permitAll();
		
		http.authorizeRequests().antMatchers("/userInfo").access("hasRole('" +  AppRole.ROLE_USER + "')");
		
		// for ADMIN only
		http.authorizeRequests().antMatchers("/admin").access("hasRole('" +  AppRole.ROLE_ADMIN + "')");
		
		//	form login config
		http.authorizeRequests().and().formLogin() // 
			// submit URL of login page
			.loginProcessingUrl("/j_spring_security_check") // submit URL
			.loginPage("/login") //
			.defaultSuccessUrl("/userInfo") //
			.failureUrl("/login?error=true") //
			.usernameParameter("username") //
			.passwordParameter("password");
		
		// logout config
		http.authorizeRequests().and().logout().logoutUrl("/logout").logoutSuccessUrl("/");
		
		
		//	spring social config
		http.apply(new SpringSocialConfigurer())
			//
			.signupUrl("/signUp");
		
	}
	
	@Override
	public UserDetailsService userDetailsService() {
		return userDetailsService;
	}
}
