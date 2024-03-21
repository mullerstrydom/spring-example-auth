package com.example.spring.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.authorizeHttpRequests( auth -> auth
                        .requestMatchers("/test").hasRole("ADMIN")
                        .anyRequest().authenticated())                          // all requests need to be authenticated
                .httpBasic(Customizer.withDefaults())
                .build();
    }

    @Bean
    public UserDetailsManager userDetailsManager(PasswordEncoder passwordEncoder) {
        UserDetails user1 = User.withUsername("user").password(passwordEncoder.encode("userpass")).roles("USER").build();
        UserDetails user2 = User.withUsername("adminuser").password(passwordEncoder.encode("adminpass")).roles("ADMIN").build();
        return new InMemoryUserDetailsManager(user1, user2);
    }

//    @Bean  // alternative for InMemoryUserDetailsManager
//    public UserDetailsManager userDetailsManager(DataSource dataSource) {
//        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource); // get users from the database
//        jdbcUserDetailsManager.setUserExistsSql("select username from users where username = ?"); // you can specify the query to use
//        return jdbcUserDetailsManager;
//    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        // used to encrypt passwords by either MD5, SHA, or BCrypt
        // (md5 and sha is deprecated)
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
