package com.amdrejr.springsecurityjwt.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amdrejr.springsecurityjwt.dto.UserDTO;
import com.amdrejr.springsecurityjwt.entities.Role;
import com.amdrejr.springsecurityjwt.entities.User;
import com.amdrejr.springsecurityjwt.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok().body(userService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        return ResponseEntity.ok().body(userService.findById(id));
    }

    @PostMapping
    public ResponseEntity<User> insert(@RequestBody @NonNull UserDTO user) {
        User newUser = new User(user.getUsername(), user.getPassword(), user.getRoleId());

        userService.save(user);
        return ResponseEntity.created(null).body(newUser);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> update(@PathVariable Long id, @RequestBody UserDTO obj) {
        User user = userService.findById(id);

        user.setUsername(obj.getUsername());
        user.setPassword(obj.getPassword());
        user.setRoles(List.of(new Role(obj.getRoleId())));
        user.setEnabled(obj.getEnabled());

        userService.update(user);
        return ResponseEntity.ok().body(obj);
    }
}
