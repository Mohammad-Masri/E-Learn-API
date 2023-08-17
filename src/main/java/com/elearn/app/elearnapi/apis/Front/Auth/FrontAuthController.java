package com.elearn.app.elearnapi.apis.Front.Auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.elearn.app.elearnapi.config.constants.UserRole;
import com.elearn.app.elearnapi.modules.User.User;
import com.elearn.app.elearnapi.modules.User.UserService;
import com.elearn.app.elearnapi.modules.User.DTO.LoginResponse;
import com.elearn.app.elearnapi.modules.User.DTO.TokenResponse;
import com.elearn.app.elearnapi.modules.User.DTO.UserResponse;
import com.elearn.app.elearnapi.utilities.JWTUtilities;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/front/auth")
public class FrontAuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/sign-up")
    public UserResponse signUp(@RequestBody SignUpBody body) {
        User user = this.userService.createStudentUser(body.getName(), body.getEmail(), body.getPassword());
        UserResponse userResponse = this.userService.makeUserResponse(user);
        return userResponse;
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginBody body) {
        User user = this.userService.checkFindByEmail(body.getEmail());
        this.userService.checkIsInRole(user, UserRole.STUDENT);
        this.userService.checkPasswordIsCorrect(user, body.getPassword());
        LoginResponse loginResponse = this.userService.makeLoginResponse(user);
        return loginResponse;
    }

    @PostMapping("/refresh-token")
    public TokenResponse refreshToken(HttpServletRequest request) {
        String id = (String) request.getAttribute("id");
        User user = this.userService.checkFindById(id);
        String token = JWTUtilities.generateJwtToken(user);
        TokenResponse tokenResponse = this.userService.makeTokenResponse(token);
        return tokenResponse;
    }

}
