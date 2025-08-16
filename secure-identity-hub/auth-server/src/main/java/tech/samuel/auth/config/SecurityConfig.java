package tech.samuel.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    // @Bean
    // public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    //     http
    //         .csrf(csrf -> csrf.disable()) // por enquanto desabilita CSRF
    //         .authorizeHttpRequests(auth -> auth
    //             .anyRequest().permitAll() // libera tudo (vamos travar depois)
    //         );
    //     return http.build();
    // }
     @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // só pra simplificar no início
            .authorizeHttpRequests(auth -> auth
                .anyRequest().authenticated() // 🔒 exige autenticação em tudo
            )
            .httpBasic(); // autenticação básica (usuário/senha)
        return http.build();
    }
}
