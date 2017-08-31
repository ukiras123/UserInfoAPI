package com.kiran.model.response;

/**
 * @author Kiran
 * @since 8/29/17
 */
public class SlackResponse {

    private String text;

    private String response_type;

    public SlackResponse(String text) {
        this.text = text;
        this.response_type = "in_channel";
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getResponse_type() {
        return response_type;
    }

    public void setResponse_type(String response_type) {
        this.response_type = response_type;
    }
}
