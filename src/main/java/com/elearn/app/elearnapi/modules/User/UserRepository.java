package com.elearn.app.elearnapi.modules.User;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, String> {
    public User findByEmail(String email);
}
