package com.kiran.service.validator;

import org.springframework.stereotype.Component;

/**
 * @author Kiran
 * @since 8/24/17
 */

@Component
public class BusinessRuleViolation {
    private String detail;

    public BusinessRuleViolation(){}
    public BusinessRuleViolation(String detail) {
        this.detail = detail;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BusinessRuleViolation that = (BusinessRuleViolation) o;

        return detail != null ? detail.equals(that.detail) : that.detail == null;
    }

    @Override
    public int hashCode() {
        return detail != null ? detail.hashCode() : 0;
    }
}
