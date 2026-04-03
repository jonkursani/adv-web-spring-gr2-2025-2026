package dev.jonkursani.restapigr2.configs;

import dev.jonkursani.restapigr2.entities.Permission;
import dev.jonkursani.restapigr2.entities.Role;
import dev.jonkursani.restapigr2.entities.User;
import dev.jonkursani.restapigr2.repositories.UserRepository;
import dev.jonkursani.restapigr2.security.AppUserDetailsService;
import dev.jonkursani.restapigr2.security.JwtAuthenticationFilter;
import dev.jonkursani.restapigr2.services.AuthService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter(AuthService authService) {
        return new JwtAuthenticationFilter(authService);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, JwtAuthenticationFilter jwtAuthenticationFilter) {
        http.authorizeHttpRequests(auth -> auth
                .requestMatchers(HttpMethod.GET, "/api/departments/**").permitAll() // lejoje me pas qasje pa qene authenticated
                .requestMatchers(
                        "/swagger-ui/**",
                        "/v3/api-docs/**",
                        "/swagger-resources/**",
                        "/webjars/**",
                        "/docs",
                        "/api/auth/**"
                ).permitAll()
                // hasRole() -> perdoret per 1 rol, hasAnyRole() -> perdoret per liste me role
                .requestMatchers("/api/management/**").hasAnyRole(Role.ADMIN.name(), Role.MANAGER.name())
                // hasAuthority() -> perdoret per 1 permission, hasAnyAuthority() -> perdoret per liste me permission
                .requestMatchers(HttpMethod.GET, "/api/management/**")
                    .hasAnyAuthority(Permission.ADMIN_READ.getPermission(), Permission.MANAGER_READ.getPermission())
                .requestMatchers(HttpMethod.POST, "/api/management/**")
                    .hasAnyAuthority(Permission.ADMIN_WRITE.getPermission(), Permission.MANAGER_WRITE.getPermission())
                .requestMatchers(HttpMethod.PUT, "/api/management/**")
                    .hasAnyAuthority(Permission.ADMIN_WRITE.getPermission(), Permission.MANAGER_WRITE.getPermission())
                .requestMatchers(HttpMethod.DELETE, "/api/management/**")
                    .hasAnyAuthority(Permission.ADMIN_WRITE.getPermission(), Permission.MANAGER_WRITE.getPermission())

                .anyRequest().authenticated()
        );

        // Disable CSRF token
        http.csrf(csrf -> csrf.disable());

        // Session management - stateless
        http.sessionManagement(session ->
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        );

        // Add JWT filter
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository) {
        AppUserDetailsService userDetailsService = new AppUserDetailsService(userRepository);

        String email = "user@test.com";
        userRepository.findByEmail(email)
                .orElseGet(() -> {
                    var user = User.builder()
                            .name("Test User")
                            .email(email)
                            .password(passwordEncoder().encode("Test#123"))
                            .active(true)
                            .createdBy(1)
                            .build();

                    return userRepository.save(user);
                });

        return userDetailsService;
    }
}