package com.azathoth.spring_security_learning.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) {
        try {
            httpSecurity.csrf(AbstractHttpConfigurer::disable) // this will disable the spring security login feature
                    .authorizeHttpRequests(request -> request.anyRequest().authenticated()) // this will allow any request is authenticated
                    .formLogin(Customizer.withDefaults())
                    .httpBasic(Customizer.withDefaults());

            return httpSecurity.build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // customise to have different users
//    @Bean
//    public UserDetailsService userDetailsService() {
//        // creating list of users
//        List<UserDetails> users = Arrays.asList(
//                User.withDefaultPasswordEncoder()
//                        .username("Vanessa")
//                        .password("123")
//                        .roles("USER")
//                        .build(),
//                User.withDefaultPasswordEncoder()
//                        .username("Kate")
//                        .password("123")
//                        .roles("USER")
//                        .build(),
//                User.withDefaultPasswordEncoder()
//                        .username("Benji")
//                        .password("123")
//                        .roles("ADMIN")
//                        .build()
//        );
//
//        return new InMemoryUserDetailsManager(users);
//    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12); // un BCrypt the password
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }
}
