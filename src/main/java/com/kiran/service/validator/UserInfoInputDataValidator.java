package com.kiran.service.validator;

import com.kiran.controller.dto.UserInfoDTO;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Kiran
 * @since 8/24/17
 */

@Component
public class UserInfoInputDataValidator {

    public Set<BusinessRuleViolation> validateAssignedFieldValues(UserInfoDTO userInfoDTO) {
        Set<BusinessRuleViolation> violations = new HashSet<>();

        for (UserInfoDTO.UserInfoAttribute assignedAttribute : userInfoDTO.getAssignedAttributes()) {
            switch (assignedAttribute) {
                case PhoneNo:
                    validatePhoneNumber(
                            userInfoDTO.getPhoneNo(),
                            assignedAttribute.name(),
                            violations);
                    break;
                case FirstName:
                    String first= "[A-Z][a-zA-Z]*";
                        if (!userInfoDTO.getFirstName().matches(first))
                            violations.add(new BusinessRuleViolation("Invalid First Name"));
                    break;
                case LastName:
                    String last= "[a-zA-z]+([ '-][a-zA-Z]+)*";
                    if (!userInfoDTO.getFirstName().matches(last))
                        violations.add(new BusinessRuleViolation("Invalid Last Name"));
                    break;
                case Position:
                    break;
                default:
                    break;
            }
        }
        return violations;
    }

    public boolean validatePhoneNumber(String phoneNo, String fieldName, Set<BusinessRuleViolation> violations) {
        String regexStr = "^(?:(?:\\+?1\\s*(?:[.-]\\s*)?)?(?:\\(\\s*([2-9]1[02-9]|[2-9][02-8]1|[2-9][02-8][02-9])\\s*\\)|([2-9]1[02-9]|[2-9][02-8]1|[2-9][02-8][02-9]))\\s*(?:[.-]\\s*)?)?([2-9]1[02-9]|[2-9][02-9]1|[2-9][02-9]{2})\\s*(?:[.-]\\s*)?([0-9]{4})(?:\\s*(?:#|x\\.?|ext\\.?|extension)\\s*(\\d+))?$";
        if(!phoneNo.matches(regexStr)) {
            violations.add(new BusinessRuleViolation("Invalid Phone Number"));
            return false;
        } else {
            return true;
        }
    }

}
