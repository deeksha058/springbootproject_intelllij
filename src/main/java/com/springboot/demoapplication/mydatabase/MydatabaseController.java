package com.springboot.demoapplication.mydatabase;


import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.Registration;

@RestController
@RequestMapping(path = "api/v1/mydatabase")
@AllArgsConstructor
public class MydatabaseController {

    private RegistrationService registrationService;

    @PostMapping
    public String register(@RequestBody RegistrationRequest request){
        return registrationService.register(request);
    }
    @GetMapping(path = "confirm")
    public String confirm(@RequestParam("token") String token){
        return registrationService.confirmToken(token);
    }
}
