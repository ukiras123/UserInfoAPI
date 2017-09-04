package com.kiran.service.integration;

import org.apache.tomcat.util.codec.binary.Base64;
import org.json.JSONObject;
import org.springframework.http.*;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

/**
 * @author Kiran
 * @since 8/31/17
 */

@Component
public class JiraAPI {

    public JSONObject getTicketDetail(String jiraTicket) throws InterruptedException {
        String URL = "https://jira.oceanx.com/rest/api/latest/issue/"+jiraTicket+".json";

        try {
            RestTemplate restTemplate = new RestTemplate();
            String plainCreds = "kgautam:Nepal123";
            byte[] plainCredsBytes = plainCreds.getBytes();
            byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
            String base64Creds = new String(base64CredsBytes);

            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", "Basic " + base64Creds);

            HttpEntity<String> request = new HttpEntity<String>(headers);
            ResponseEntity<String> response = restTemplate.exchange(URL, HttpMethod.GET, request, String.class);

            JSONObject jObject  = new JSONObject(response);
            String strBody =   jObject.getString("body");
            JSONObject jBody  = new JSONObject(strBody);
            return jBody;
        } catch (Exception ex) {
            System.out.println("** exception: " + ex.getMessage());
        }
        return null;
    }


    public String assignATicket(String jiraTicket, String asignee) throws InterruptedException {
        String URL = "https://jira.oceanx.com/rest/api/latest/issue/"+jiraTicket+"/assignee";
        try {
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            String plainCreds = "kgautam:Nepal123";
            byte[] plainCredsBytes = plainCreds.getBytes();
            byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
            String base64Creds = new String(base64CredsBytes);

            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", "Basic " + base64Creds);
            headers.setContentType(MediaType.APPLICATION_JSON);
            String input = "{\"name\":\"" + asignee + "\"}";
            HttpEntity<String> entity = new HttpEntity<String>(input, headers);
            ResponseEntity<String> response = restTemplate.exchange(URL, HttpMethod.PUT, entity, String.class);
            HttpStatus statusCode = response.getStatusCode();
            int code = statusCode.value();
            if (code == 204) {
                return "passed";
            } else {
                return "failed";
            }
        }catch(HttpClientErrorException ex)
        {
            System.out.println("** exception: " + ex.getMessage());
            if (ex.getStatusCode().value() == 400) {
                return "userIssue";
            } else if (ex.getStatusCode().value() == 404) {
            return "jiraTicket";
            } else {
                return "failed";
            }
        } catch (Exception ex) {
                return "failed";
        }
    }


}
