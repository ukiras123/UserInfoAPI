package com.kiran.service.Utilities;

import org.apache.tomcat.util.codec.binary.Base64;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * @author Kiran
 * @since 8/31/17
 */

@Component
public class JiraAPI {

    public JSONObject getAPIResponse(String jiraTicket) throws InterruptedException {
        String URL = "https://jira.oceanx.com/rest/api/latest/issue/"+jiraTicket+".json";

        try {
            RestTemplate restTemplate = new RestTemplate();

            String plainCreds = "user:pass"; 
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
            System.out.println("** Exception: " + ex.getMessage());
        }
        return null;
    }


}
