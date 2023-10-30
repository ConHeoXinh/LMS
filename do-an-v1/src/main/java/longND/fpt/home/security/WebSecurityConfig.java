package longND.fpt.home.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import longND.fpt.home.security.jwt.JwtAuthenticationEntryPoint;
import longND.fpt.home.security.jwt.JwtAuthenticationFilter;

@Configuration
@EnableMethodSecurity
public class WebSecurityConfig {

	private UserDetailsService userDetailsService;

	private JwtAuthenticationEntryPoint authenticationEntryPoint;

	private JwtAuthenticationFilter authenticationFilter;

	public WebSecurityConfig(UserDetailsService userDetailsService,
			JwtAuthenticationEntryPoint authenticationEntryPoint, JwtAuthenticationFilter authenticationFilter) {
		this.userDetailsService = userDetailsService;
		this.authenticationEntryPoint = authenticationEntryPoint;
		this.authenticationFilter = authenticationFilter;
	}

	@Bean
	public static PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
		return configuration.getAuthenticationManager();
	}

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

//		http.csrf().disable().authorizeHttpRequests((authorize) ->
//		// authorize.anyRequest().authenticated()
//		authorize
//				// .requestMatchers(HttpMethod.GET, "/api/categories/**").permitAll()
//				.requestMatchers("/api/auth/**").permitAll()
//				.requestMatchers("/api/user/**").permitAll()
//				.requestMatchers("/api/book/**").permitAll()
//				.anyRequest().authenticated()
//
//		).exceptionHandling(exception -> exception.authenticationEntryPoint(authenticationEntryPoint))
//				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
//
//		http.addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class);
//
//		return http.build();

		http.csrf().disable().cors().disable();
		// We need to make sure our authentication filter is run before the http request
		// filter is run.
		http.addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class);

		http.authorizeHttpRequests()
				// Specific exclusions or rules.
				.requestMatchers("/api/user/**").permitAll().requestMatchers("/api/auth/**").permitAll()
				.requestMatchers(HttpMethod.GET, "/api/book/**").permitAll()
				// Everything else should be authenticated.
				.anyRequest().authenticated();
		return http.build();

	}

}
