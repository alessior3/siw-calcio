package it.uniroma3.siw.configuration;

import javax.sql.DataSource;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
	private final DataSource dataSource;

	public SecurityConfiguration(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	@Bean
	public UserDetailsService userDetailsService() {
		JdbcUserDetailsManager manager = new JdbcUserDetailsManager(dataSource);
		manager.setUsersByUsernameQuery("SELECT username, password, 1 as enabled FROM credentials WHERE username=?");
		manager.setAuthoritiesByUsernameQuery("SELECT username, ruolo_utente FROM credentials WHERE username=?");
		return manager;
	}
	
	@Bean
	public PasswordEncoder passwordEncorder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	protected SecurityFilterChain configure(final HttpSecurity httpSecurity) throws Exception{
		httpSecurity.csrf(csrf -> csrf.disable()); // Disabilitiamo CSRF per permettere logout/login da React
		httpSecurity.authorizeHttpRequests(authorize -> {
			authorize.requestMatchers("/css/**", "/images/**", "/favicon.ico", "/", "/index", "/login", "/register", "/crash", "/error", "/api/**").permitAll();
			authorize.requestMatchers(HttpMethod.GET, "/squadre/**", "/tornei/**", "/partite/**").permitAll();
			authorize.requestMatchers("/admin/**").hasAuthority("ADMIN");
			authorize.anyRequest().authenticated();
		});
		httpSecurity.formLogin(form -> {
			form.loginPage("/login").permitAll();
			form.defaultSuccessUrl("/success", true);
			form.failureUrl("/login?error=true");
		});
		httpSecurity.logout(logout -> {
			logout.logoutUrl("/logout");
			logout.logoutSuccessUrl("/");
			logout.invalidateHttpSession(true);
			logout.deleteCookies("JSESSIONID");
			logout.clearAuthentication(true);
			logout.permitAll();
		});
		return httpSecurity.build();
	}
	
}
