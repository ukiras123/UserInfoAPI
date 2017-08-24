package com.kiran.controller;

import com.kiran.controller.dto.UserInfoDTO;
import com.kiran.model.entity.UserInfoEntity;
import com.kiran.model.response.CreateResponse;
import com.kiran.model.response.ErrorResponse;
import com.kiran.model.response.ReadAllResponse;
import com.kiran.service.Exception.BusinessRulesViolationException;
import com.kiran.service.UserInfoService;
import com.kiran.translator.UserInfoTranslator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

/**
 * @author Kiran
 * @since 8/24/17
 */

@RestController
@RequestMapping("/user")
public class UserInfoController {

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private UserInfoTranslator userInfoTranslator;

    //Create new User Info
    @RequestMapping(method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public HttpEntity<?> createNewUser(@RequestBody UserInfoDTO userInfoDTO) {
        try {
            UserInfoEntity newUserInfoEntity =
                    userInfoService.createUserInfo(userInfoDTO);

            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setLocation(ServletUriComponentsBuilder.fromCurrentRequest().path("/{code}")
                    .buildAndExpand(newUserInfoEntity.getFirstName()).toUri());

            CreateResponse response = new CreateResponse(newUserInfoEntity.getFirstName());
            return new ResponseEntity<>(response, httpHeaders, HttpStatus.CREATED);
        } catch (BusinessRulesViolationException e) {
            ErrorResponse response = new ErrorResponse(e.getViolations());
            return new ResponseEntity<>(response, null, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("Please contact your administrator", null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Read user by First Name
    @RequestMapping(value = "/{firstName}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<?> readMarketingProgram(@PathVariable("firstName") String firstName) {
        Iterable<UserInfoEntity> userInfoEntityIterable = userInfoService.readUserByFirstName(firstName);
        List<UserInfoDTO> UserInfoEntityList =
                userInfoTranslator.entityListToDTOList(userInfoEntityIterable);
        ReadAllResponse response = new ReadAllResponse(UserInfoEntityList.size(), UserInfoEntityList);
        return new ResponseEntity<>(response, null, HttpStatus.OK);
    }

    //Read all users
    @RequestMapping(method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<?> readMarketingProgram() {
        Iterable<UserInfoEntity> userInfoEntityIterable = userInfoService.readAllUsers();
        List<UserInfoDTO> UserInfoEntityList =
                userInfoTranslator.entityListToDTOList(userInfoEntityIterable);
        ReadAllResponse response = new ReadAllResponse(UserInfoEntityList.size(), UserInfoEntityList);
        return new ResponseEntity<>(response, null, HttpStatus.OK);
    }


}
