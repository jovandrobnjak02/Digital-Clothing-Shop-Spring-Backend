//package com.example.digital_clothing_shop.Config;
//
//import com.example.digital_clothing_shop.Security.AuthenticationTokenFilter;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
//
//    @Autowired
//    private final AuthenticationProvider authenticationProvider;
//
//    @Autowired
//    private final AuthenticationTokenFilter authenticationTokenFilter;
//
//    public SecurityConfig(AuthenticationProvider authenticationProvider, AuthenticationTokenFilter authenticationTokenFilter) {
//        this.authenticationProvider = authenticationProvider;
//        this.authenticationTokenFilter = authenticationTokenFilter;
//    }
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .cors().and().csrf()
//                .disable()
//                .authorizeHttpRequests(authorizeRequests ->
//                        authorizeRequests
//                                .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
//                                .requestMatchers(HttpMethod.GET, "/products/*").permitAll()
//                                .requestMatchers(HttpMethod.POST, "/users/register").permitAll()
//                                .anyRequest().authenticated()
//                )
//                .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//                .addFilterBefore(authenticationTokenFilter, UsernamePasswordAuthenticationFilter.class)
//                .logout().disable()
//        ;
//
//        return http.build();
//    }
//
//
//}
