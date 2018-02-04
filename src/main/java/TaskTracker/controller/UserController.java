package TaskTracker.controller;

import TaskTracker.model.User;
import TaskTracker.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") int id){
        User user = userService.getUserById(id);
        return ResponseEntity.ok().body(user);
    }

    @GetMapping("/user")
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> users = userService.getAll();
        return ResponseEntity.ok().body(users);
    }

    @PostMapping("/user")
    public ResponseEntity<?> register(@RequestBody User user){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));
        return ResponseEntity.ok().body(userService.register(user));
    }

    @DeleteMapping("/user")
    public ResponseEntity<?> delete(@RequestBody User user){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));
        return ResponseEntity.ok().body(userService.delete(user));
    }

    @PutMapping("/user")
    public ResponseEntity<?> update(@RequestBody User user){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));
        return  ResponseEntity.ok().body(userService.update(user));
    }
}
