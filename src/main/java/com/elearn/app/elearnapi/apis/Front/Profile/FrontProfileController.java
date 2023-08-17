package com.elearn.app.elearnapi.apis.Front.Profile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.elearn.app.elearnapi.modules.User.User;
import com.elearn.app.elearnapi.modules.User.UserService;
import com.elearn.app.elearnapi.modules.User.DTO.UserResponse;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/front/profile")
public class FrontProfileController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/me")
    public UserResponse getMyProfile(HttpServletRequest request) {
        String id = (String) request.getAttribute("id");
        User user = this.userService.checkFindById(id);
        UserResponse userResponse = this.userService.makeUserResponse(user);
        return userResponse;
    }

}
