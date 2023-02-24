package crud.service;

import crud.model.User;

import java.util.List;

public interface UserService {

    void addUser(User user);
    void updateUser(User user);
    void removeUser(Long id);
    User getUserById(Long id);
    List<User> getAllUsers();

}