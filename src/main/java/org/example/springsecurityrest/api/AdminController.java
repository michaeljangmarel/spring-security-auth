package org.example.springsecurityrest.api;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @GetMapping("/test")
//    @PreAuthorize("hasAnyAuthority('ADMIN')")
    private  String test () {
        return "test from admin permission";
    }

}
