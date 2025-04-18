package br.edu.ifsp.user_api.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifsp.user_api.dto.CreateUserDTO;
import br.edu.ifsp.user_api.model.User;
import br.edu.ifsp.user_api.service.UserService;

@RestController
@RequestMapping("api")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("user")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getUserList();
        return ResponseEntity.ok().body(users);
    }

    @GetMapping("user/{id}")
    public ResponseEntity<User> getOneUser(
            @PathVariable int id) {
        User user = userService.getUserById(id);
        if (user != null)
            return ResponseEntity.ok().body(user); // Status 200
        return ResponseEntity.notFound().build(); // Status 404
    }

    @PostMapping("user")
    public ResponseEntity<User> createUser(@RequestBody CreateUserDTO dto) {
        User newUser = userService.createUser(dto);

        return ResponseEntity
                .created(URI.create("/api/user/" + newUser.getId()))
                .body(newUser);

    }

    @PutMapping("user/{id}")
    public ResponseEntity<User> updateUser(
            @PathVariable int id,
            @RequestBody CreateUserDTO dto) {

        User updatedUser = userService.updateUser(id, dto);

        if (updatedUser != null) {
            return ResponseEntity.ok(updatedUser);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("user/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable int id) {
        User deletedUser = userService.deleteUser(id);

        if (deletedUser != null) {
            return ResponseEntity.ok(deletedUser);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
