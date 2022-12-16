package rosa.victor.microususer.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rosa.victor.microususer.entity.User;
import rosa.victor.microususer.service.UserService;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
public class UserController {

    private final UserService userService;

    @GetMapping
    public List<User> getAllUser() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        Optional<User> optionalUser = userService.findById(id);
        if (optionalUser.isEmpty())
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity.ok(optionalUser.get());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser(@RequestBody User user) {
        return userService.save(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@RequestBody User user, @PathVariable Long id) {
        Optional<User> optionalUser = userService.findById(id);
        if (optionalUser.isEmpty())
            return ResponseEntity.notFound().build();

        User oldUser = optionalUser.get();
        oldUser.setName(user.getName());
        oldUser.setEmail(user.getEmail());
        oldUser.setPassword(user.getPassword());

        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(oldUser));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id){
        Optional<User> optionalUser = userService.findById(id);
        if (optionalUser.isEmpty())
            return ResponseEntity.notFound().build();

        userService.delete(id);
        return ResponseEntity.noContent().build();

    }

}
