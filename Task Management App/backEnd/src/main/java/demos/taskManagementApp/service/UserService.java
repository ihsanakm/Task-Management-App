package demos.taskManagementApp.service;


import demos.taskManagementApp.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService {
    User saveUser(User user);

    List<User> getAllUsers();

    User getUserById(Long id);

    User updateUser(Long id, User user);

    UserDetailsService userDetailsService();


//    boolean deleteUser(Long id);
}
