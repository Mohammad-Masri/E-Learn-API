package com.elearn.app.elearnapi.apis.Front.Auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.elearn.app.elearnapi.modules.User.User;
import com.elearn.app.elearnapi.modules.User.UserService;

@RestController
@RequestMapping("/front/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/sign-up")
    public User signUp(@RequestBody SignUpBody body) {
        return this.userService.createStudentUser(body.getName(), body.getEmail(), body.getPassword());
    }

    @PostMapping("/login")
    public boolean login(@RequestBody LoginBody body) {
        User user = this.userService.checkFindByEmail(body.getEmail());

        this.userService.checkPasswordIsCorrect(user, body.getPassword());

        return true;
    }

}
