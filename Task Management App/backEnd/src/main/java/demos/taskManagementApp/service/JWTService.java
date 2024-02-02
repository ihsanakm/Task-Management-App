package demos.taskManagementApp.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface JWTService {

    String extractUserName(String name);

    String generateToken(UserDetails userDetails);

    boolean isTokenValid(String token, UserDetails userDetails);
}
