package com.kiran.controller;

import com.kiran.controller.dto.UserInfoDTO;
import com.kiran.model.entity.UserInfoEntity;
import com.kiran.model.response.CreateResponse;
import com.kiran.model.response.ErrorResponse;
import com.kiran.model.response.ReadAllResponse;
import com.kiran.model.response.SlackResponse;
import com.kiran.service.Exception.BusinessRulesViolationException;
import com.kiran.service.SlackService;
import com.kiran.service.UserInfoService;
import com.kiran.service.Utilities.Utilities;
import com.kiran.translator.UserInfoTranslator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.HashMap;
import java.util.List;

/**
 * @author Kiran
 * @since 8/24/17
 */

@RestController
@RequestMapping("/users")
public class UserInfoController {

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private Utilities utilities;

    @Autowired
    private UserInfoTranslator userInfoTranslator;

    @Autowired
    private SlackService slackService;

    //Slack================================

    @RequestMapping(value = "/slack",method = RequestMethod.POST, consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public HttpEntity<?> getText(@RequestBody MultiValueMap<String, String > formVars ) {
        try {
            String user_name = utilities.trimString(formVars.get("user_name").toString(),1);
            String text = utilities.trimString(formVars.get("text").toString(),1);
            if (!text.isEmpty())
            {
                String jiraTicket = slackService.getJiraCode(text);
                if (jiraTicket == null) {
                    SlackResponse response = new SlackResponse("Please input a valid Jira Ticket");
                    return new ResponseEntity<>(response, null, HttpStatus.OK);
                }
                HashMap<String, String> jiraMap;
                jiraMap = slackService.getJiraResponse(jiraTicket);
                SlackResponse response = new SlackResponse("*Summary* : "+jiraMap.get("summary")+"\n*Assignee* : "+jiraMap.get("assignee")+"\n*Status* : "+jiraMap.get("status"));
                return new ResponseEntity<>(response, null, HttpStatus.OK);
            } else {
                SlackResponse response = new SlackResponse("Welcome, " + user_name.substring(0, 1).toUpperCase() + user_name.substring(1) + ". You can now look for Jira Ticket Info.");
                return new ResponseEntity<>(response, null, HttpStatus.OK);
            }
        } catch (BusinessRulesViolationException e) {
            SlackResponse response = new SlackResponse("Something went wrong. Please try again");
            return new ResponseEntity<>(response, null, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Please contact your administrator", null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

















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
