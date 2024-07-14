package net.likelion.dailytales.authentication.infrastructure.security;

import jakarta.annotation.Nonnull;
import net.likelion.dailytales.core.domain.authentication.LoggedInUser;
import net.likelion.dailytales.core.domain.authentication.exception.AuthenticationFailedException;
import org.springframework.core.MethodParameter;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.security.core.context.SecurityContextHolder;

@Component
public class LoggedInUserArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(@Nonnull MethodParameter parameter) {
        return parameter.hasParameterAnnotation(LoggedInUser.class)
                && parameter.getParameterType().equals(String.class);
    }

    @Override
    public String resolveArgument(
            @Nonnull MethodParameter parameter,
            ModelAndViewContainer mavContainer,
            @Nonnull NativeWebRequest webRequest,
            WebDataBinderFactory binderFactory
    ) {
        Authentication authentication = SecurityContextHolder
                .getContext()
                .getAuthentication();
        if (!(authentication instanceof APIAuthentication)) {
            throw new AuthenticationFailedException();
        }
        return (String) authentication.getPrincipal();
    }
}
