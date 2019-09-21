package com.mike.sbsocialjpa.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.UserIdSource;
import org.springframework.social.config.annotation.ConnectionFactoryConfigurer;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurer;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.connect.web.ConnectController;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import org.springframework.social.google.connect.GoogleConnectionFactory;
import org.springframework.social.security.AuthenticationNameUserIdSource;

import com.mike.sbsocialjpa.dao.AppUserDAO;

@Configuration
@EnableSocial
//Load to Environment.
@PropertySource("classpath:social-cfg.properties")
public class SocialConfig implements SocialConfigurer {

	private boolean autoSignUp = false;
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired AppUserDAO appUserDAO;
	
	
	@Override
	public void addConnectionFactories(ConnectionFactoryConfigurer connectionFactoryConfigurer,
			Environment environment) {
		// TODO Auto-generated method stub
		try {
			this.autoSignUp = Boolean.parseBoolean(environment.getProperty("social.auto-signup"));
		} catch (Exception e) {
			this.autoSignUp = false;
		}
		
		// facebook
		FacebookConnectionFactory ffactory = new FacebookConnectionFactory (//
				environment.getProperty("facebook.app.id"),//
				environment.getProperty("facebook.app.secret"));
		
		ffactory.setScope(environment.getProperty("facebook.scope"));
		
		// auth_type = reauthenticate
		connectionFactoryConfigurer.addConnectionFactory(ffactory);
		
		// google
		GoogleConnectionFactory gfactory = new GoogleConnectionFactory(//
				environment.getProperty("google.client.id"), //
				environment.getProperty("google.client.secret"));
		
		gfactory.setScope(environment.getProperty("google.scope"));
		
		connectionFactoryConfigurer.addConnectionFactory(gfactory);
	}

	@Override
	public UserIdSource getUserIdSource() {
		// TODO Auto-generated method stub
		return new AuthenticationNameUserIdSource();
	}

	@Override
	public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
		// TODO Auto-generated method stub
		JdbcUsersConnectionRepository usersConnectionRepository = new JdbcUsersConnectionRepository(dataSource,
				connectionFactoryLocator,
				
				Encryptors.noOpText());
		
		if(autoSignUp) {
			// After logging in to social networking 
			// Automatically creates corresponding APP_USER if it does not exist
			ConnectionSignUp connectionSignUp = new ConnectionSignUpImpl(appUserDAO);
			usersConnectionRepository.setConnectionSignUp(connectionSignUp);
		}
		return usersConnectionRepository;
	}
	
	@Bean
	public ConnectController connectionController(ConnectionFactoryLocator connectionFactoryLocator, //
			ConnectionRepository connectionRepository) {
		return new ConnectController(connectionFactoryLocator, connectionRepository);
	}
 
}
