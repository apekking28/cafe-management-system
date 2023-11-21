package com.ilham.cafe.service;

import com.ilham.cafe.dto.AuthResponseDTO;
import com.ilham.cafe.dto.LoginResponseDTO;
import com.ilham.cafe.wrapper.UserWrapper;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface UserService {

    ResponseEntity<AuthResponseDTO> signUp(Map<String, String> requestMap);

    ResponseEntity<LoginResponseDTO> login(Map<String, String> requestMap);

    ResponseEntity<List<UserWrapper>> getAllUser();

    ResponseEntity<AuthResponseDTO> update(Map<String, String> requestMap);

    ResponseEntity<AuthResponseDTO> checkToken();

    ResponseEntity<AuthResponseDTO> changePassword(Map<String, String> requestMap);

    ResponseEntity<AuthResponseDTO> forgotPassword(Map<String, String> requestMap);
}
