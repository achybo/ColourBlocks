package com.homidle.colorblocks;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@Component
public class ColorJwtFilter extends OncePerRequestFilter {
    private ColorJwtUtil colorJwtUtil;
    private CustomUserDetails customUserDetails;

    public ColorJwtFilter(ColorJwtUtil colorJwtUtil, CustomUserDetails customUserDetails) {
        this.colorJwtUtil = colorJwtUtil;
        this.customUserDetails = customUserDetails;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")){
            String token = authHeader.substring(7);
            String username = colorJwtUtil.extractUsername(token);

            if(username != null && SecurityContextHolder.getContext().getAuthentication()==null){

                UserDetails userDetails = customUserDetails.loadUserByUsername(username);

                if (colorJwtUtil.isTokenValid(token, userDetails)){
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            }
        }
        filterChain.doFilter(request, response);
    }
}
