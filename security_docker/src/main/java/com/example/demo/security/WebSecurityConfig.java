package com.example.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.demo.repository.UserRepository;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private AuthenticationConfiguration authenticationConfiguration;
	@Autowired
	private UserDetailsServiceImpl userDetailsServiceImpl;
	@Autowired
	private JwtAuthenticationFilter jwtAuthenticationFilter;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//		http
//			.authorizeHttpRequests((requests) -> requests
//				.requestMatchers("/", "/home","/**","/api/sign").permitAll()
//				.anyRequest().authenticated()
//			)
//			.formLogin((form) -> form
//				.loginPage("/login")
//				.permitAll()
//			)
//			.logout((logout) -> logout.permitAll());
		http
				// you probably want a request matcher since you are using @Order
		.csrf().disable()
//		.cors().disable()
		.cors().and()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//		.and()
//		.oauth2Client()
		.and()
		.authorizeHttpRequests((authorize) -> 
		authorize
		.requestMatchers("/", "/home","/api/**").permitAll()
//				.anyRequest().permitAll())
		.requestMatchers("/page/pageA").hasAnyRole("GUEST","VIP","MANERGE","ADMIN")
		.requestMatchers("/page/pageB").hasAnyRole("VIP","MANERGE","ADMIN")
		.requestMatchers("/page/pageC").hasAnyRole("MANERGE","ADMIN")
		.requestMatchers("/page/pageD").hasAnyRole("ADMIN")
		.anyRequest().authenticated())
//		.oauth2Client()
//		.and()
		.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
	    
//		GUEST,
//		VIP,
//		MANERGE,
//		ADMIN
		
		return http.build();

//		return http.build();
	}

	@Bean
	public AuthenticationManager authenticationManager() throws Exception {
		AuthenticationManager authenticationManager = authenticationConfiguration.getAuthenticationManager();
		return authenticationManager;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	@Bean
    DaoAuthenticationProvider authProvider(){
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsServiceImpl);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }
	

//	@Bean
//	public UserDetailsService userDetailsService() {
//		
//	}
}
