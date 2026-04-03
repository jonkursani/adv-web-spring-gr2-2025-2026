package dev.jonkursani.restapigr2.security;

import dev.jonkursani.restapigr2.services.AuthService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
@Slf4j // Lombok annotation for logging (printimi i mesazheve)
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final AuthService authService;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        try {
            String token = extractToken(request);

            if (token != null) {
                var userDetails = authService.validateToken(token);
                var authentication = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );

                SecurityContextHolder.getContext().setAuthentication(authentication);

                // per cdo request pe shtojme edhe userId qe e kemi marr prej tokenit
                if (userDetails instanceof AppUserDetails) {
                    request.setAttribute("userId", ((AppUserDetails) userDetails).getUser().getId());
                }
            }
        } catch (Exception e) {
            log.error("Error during authentication: {}", e.getMessage());
        }

        // Aplikimi i filterit
        filterChain.doFilter(request, response);
    }

    private String extractToken(HttpServletRequest request) {
        // Bearer <token>
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }

        return null;
    }
}