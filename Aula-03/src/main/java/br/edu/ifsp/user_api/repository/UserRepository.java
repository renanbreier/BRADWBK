package br.edu.ifsp.user_api.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import br.edu.ifsp.user_api.model.User;
import br.edu.ifsp.user_api.util.UserDataSource;

@Component
public class UserRepository {

    @Autowired
    UserDataSource uDataSource;

    public List<User> getAllUsers() {
        return uDataSource.getDataSource();
    }

    public User getUserById(int id) {
        List<User> users = getAllUsers();
        return users.stream()
                .filter(t -> t.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public void save(User user) {
        uDataSource.add(user);
    }

    public void deleteById(int id) {
        List<User> users = getAllUsers();
        users.removeIf(u -> u.getId() == id);
    }
}
