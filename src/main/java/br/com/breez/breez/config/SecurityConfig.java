package br.com.breez.breez.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/css/**", "/js/**", "/images/**").permitAll()  // libera homepage e assets
                        .anyRequest().authenticated()                                       // exige login nas outras rotas
                )
                .formLogin(form -> form
                        .loginPage("/login")   // rota customizada, se quiser
                        .permitAll()
                )
                .logout(Customizer.withDefaults());
        return http.build();
    }
}
