package br.edu.ifsp.user_api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifsp.user_api.dto.CreateUserDTO;
import br.edu.ifsp.user_api.model.User;
import br.edu.ifsp.user_api.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public List<User> getUserList() {
        return userRepository.getAllUsers();
    }

    public User getUserById(int id) {
        return userRepository.getUserById(id);
    }

    public User createUser(CreateUserDTO dto) {
        User newUser = new User();
        newUser.setId(generateNewId());
        newUser.setLogin(dto.getLogin());
        newUser.setPassword(dto.getPassword());
        userRepository.save(newUser);

        return newUser;
    }

    public User updateUser(int id, CreateUserDTO dto) {
        User existingUser = userRepository.getUserById(id);

        if (existingUser == null) {
            return null;
        }

        existingUser.setLogin(dto.getLogin());
        existingUser.setPassword(dto.getPassword());

        return existingUser;
    }

    public User deleteUser(int id) {
        User user = userRepository.getUserById(id);

        if (user == null) {
            return null;
        }

        userRepository.deleteById(id);
        return user;
    }

    private int generateNewId() {
        return userRepository.getAllUsers().stream()
                .mapToInt((user) -> user.getId())
                .max()
                .orElse(0) + 1;
    }
}
