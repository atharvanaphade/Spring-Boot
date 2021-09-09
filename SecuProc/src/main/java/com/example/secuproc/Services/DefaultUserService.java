package com.example.secuproc.Services;

import com.example.secuproc.Models.User;
import com.example.secuproc.Models.UserRepository;
import com.example.secuproc.Serializers.UserData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service("userService")
public class DefaultUserService implements UserService{

    @Autowired
    UserRepository userRepository;

    /**
     * Create a User based on data sent to the service class.
     * @param userData
     * @return Serializer of the User (UserData).
     * */
    @Override
    public UserData saveUser (UserData userData) {
        User userModel = populateUserEntity(userData);
        return populateUserData(userRepository.save(userModel));
    }

    /**
     * Delete User based on user Id.
     * @param id
     * @return Boolean value if the User was deleted.
     */
    @Override
    public boolean deleteUser (Long id) {
        userRepository.deleteById(id);
        return true;
    }

    /**
     * Method of all Users available in the database.
     * @return A list of all Users.
     */
    @Override
    public List<UserData> getAllUsers () {
        List<UserData> users = new ArrayList<>();
        List<User> userList = userRepository.findAll();
        userList.forEach(user -> {
            users.add(populateUserData(user));
        });
        return users;
    }

    /**
     * Get User by id.
     * @param id
     * @return UserData
     */
    @Override
    public UserData getUserById (Long id) {
        return populateUserData(userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User Not Found")));
    }

    /**
     * Internal method to convert JPA Entity to Serializer object.
     * @param user
     * @return userData
     */
    public UserData populateUserData (final User user) {
        UserData userData = new UserData();
        userData.setId(user.getId());
        userData.setFirstName(user.getFirstName());
        userData.setLastName(user.getLastName());
        userData.setEmail(user.getEmail());

        return userData;
    }

    /**
     * Method to map the serializer object to JPA Entity
     * @param userData
     * @return User
     */
    public User populateUserEntity (UserData userData) {
        User user = new User();
        user.setFirstName(userData.getFirstName());
        user.setLastName(userData.getLastName());
        user.setEmail(userData.getEmail());

        return user;
    }
}
