package com.elearn.app.elearnapi.modules.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.elearn.app.elearnapi.config.UserRole;
import com.elearn.app.elearnapi.errors.HTTPServerError;
import com.elearn.app.elearnapi.utilities.PasswordUtilities;
import com.elearn.app.elearnapi.utilities.StringUtilities;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User findByEmail(String email) {
        String normalizedEmail = StringUtilities.normalize(email);
        User user = this.userRepository.findByEmail(normalizedEmail);
        return user;
    }

    public User checkAlreadyFindByEmail(String email) {
        User user = this.findByEmail(email);
        if (user != null) {
            throw new HTTPServerError(HttpStatus.CONFLICT,
                    String.format("there is user already registered with this email ( %s )", email));
        }
        return user;
    }

    private User createUser(
            String name,
            String email,
            String password,
            UserRole role) {

        this.checkAlreadyFindByEmail(email);

        String hashedPassword = PasswordUtilities.hashPassword(password);
        String normalizedEmail = StringUtilities.normalize(email);
        User user = new User(name, normalizedEmail, hashedPassword, role);
        return this.userRepository.save(user);
    }

    public User createStudentUser(
            String name,
            String email,
            String password) {
        User user = this.createUser(name, email, password, UserRole.STUDENT);
        return user;
    }

}
