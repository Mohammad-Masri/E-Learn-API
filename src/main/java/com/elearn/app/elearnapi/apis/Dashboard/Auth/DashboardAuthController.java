package com.elearn.app.elearnapi.apis.Dashboard.Auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.elearn.app.elearnapi.config.constants.UserRole;
import com.elearn.app.elearnapi.modules.User.User;
import com.elearn.app.elearnapi.modules.User.UserService;
import com.elearn.app.elearnapi.modules.User.DTO.LoginResponse;

@RestController
@RequestMapping("/dashboard/auth")
public class DashboardAuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginBody body) {
        User user = this.userService.checkFindByEmail(body.getEmail());
        this.userService.checkIsInRole(user, UserRole.ADMIN);
        this.userService.checkPasswordIsCorrect(user, body.getPassword());
        LoginResponse loginResponse = this.userService.makeLoginResponse(user);
        return loginResponse;
    }

}
