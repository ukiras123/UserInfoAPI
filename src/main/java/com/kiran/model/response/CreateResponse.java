package com.kiran.model.response;

import java.time.LocalDateTime;

/**
 * @author Kiran
 * @since 8/24/17
 */
public class CreateResponse {

    private String firstName;
    private String dataTime;
    public CreateResponse(String firstName) {
        this.firstName = firstName;
        this.dataTime = LocalDateTime.now().toString();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
}
