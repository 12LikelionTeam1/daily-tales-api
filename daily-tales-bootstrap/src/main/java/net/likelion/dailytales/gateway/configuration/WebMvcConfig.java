package net.likelion.dailytales.gateway.configuration;

import lombok.RequiredArgsConstructor;
import net.likelion.dailytales.authentication.infrastructure.security.LoggedInUserArgumentResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
@EnableWebMvc
@RequiredArgsConstructor
public class WebMvcConfig implements WebMvcConfigurer {
    private final LoggedInUserArgumentResolver loggedInUserArgumentResolver;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("34.22.100.127:5173")
                .allowedOrigins("10.178.0.2:5173")
                .allowedOrigins("http://localhost:5173") //TODO 이거 포트 바꿔야함
                .allowedMethods("OPTIONS","GET","POST","PUT","DELETE");
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(loggedInUserArgumentResolver);
    }
}