
//🔹 Step 1: What Spring Security Does?
//
//Spring Security protects your app with 2 things:
//
//Authentication → Check if you are a real user.
//Example: Login with username = devansh, password = 1234.
//
//Authorization → Check if you are allowed to do something.
//Example: Only an ADMIN can create products, but a USER can only view products.
//
//Without Spring Security → anyone can hit your APIs.
//With Spring Security → every request is checked.

//🔹 Step 2: How Authentication Works
//
//👉 When you call an API like:
//
//GET /api/products
//
//
//Spring Security will ask → Who are you?
//You must send a username & password (in Basic Auth header).
//
//If correct → ✅ login success.
//If wrong → ❌ 401 Unauthorized.
//
//🔹 Step 3: Define Users & Roles
//
//Spring Security needs to know which users exist and what roles they have.
//Example:
//
//devansh / 1234 → ROLE_USER
//
//admin / admin123 → ROLE_ADMIN



// step 4
package com.bitsandbites.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
	
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // ✅ disable CSRF for REST APIs
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/products/**").hasRole("ADMIN")   // Only ADMIN can access products
                .requestMatchers("/api/categories/**").hasAnyRole("ADMIN", "USER") // ADMIN + USER can access categories
                .anyRequest().authenticated()
            )
            .httpBasic(withDefaults -> {}); // ✅ use Basic Auth (for Postman testing)

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder encoder) {
        UserDetails devansh = User.withUsername("devansh")
                .password(encoder.encode("1234"))
                .roles("USER")
                .build();

        UserDetails admin = User.withUsername("admin")
                .password(encoder.encode("1234"))
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(devansh, admin);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}


//🔹 Step 5: How It Works in Your Project
//
//If you try /api/products with user devansh/1234 → ❌ Forbidden (not ADMIN).
//
//If you try /api/products with admin/admin123 → ✅ Allowed.
//
//If you try /api/categories with both → ✅ Allowed


