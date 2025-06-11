package com.petadoption.pet_adoption_api.security;

import com.petadoption.pet_adoption_api.repositories.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {


    @Autowired
    TokenService tokenService;

    @Autowired
    private UserRepository userRepository;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String path = request.getRequestURI();
        String method = request.getMethod();

        boolean isPublicPath =
                (method.equals("GET") && path.startsWith("/api/pets")) ||
                        (method.equals("POST") && path.equals("/auth/login")) ||
                        (method.equals("POST") && path.equals("/api/requests"));

        if (isPublicPath) {
            System.out.println("🔓 Rota pública liberada: " + method + " " + path);
            filterChain.doFilter(request, response);
            return;
        }

        try {
            var token = this.recoverToken(request);
            if (token != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                var login = tokenService.validateToken(token);
                if (login != null) {
                    var user = userRepository.findByLogin(login);
                    if (user != null) {
                        var auth = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                        SecurityContextHolder.getContext().setAuthentication(auth);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("❌ Erro ao validar token: " + e.getMessage());
            // NÃO interrompe o fluxo
        }

        filterChain.doFilter(request, response);
    }



    private String recoverToken(HttpServletRequest request) {
        var authHeader = request.getHeader("Authorization");
        if (authHeader == null) return null;
        return authHeader.replace("Bearer ", "");
    }
}
