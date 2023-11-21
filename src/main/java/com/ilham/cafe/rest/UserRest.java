package com.ilham.cafe.rest;

import com.ilham.cafe.dto.AuthResponseDTO;
import com.ilham.cafe.dto.LoginResponseDTO;
import com.ilham.cafe.wrapper.UserWrapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@RequestMapping(path = "/user")
public interface UserRest {
    @PostMapping(path = "/signup")
    public ResponseEntity<AuthResponseDTO> signUp(@RequestBody(required = true) Map<String, String> requestMap);

    @PostMapping(path = "/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody(required = true) Map<String, String> requestMap);

    @GetMapping(path = "/get")
    public ResponseEntity<List<UserWrapper>> getAllUser();

    @PostMapping(path = "/update")
    public ResponseEntity<AuthResponseDTO> update(@RequestBody(required = true) Map<String, String> requestMap);

    @GetMapping(path = "checkToken")
    public ResponseEntity<AuthResponseDTO> checkToken();

    @PostMapping(path = "changePassword")
    ResponseEntity<AuthResponseDTO> changePassword(@RequestBody Map<String, String> requestMap);

    @PostMapping(path = "/forgotPassword")
    ResponseEntity<AuthResponseDTO> forgotPassword(@RequestBody Map<String, String> requestMap);
}
