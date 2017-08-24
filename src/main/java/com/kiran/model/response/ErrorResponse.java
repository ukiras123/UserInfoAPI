package com.kiran.model.response;

import com.kiran.service.validator.BusinessRuleViolation;

import java.util.Set;

/**
 * @author Kiran
 * @since 8/24/17
 */

public class ErrorResponse {

    private Set<BusinessRuleViolation> errors;
    public ErrorResponse(Set<BusinessRuleViolation> errors) {
        this.errors = errors;
    }

    public Set<BusinessRuleViolation> getErrors() {
        return errors;
    }

    public void setErrors(Set<BusinessRuleViolation> errors) {
        this.errors = errors;
    }
}
