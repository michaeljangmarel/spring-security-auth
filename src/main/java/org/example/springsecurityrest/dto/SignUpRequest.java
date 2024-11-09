package org.example.springsecurityrest.dto;


public record SignUpRequest(String username ,
         String password ,
           String email ) {
}
