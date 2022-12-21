package rosa.victor.microususer.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import rosa.victor.microususer.entity.User;
import rosa.victor.microususer.service.UserService;

import java.util.*;

@AllArgsConstructor
@RestController
public class UserController {

    private final UserService userService;

    @GetMapping("/")
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

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> createUser(@Valid @RequestBody User user, BindingResult result) {

        if(!user.getEmail().isEmpty() && userService.findByEmail(user.getEmail()).isPresent()) {
            return ResponseEntity
                    .badRequest()
                    .body(Collections.singletonMap("error", "The email is already in use"));
        }

        if (result.hasErrors()) {
            return getErrors(result);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@Valid @RequestBody User user, BindingResult result, @PathVariable Long id) {

        if (result.hasErrors()) {
            return getErrors(result);
        }

        Optional<User> optionalUser = userService.findById(id);
        if (optionalUser.isEmpty())
            return ResponseEntity.notFound().build();

        User oldUser = optionalUser.get();

        if(!user.getEmail().isEmpty() && !user.getEmail().equalsIgnoreCase(oldUser.getEmail()) && userService.findByEmail(user.getEmail()).isPresent()) {
            return ResponseEntity
                    .badRequest()
                    .body(Collections.singletonMap("error", "The email is already in use"));
        }

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

    private static ResponseEntity<Map<String, String>> getErrors(BindingResult result) {
        Map<String, String> errors = new HashMap<>();
        result.getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));

        return ResponseEntity.badRequest().body(errors);
    }

}
