package com.ilham.cafe.restImpl;

import com.ilham.cafe.constants.CafeConstants;
import com.ilham.cafe.dto.AuthResponseDTO;
import com.ilham.cafe.dto.LoginResponseDTO;
import com.ilham.cafe.rest.UserRest;
import com.ilham.cafe.service.UserService;
import com.ilham.cafe.wrapper.UserWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class UserRestImpl implements UserRest {

    @Autowired
    UserService userService;

    @Override
    public ResponseEntity<AuthResponseDTO> signUp(Map<String, String> requestMap) {
        try {
            return userService.signUp(requestMap);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new AuthResponseDTO(CafeConstants.SOMETHING_WENT_WRONG), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<LoginResponseDTO> login(Map<String, String> requestMap) {
        try {
            return userService.login(requestMap);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new LoginResponseDTO(CafeConstants.SOMETHING_WENT_WRONG, null), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<List<UserWrapper>> getAllUser() {
        try {
            return userService.getAllUser();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<List<UserWrapper>>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<AuthResponseDTO> update(Map<String, String> requestMap) {
        try {
            return userService.update(requestMap);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new AuthResponseDTO(CafeConstants.SOMETHING_WENT_WRONG), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<AuthResponseDTO> checkToken() {
        try {
            return userService.checkToken();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new AuthResponseDTO(CafeConstants.SOMETHING_WENT_WRONG), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<AuthResponseDTO> changePassword(Map<String, String> requestMap) {
        try {
            return userService.changePassword(requestMap);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new AuthResponseDTO(CafeConstants.SOMETHING_WENT_WRONG), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<AuthResponseDTO> forgotPassword(Map<String, String> requestMap) {
        try {
            return userService.forgotPassword(requestMap);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new AuthResponseDTO(CafeConstants.SOMETHING_WENT_WRONG), HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
