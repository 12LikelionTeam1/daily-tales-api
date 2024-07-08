package net.likelion.dailytales.authentication.infrastructure.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
    private final CustomJwtFilter customJwtFilter;
    private final AuthEntryPoint authEntryPoint;
    private final WebAccessDeniedHandler webAccessDeniedHandler;

    @Bean
    public SecurityFilterChain doFilter(HttpSecurity http) throws Exception {
        return http
                .headers((configurer) -> configurer.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin))
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .sessionManagement((configurer) -> configurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests((configurer) -> configurer
                        .requestMatchers("/login").anonymous()
                        .requestMatchers("/api/oauth/*").anonymous()
                        .requestMatchers("/api/refresh").permitAll()
                        .anyRequest().authenticated()
                )
                .addFilterBefore(customJwtFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling((configurer) -> configurer
                        .authenticationEntryPoint(authEntryPoint)
                        .accessDeniedHandler(webAccessDeniedHandler)
                )
                .build();
    }
}
