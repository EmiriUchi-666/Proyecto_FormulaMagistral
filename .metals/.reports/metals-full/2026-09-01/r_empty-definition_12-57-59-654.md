error id: file:///C:/Users/USUARIO/Documents/farmacia/farmacia/src/main/java/com/example/farmacia/config/SecurityConfig.java:_empty_/`<any>`#ignoringRequestMatchers#
file:///C:/Users/USUARIO/Documents/farmacia/farmacia/src/main/java/com/example/farmacia/config/SecurityConfig.java
empty definition using pc, found symbol in pc: _empty_/`<any>`#ignoringRequestMatchers#
empty definition using semanticdb
empty definition using fallback
non-local guesses:

offset: 1326
uri: file:///C:/Users/USUARIO/Documents/farmacia/farmacia/src/main/java/com/example/farmacia/config/SecurityConfig.java
text:
```scala
package com.example.farmacia.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/css/**", "/js/**", "/h2-console/**", "/login").permitAll()
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/login")
                .defaultSuccessUrl("/", true)
                .permitAll()
            )
            .logout(logout -> logout
                .logoutSuccessUrl("/login?logout")
                .permitAll()
            )
            .headers(headers -> headers
                .frameOptions(frame -> frame.sameOrigin())
            )
            .csrf(csrf -> csrf
                .ignoringRequestM@@atchers("/h2-console/**")
            );
        return http.build();
    }

    @Bean
    UserDetailsService userDetailsService() {
        return new InMemoryUserDetailsManager(
            User.withUsername("admin")
                .password("{noop}admin123")
                .roles("ADMIN", "USER")
                .build()
        );
    }
}
```


#### Short summary: 

empty definition using pc, found symbol in pc: _empty_/`<any>`#ignoringRequestMatchers#