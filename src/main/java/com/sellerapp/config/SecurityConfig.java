package com.sellerapp.config;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
//@EnableGlobalMethodSecurity(prePostEnabled=true)
//@EnableWebSecurity
public class SecurityConfig {

		@Autowired
		private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

		@Autowired
		private UserDetailsService jwtUserDetailsService;

		@Autowired
		private JwtRequestFilter jwtRequestFilter;

		public String[] allowedUrl = { "/auth/**", "/swagger-ui/**", "/v3/api-docs/**" };
		
		@Bean
		public PasswordEncoder passwordEncoder() {
			return new BCryptPasswordEncoder();
		}
		
		@Bean
		  public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
		    return authConfig.getAuthenticationManager();
		 }
		
		
	
		
		
		
		 @Bean
		  public DaoAuthenticationProvider authenticationProvider() {
		      DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		       
		      authProvider.setUserDetailsService(jwtUserDetailsService);
		      authProvider.setPasswordEncoder(passwordEncoder());
		   
		      return authProvider;
		  }
		 
		
		 @Bean
		  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
			 http.cors().and() 
				 .csrf().disable().authorizeRequests().antMatchers(allowedUrl).permitAll().
				  anyRequest().authenticated().and().
				 exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).and().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);

			   http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
		    
		    return http.build();
		  }
		
			
		  
		
		    @Bean
		    CorsConfigurationSource corsConfigurationSource() {
		        CorsConfiguration configuration = new CorsConfiguration();
		        configuration.setAllowedOrigins(Arrays.asList("*"));
		        configuration.setAllowedMethods(Arrays.asList("GET","POST"));
		        configuration.setAllowedHeaders(Arrays.asList("*"));
		        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		        source.registerCorsConfiguration("/**", configuration);
		        return source;
		    }

}
