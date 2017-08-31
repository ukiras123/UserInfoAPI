package com.kiran.controller;

import com.kiran.model.response.SlackResponse;
import com.kiran.service.Exception.BusinessRulesViolationException;
import com.kiran.service.SlackService;
import com.kiran.service.Utilities.Utilities;
import com.kiran.translator.UserInfoTranslator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * @author Kiran
 * @since 8/24/17
 */

@RestController
@RequestMapping("/slack")
public class SlackController {

    @Autowired
    private Utilities utilities;

    @Autowired
    private UserInfoTranslator userInfoTranslator;

    @Autowired
    private SlackService slackService;

    //Slack================================

    @RequestMapping(value = "/jira",method = RequestMethod.POST, consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE},
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


}
