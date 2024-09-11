package sae.planning.security;

import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer.FrameOptionsConfig;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

import jakarta.servlet.DispatcherType;

@Configuration
@EnableWebSecurity
public class Security {
	@Bean
	public SecurityFilterChain mesautorisations(HttpSecurity http, HandlerMappingIntrospector introspector)
			throws Exception {
		MvcRequestMatcher.Builder mvc = new MvcRequestMatcher.Builder(introspector);
		
		RequestMatcher h2Matcher = antMatcher("/h2-console/**");                     // pour H2    
         http.csrf().disable().authorizeHttpRequests((authorize) -> authorize
        .dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
        .requestMatchers(antMatcher("/**.css"),antMatcher("/**.js"),antMatcher("/**.png")).permitAll()
            .requestMatchers(mvc.pattern("/"),mvc.pattern(""),mvc.pattern("/createAccount"),mvc.pattern("/planning"),mvc.pattern("/planning-day")).permitAll()
            .requestMatchers( antMatcher("/h2-console/**") ).permitAll()
            .requestMatchers(mvc.pattern("/addPlanning"),mvc.pattern("/deleteSlot"),mvc.pattern("/reservationDay"),mvc.pattern("/deleteAllSlot"),mvc.pattern("/annuleManager")).hasAuthority("manager")
            
            
            
            .anyRequest().authenticated()
        )
        .csrf( customizer -> customizer.ignoringRequestMatchers(h2Matcher) )         // pour H2    
        .headers( headers -> headers.frameOptions(FrameOptionsConfig::sameOrigin) )  // pour H2    
        .formLogin().loginPage("/loginAccount").loginProcessingUrl("/loginAccount").permitAll();
         return http.logout( configurer -> 
            configurer.logoutSuccessUrl("/") 
        )
        .rememberMe( configurer -> 
            configurer.rememberMeParameter("remember")
            .useSecureCookie(true)
        ).csrf(AbstractHttpConfigurer::disable)
        .build();	
		
	}

	
	
	
	@Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers(new AntPathRequestMatcher("/h2-console/**"));
    }

	@Bean
	public PasswordEncoder encoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}
}
