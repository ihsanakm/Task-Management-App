package demos.taskManagementApp.service;

import demos.taskManagementApp.entity.UserEntity;
import demos.taskManagementApp.model.User;
import demos.taskManagementApp.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserImplementation implements UserService{
    private UserRepository userRepository;

    public UserImplementation(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User saveUser(User user) {
        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(user,userEntity);
        userRepository.save(userEntity);
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        List<UserEntity> userEntities= userRepository.findAll();
        List<User> users;
        users = userEntities.stream().map(userEntity -> new User(
                userEntity.getId(),
                userEntity.getName(),
                userEntity.getRole(),
                userEntity.getPosition(),
                userEntity.getEmail(),
                userEntity.getPassword()
        )).collect(Collectors.toList());
        return users;
    }

    @Override
    public User getUserById(Long id) {
        UserEntity userEntity;
        userEntity = userRepository.findById(id).get();
        User user = new User(userEntity.getId(), userEntity.getName(), userEntity.getRole(), userEntity.getPosition(), userEntity.getEmail(), userEntity.getPassword());
        BeanUtils.copyProperties(userEntity, user);
        return user;
    }

    @Override
    public User updateUser(Long id, User user) {
        UserEntity userEntity = userRepository.findById(id).get();
        userEntity.setRole(user.getRole());

        userRepository.save(userEntity);

        return user;
    }

    @Override
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                return userRepository.findByEmail(username);
            }
        };
    }
}
