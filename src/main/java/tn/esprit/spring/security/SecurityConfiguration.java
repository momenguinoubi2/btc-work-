package tn.esprit.spring.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.authentication.PasswordEncoderParser;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import tn.esprit.spring.filters.JwtRequestFilter;
import tn.esprit.spring.services.MyUserDetailsService;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	@Autowired
	DataSource dataSource;
	@Autowired
	private MyUserDetailsService myUserDetailsService;
	@Autowired
	JwtRequestFilter jwtRequestFilter;

	//Create rules that require authentication for all endpoints except /registration
	@Override
	 protected void configure(HttpSecurity httpSecurity) throws Exception {
		 //httpSecurity.csrf().disable();
		;
		  httpSecurity.csrf().disable().cors().disable()
				  .authorizeRequests()
				  .antMatchers("/client/admin", "/client/admin/**").hasRole("ADMIN")
				  .antMatchers("/client/user","/client/user/**").hasAnyRole("USER", "ADMIN")
				  .antMatchers("/client/entreprise","/client/entreprise/**").hasAnyRole("ENTREPRISE", "ADMIN")
				  .antMatchers("/client/employee","/client/employee/**").hasAnyRole("EMPLOYEE","ENTREPRISE", "ADMIN")
				  .antMatchers("/authenticate","/**").permitAll()
				  .anyRequest().authenticated()
				  //.antMatchers("/**").permitAll()
				  //.and().formLogin();
				  .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		  httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		//Using JWT
		auth.userDetailsService(myUserDetailsService)
				.passwordEncoder(passwordEncoder());

		//using JDBC
//		auth.jdbcAuthentication()
//				.dataSource(dataSource)
//				.passwordEncoder(passwordEncoder())
//				.usersByUsernameQuery("select nom,password,enabled " +
//						"from client " +
//						"where nom = ? ")
//				.authoritiesByUsernameQuery("select nom,role " +
//						"from client " +
//						"where nom = ? ");
	}

	@Override
	@Bean
	public AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}

	 //Crypting password
	 @Bean
	  public PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
		 //return new Pbkdf2PasswordEncoder("pepper", 200000, 256);
		// return NoOpPasswordEncoder.getInstance();
	  }
	  
}
