package com.hexaware.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import com.hexaware.filter.JwtAuthFilter;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
	
	@Autowired
	private UserDetailsService userDetailService;
	
	@Autowired
	private JwtAuthFilter jwtAuthFilter;
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
	    return httpSecurity
	            .csrf(AbstractHttpConfigurer::disable)
	            .cors(cors -> cors.configurationSource(request -> {
	                CorsConfiguration config = new CorsConfiguration();
	                config.setAllowCredentials(true);
	                config.addAllowedOriginPattern("*"); // Allow all origins
	                config.addAllowedHeader("*");
	                config.addAllowedMethod("*");
	                return config;
	            }))
	            .authorizeHttpRequests(registry -> {
	                registry.requestMatchers("/user/register","/user/sendotp/**","/user/forgetpassword/**", "/user/login", "/route/getall", "/bus/searchbus/**", "/bus/getbus/**").permitAll();
	                registry.requestMatchers("/booking/user/**", "/payment/process/**", "/seat/user/**").hasRole("USER");
	                registry.requestMatchers("/booking/op/**", "/bus/op/**", "/payment/getpayment/**", "/payment/refund/**", "/seat/op/**").hasRole("OPERATOR");
	                registry.requestMatchers("/user/admin/**", "/booking/admin/**", "/route/admin/**").hasRole("ADMIN");
	                registry.requestMatchers( "/booking/getbooking/**").hasAnyRole("USER", "OPERATOR", "ADMIN");
	                registry.requestMatchers("/seat/getseats/**","/user/getuser/**","/user/updatepwd/**","/user/update/**").hasAnyRole("OPERATOR", "USER");
	                registry.anyRequest().authenticated();
	            })
	            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
	            .build();
	}

	
	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailService);
		provider.setPasswordEncoder(passwordEncoder());
		return provider;
	}
	
	@Bean
	public AuthenticationManager authenticationManager() {
		return new ProviderManager(authenticationProvider());
	}
	
	@Bean
	public PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}
}
