package com.example.secuproc.Services;

import com.example.secuproc.Serializers.UserData;

import java.util.List;

public interface UserService {
    UserData saveUser (UserData userData);
    boolean deleteUser (final Long id);
    List<UserData> getAllUsers ();
    UserData getUserById (final Long id);
}
