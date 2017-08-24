package com.kiran.service.Exception;

import com.kiran.service.validator.BusinessRuleViolation;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Kiran
 * @since 8/24/17
 */
public class BusinessRulesViolationException extends RuntimeException{
    private static final long serialVersionUID = 1L;
    private Set<BusinessRuleViolation> violations = new HashSet();

    public BusinessRulesViolationException(Set<BusinessRuleViolation> violations) {
        if(violations != null && !violations.isEmpty()) {
            this.violations = violations;
        } else {
            throw new IllegalStateException("BusinessRulesViolationException must contain non-empty set of violations.");
        }
    }

    public Set<BusinessRuleViolation> getViolations() {
        return this.violations;
    }

    public int getViolationCount() {
        return this.violations.size();
    }
}
