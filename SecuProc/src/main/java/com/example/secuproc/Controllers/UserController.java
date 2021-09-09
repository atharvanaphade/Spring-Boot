package com.example.secuproc.Controllers;

import com.example.secuproc.Serializers.UserData;
import com.example.secuproc.Services.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Resource(name = "userService")
    private UserService userService;

    /**
     * <p>Get all User data in the database, Pagination can be used here in production.</p>
     * @return List<UserData>
     */
    @GetMapping
    public List<UserData> getUsers () {
        return userService.getAllUsers();
    }

    /**
     * Method to get user data based on ID.
     * @param id
     * @return UserData
     */
    @GetMapping("/user/{id}")
    public UserData getUser (@PathVariable Long id) {
        return userService.getUserById(id);
    }

    /**
     * Post request to create User in database.
     * @param userData
     * @return
     */
    @PostMapping("/user")
    public UserData saveUser (final @RequestBody UserData userData) {
        return userService.saveUser(userData);
    }

    /**
     * <p>Delete User based on ID from database.</p>
     * @param id
     * @request
     */
    @DeleteMapping("/user/{id}")
    public Boolean deleteCustomer (@PathVariable Long id) {
        return userService.deleteUser(id);
    }
}
