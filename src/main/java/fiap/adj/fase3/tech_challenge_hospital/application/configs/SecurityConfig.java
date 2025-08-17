package fiap.adj.fase3.tech_challenge_hospital.application.configs;

import fiap.adj.fase3.tech_challenge_hospital.application.configs.security.PlainTextPasswordEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // Configura gerenciador de autenticação
    // HttpSecurity - trata da camada de segurança do Http
    // O HttpSecurity muda a configuração padrão para Http Basic
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity httpSecurity) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = httpSecurity.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
        return authenticationManagerBuilder.build();
    }

    // Define como será a codificação do password
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new PlainTextPasswordEncoder();
    }

    // SecurityFilterChain - Sequência de filtros de URL para segurança
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        // Filtra os paths e aplica políticas de segurança
        httpSecurity.authorizeHttpRequests(authorizeRequests -> {
            authorizeRequests.requestMatchers("/public").permitAll();
            authorizeRequests.requestMatchers("/logout").permitAll();
            authorizeRequests.anyRequest().authenticated();
        });
        httpSecurity.httpBasic(Customizer.withDefaults());
        return httpSecurity.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager inMemoryUserDetailsManager = new InMemoryUserDetailsManager();
        inMemoryUserDetailsManager.createUser(
                User.withUsername("usuario")
                        .password("senha")
                        .roles("USER")
                        .build()
        );
        inMemoryUserDetailsManager.createUser(
                User.withUsername("admin")
                        .password("password")
                        .roles("ADMIN", "USER")
                        .build()
        );
        return inMemoryUserDetailsManager;
    }
}
