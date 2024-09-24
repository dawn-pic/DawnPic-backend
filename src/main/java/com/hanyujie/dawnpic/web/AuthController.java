package com.hanyujie.dawnpic.web;

import com.hanyujie.dawnpic.entity.AuthRequest;
import com.hanyujie.dawnpic.entity.CheckUsernameResponse;
import com.hanyujie.dawnpic.entity.CurrentUserResponse;
import com.hanyujie.dawnpic.entity.User;
import com.hanyujie.dawnpic.enums.RoleEnum;
import com.hanyujie.dawnpic.jwt.JwtUtil;
import com.hanyujie.dawnpic.service.CustomUserDetailsService;
import com.hanyujie.dawnpic.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/user")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final CustomUserDetailsService userDetailsService;
    private final UserService userService;
    private final int MONTH_SECONDS = 2_592_000;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil, CustomUserDetailsService userDetailsService, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthRequest authRequest, HttpServletResponse response) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
            );
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getUsername());
        final String role = userDetails.getAuthorities().iterator().next().getAuthority();
        final String jwt = jwtUtil.generateToken(userDetails.getUsername(), role);
        Cookie cookie = new Cookie("JWT_TOKEN", jwt);;
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        cookie.setMaxAge(MONTH_SECONDS);
        response.addCookie(cookie);

        return ResponseEntity.ok("Login successfully");
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody AuthRequest authRequest, HttpServletResponse response) throws Exception {
        try {
            User newUser = new User();
            newUser.setUsername(authRequest.getUsername());
            newUser.setPassword(authRequest.getPassword());

            userService.saveUser(newUser);
            final String jwt = jwtUtil.generateToken(authRequest.getUsername(), RoleEnum.USER.getRole());
            Cookie cookie = new Cookie("JWT_TOKEN", jwt);
            cookie.setHttpOnly(true);
            cookie.setPath("/");
            cookie.setMaxAge(MONTH_SECONDS);
            response.addCookie(cookie);

            return ResponseEntity.ok("User registered successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("User registration failed" + e.getMessage());
        }
    }

    @GetMapping("/current-user")
    public ResponseEntity<?> getCurrentUser(@AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User details not found");
        }
        CurrentUserResponse currentUserResponse = new CurrentUserResponse(userDetails.getUsername(), userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()));
        return ResponseEntity.ok(currentUserResponse);
    }

    @GetMapping("/check-username")
    public ResponseEntity<?> checkUsername(@RequestParam String username) {
        boolean isUsernameExist = userService.isUsernameExist(username);

        CheckUsernameResponse checkUsernameResponse = new CheckUsernameResponse(isUsernameExist);
        return ResponseEntity.ok(checkUsernameResponse);
    }
}
