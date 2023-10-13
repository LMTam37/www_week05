package vn.edu.iuh.fit.week_05.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class CandidateConfiguration {
    @Bean
    public UserDetailsService userDetailsService(){
        User.UserBuilder users = User.withDefaultPasswordEncoder();
        UserDetails user = users
                .username("employer")
                .password("1")
                .roles("EMPLOYER")
                .build();
        UserDetails admin = users
                .username("candidate")
                .password("1")
                .roles("CANDIDATE")
                .build();
        return new InMemoryUserDetailsManager(user, admin);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
                .authorizeHttpRequests(registry ->
                        registry.requestMatchers("login").permitAll()
                                .anyRequest().authenticated())
                .formLogin(
                        formLoginConfigurer -> formLoginConfigurer
                                .defaultSuccessUrl("/home", true).permitAll()
                );
        return http.build();
    }
}
