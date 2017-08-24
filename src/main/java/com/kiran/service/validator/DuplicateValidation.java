package com.kiran.service.validator;

import com.kiran.controller.dto.UserInfoDTO;
import com.kiran.dao.UserInfoDao;
import com.kiran.model.entity.UserInfoEntity;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Kiran
 * @since 8/24/17
 */

@Component
public class DuplicateValidation {



    public Set<BusinessRuleViolation> validateAssignedFieldValues(UserInfoDTO userInfoDTO, UserInfoDao userInfoDao) {
        Set<BusinessRuleViolation> violations = new HashSet<>();

        for (UserInfoDTO.UserInfoAttribute assignedAttribute : userInfoDTO.getAssignedAttributes()) {
            switch (assignedAttribute) {
                case PhoneNo:
                    validateDuplicatePhoneNumber(
                            userInfoDTO.getPhoneNo(),
                            assignedAttribute.name(),
                            userInfoDao,
                            violations);
                    break;
                default:
                    break;
            }
        }
        return violations;
    }

    private boolean validateDuplicatePhoneNumber(String phoneNo, String name, UserInfoDao userInfoDao, Set<BusinessRuleViolation> violations) {
        UserInfoEntity user = userInfoDao.findByPhoneNo(phoneNo);
        if (user == null) {
            return true;
        } else {
          violations.add(new BusinessRuleViolation("Duplicate Phone Number"));
            return false;
        }
    }


}

