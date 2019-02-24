package hr.vvg.Miokovic.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * 
 * Predstavlja klasu za konfiguraciju sigurnosnih obilježja aplikacije.
 * 
 * @author Denis
 *
 */

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	DataSource dataSource;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth
			.jdbcAuthentication()
				.dataSource(dataSource)
				.usersByUsernameQuery("SELECT username, password, enabled FROM Korisnik WHERE username=?")
				.authoritiesByUsernameQuery("SELECT username, authority FROM KorisnikPrava WHERE username=?")
				.passwordEncoder(new BCryptPasswordEncoder(4));
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http
			.authorizeRequests()
				.antMatchers("/novoPredavanje/**")
				.hasRole("USER")
				.antMatchers("/**").permitAll()
				.and()
			.formLogin()
				.loginPage("/login")
				.defaultSuccessUrl("/novoPredavanje", true)
				.and()
			.logout()
				.logoutSuccessUrl("/login")
			.and()
				.csrf().ignoringAntMatchers("/**", "/login");
	}
}
