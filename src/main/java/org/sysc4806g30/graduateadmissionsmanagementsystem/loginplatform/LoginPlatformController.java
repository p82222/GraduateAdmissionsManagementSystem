package org.sysc4806g30.graduateadmissionsmanagementsystem.loginplatform;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.sysc4806g30.graduateadmissionsmanagementsystem.users.model.User;

@Controller
@RequestMapping("/login")
public class LoginPlatformController {

    @Autowired
    private LoginPlatform loginPlatform;

    @GetMapping
    public String showLoginPage() {
        return "login";
    }

    @PostMapping(consumes = "application/json")
    @ResponseBody
    public String login(@RequestBody LoginRequest loginRequest) {
        User user = loginPlatform.authenticate(loginRequest.getUsername(), loginRequest.getPassword());

        if (user != null) {
            String userType = user.getClass().getSimpleName().toLowerCase();
            long userUID = user.getUserUID();
            return "/" + userType + "/" + userUID;
        } else {
            return null;
        }
    }
}
