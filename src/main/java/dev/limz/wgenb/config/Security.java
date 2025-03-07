package dev.limz.wgenb.config;

import dev.limz.wgenb.security.FirebaseAuthFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class Security {
    FirebaseAuthFilter firebaseAuthFilter;

    @Autowired
    public Security(FirebaseAuthFilter firebaseAuthFilter){
        this.firebaseAuthFilter = firebaseAuthFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        return http
                .addFilterBefore(firebaseAuthFilter, BasicAuthenticationFilter.class)
                .authorizeHttpRequests(requests -> requests.requestMatchers("/").authenticated())
                .build();
    }
}
