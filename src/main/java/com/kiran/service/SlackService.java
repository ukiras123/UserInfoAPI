package com.kiran.service;

import com.kiran.service.Exception.BusinessRulesViolationException;
import com.kiran.service.Utilities.JiraAPI;
import com.kiran.service.validator.BusinessRuleViolation;
import com.kiran.service.validator.UserInfoInputDataValidator;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * @author Kiran
 * @since 8/31/17
 */
@Component
public class SlackService {

    @Autowired
    private JiraAPI jiraAPI;

    @Autowired
    private UserInfoInputDataValidator inputDataValidator;

    public HashMap  getJiraResponse(String jiraTicket)
    {
        HashMap<String, String> hmap = new HashMap<String, String>();
        try {
            JSONObject k = jiraAPI.getTicketDetail(jiraTicket);
            String summary = k.getJSONObject("fields").getString("summary");
            String asignee = "N/A";
            if (!k.getJSONObject("fields").get("assignee").toString().equals("null")) {
                asignee  = k.getJSONObject("fields").getJSONObject("assignee").getString("name");
            }
            String status = k.getJSONObject("fields").getJSONObject("status").getString("name");
            hmap.put("summary", summary);
            hmap.put("assignee", asignee);
            hmap.put("status", status);
        } catch (BusinessRulesViolationException b) {
            throw b;
        }catch (Exception ex) {
            Set<BusinessRuleViolation> violations = new HashSet<>();
            violations.add(new BusinessRuleViolation("Internal Server Error. Please try again."));
            throw new BusinessRulesViolationException(violations);
        }
        return hmap;
    }

    public String getJiraCode(String str) {
        String part[] = str.split(" ");
        LinkedList<String> mayBeJira = new LinkedList<>();
        String jiraTicket = null;
        for (String st : part) {
            if (st.length()==9 || st.length()==10 || st.length()==11)
            {
                mayBeJira.add(st);
            }
        }
        for (int i = 0; i < mayBeJira.size(); i++) {
            if (mayBeJira.get(i).contains("-") && mayBeJira.get(i).length() == 9) {
                jiraTicket = mayBeJira.get(i);
                break;
            } else if (mayBeJira.get(i).contains("-") && mayBeJira.get(i).length() == 10) {
                String st = mayBeJira.get(i);
                jiraTicket = st.substring(0, st.length() - 1);
                break;
            } else if (mayBeJira.get(i).contains("-") && mayBeJira.get(i).length() == 11) {
                String st = mayBeJira.get(i);
                jiraTicket = st.substring(0, st.length() - 2);
                break;
            }
        }
        return jiraTicket;
    }

    public String getAssigneeName(String str) {
        String part[] = str.split(" ");
        String mayBeUser = null;
        String asignee = null;
        for (String st : part) {
            if (st.contains("user"))
            {
                mayBeUser = st;
                break;
            }
        }
        if (mayBeUser != null) {
            if (mayBeUser.contains(":")) {
                asignee = mayBeUser.substring(mayBeUser.indexOf(":") + 1);
            }
        }
        return asignee;
    }
}
