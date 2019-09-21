package com.mike.sbsecurity.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.mike.sbsecurity.service.UserDetailsServicelmpl;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	UserDetailsServicelmpl userDetailService;
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder bCryptPasswordEncoder  = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder;
	}
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		// Sét đặt dịch vụ để tìm kiếm User trong Database.
        // Và sét đặt PasswordEncoder.
		auth.userDetailsService(userDetailService).passwordEncoder(passwordEncoder());
	}
	
	
	public void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		
		 // Các trang không yêu cầu login
		http.authorizeRequests().antMatchers("/","/login","logout");
		
		// Trang /userInfo yêu cầu phải login với vai trò ROLE_USER hoặc ROLE_ADMIN.
        // Nếu chưa login, nó sẽ redirect tới trang /login.
		http.authorizeRequests().antMatchers("/userInfo").access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')");
		
		//// Trang chỉ dành cho ADMIN
		http.authorizeRequests().antMatchers("/admin").access("hasRole('ROLE_ADMIN')");
		
		 // Khi người dùng đã login, với vai trò XX.
        // Nhưng truy cập vào trang yêu cầu vai trò YY,
        // Ngoại lệ AccessDeniedException sẽ ném ra.
		http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403");
		
		// Cấu hình cho Login form
		http.authorizeRequests().and().formLogin() // 
			// submit URL of login page
			.loginProcessingUrl("j_spring_security_check") // submit URL
			.loginPage("/login")
			.defaultSuccessUrl("userAccountInfo") //
			.failureUrl("/login?error=true")//
			.usernameParameter("username")//
			.passwordParameter("password")
			// Cấu hình cho logout page
			.and().logout().logoutUrl("/logout").logoutSuccessUrl("logoutSuccessful");
	}
	
}
