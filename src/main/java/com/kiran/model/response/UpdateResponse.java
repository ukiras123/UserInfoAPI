package com.kiran.model.response;

import java.time.LocalDateTime;

/**
 * @author Kiran
 * @since 8/24/17
 */
public class UpdateResponse {
    private String dataTime;
    public UpdateResponse(String firstName) {
        this.dataTime = LocalDateTime.now().toString();
    }
}
