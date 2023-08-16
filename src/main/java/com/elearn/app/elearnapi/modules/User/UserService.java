package com.elearn.app.elearnapi.modules.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.elearn.app.elearnapi.config.constants.UserRole;
import com.elearn.app.elearnapi.errors.HTTPServerError;
import com.elearn.app.elearnapi.modules.User.DTO.AdminUserResponse;
import com.elearn.app.elearnapi.modules.User.DTO.LoginResponse;
import com.elearn.app.elearnapi.modules.User.DTO.StudentUserResponse;
import com.elearn.app.elearnapi.modules.User.DTO.UserResponse;
import com.elearn.app.elearnapi.utilities.JWTUtilities;
import com.elearn.app.elearnapi.utilities.PasswordUtilities;
import com.elearn.app.elearnapi.utilities.PrintUtilities;
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

    public User checkFindByEmail(String email) {
        User user = this.findByEmail(email);
        if (user == null) {
            throw new HTTPServerError(HttpStatus.NOT_FOUND,
                    String.format("there is no user registered with this email ( %s )", email));
        }
        return user;
    }

    public void checkIsInRole(User user, UserRole userRole) {

        if (!user.getRole().equals(userRole)) {
            throw new HTTPServerError(HttpStatus.BAD_REQUEST,
                    String.format("this email ( %s ) is not related to a ( %s ) account", user.getEmail(),
                            userRole.toString()));
        }
    }

    public void checkPasswordIsCorrect(User user, String password) {
        boolean isCorrect = PasswordUtilities.isPasswordCorrect(password, user.getPassword());
        if (!isCorrect) {
            throw new HTTPServerError(HttpStatus.BAD_REQUEST,
                    String.format("Password is not correct"));
        }
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

    public User createAdminUser(
            String name,
            String email,
            String password) {
        User user = this.createUser(name, email, password, UserRole.ADMIN);
        return user;
    }

    public StudentUserResponse makeStudentUserResponse(User user) {
        return new StudentUserResponse(user);
    }

    public AdminUserResponse makeAdminUserResponse(User user) {
        return new AdminUserResponse(user);
    }

    public UserResponse makeUserResponse(User user) {
        if (user.getRole() == UserRole.STUDENT) {
            return this.makeStudentUserResponse(user);
        } else if (user.getRole() == UserRole.ADMIN) {
            return this.makeAdminUserResponse(user);

        }
        return null;
    }

    public LoginResponse makeLoginResponse(User user) {

        String token = JWTUtilities.generateJwtToken(user);

        UserResponse userResponse = this.makeUserResponse(user);

        return new LoginResponse(userResponse, token);
    }

}
