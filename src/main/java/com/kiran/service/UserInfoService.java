package com.kiran.service;

import com.kiran.controller.dto.UserInfoDTO;
import com.kiran.dao.UserInfoDao;
import com.kiran.model.entity.UserInfoEntity;
import com.kiran.service.exception.BusinessRulesViolationException;
import com.kiran.service.exception.NotFoundException;
import com.kiran.service.validator.BusinessRuleViolation;
import com.kiran.service.validator.DuplicateValidation;
import com.kiran.service.validator.UserInfoInputDataValidator;
import com.kiran.translator.UserInfoTranslator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author Kiran
 * @since 8/24/17
 */
@Component
public class UserInfoService {

    @Autowired
    private UserInfoDao userInfoDao;

    @Autowired
    private UserInfoTranslator userInfoTranslator;

    @Autowired
    private UserInfoInputDataValidator inputDataValidator;

    @Autowired
    private DuplicateValidation duplicateValidation;

    public UserInfoEntity createUserInfo(UserInfoDTO userInfoDTO) {

        // validate the input fields for create
        Set<BusinessRuleViolation> violations =
                inputDataValidator.validateAssignedFieldValues(userInfoDTO);
        if (!violations.isEmpty()) {
            throw new BusinessRulesViolationException(violations);
        }

        violations =
                duplicateValidation.validateAssignedFieldValues(userInfoDTO, userInfoDao);
        if (!violations.isEmpty()) {
            throw new BusinessRulesViolationException(violations);
        }

        UserInfoEntity newUserInfo = userInfoTranslator.dtoToEntity(userInfoDTO);

        return userInfoDao.save(newUserInfo);
    }

    public List<UserInfoEntity> readAllUsers() {
        List<UserInfoEntity> list = new ArrayList<UserInfoEntity>();
        for (UserInfoEntity item :  userInfoDao.findAll()) {
            list.add(item);
        }
        return list;
    }

    public Iterable<UserInfoEntity> readUserByFirstName(final String firstName) {
        return userInfoDao.findByFirstName(firstName);
    }

    public UserInfoEntity readUserByPhoneNumber(final String phoneNumber) {
        return getUserInfoByPhoneNo(phoneNumber);
    }


    private UserInfoEntity getUserInfoByPhoneNo(final String identifier) {
            UserInfoEntity userInfoEntity = userInfoDao.findByPhoneNo(identifier);
            if (userInfoEntity == null) {
                throw new NotFoundException("Person Not Found");
            }
            return userInfoEntity;
    }


}
