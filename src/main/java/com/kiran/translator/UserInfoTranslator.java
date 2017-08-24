package com.kiran.translator;

import com.kiran.controller.dto.UserInfoDTO;
import com.kiran.controller.dto.UserInfoDTO.UserInfoAttribute;
import com.kiran.model.entity.UserInfo;
/**
 * @author Kiran
 * @since 8/24/17
 */
public class UserInfoTranslator {

    public UserInfo dtoToEntity (final UserInfo original, final UserInfoDTO userInfoDTO)
    {
        UserInfo userInfo = (original == null) ? new UserInfo() : original;

        for (UserInfoAttribute attribute : userInfoDTO.getAssignedAttributes()) {
            switch(attribute) {
                case FirstName:
                    if(userInfo.getFirstName() == null)
                        userInfo.setFirstName(userInfoDTO.getFirstName());
                    break;
                case LastName:
                    userInfo.setLastName(userInfoDTO.getLastName());
                    break;
                case Position:
                    userInfo.setPosition(userInfoDTO.getPosition());
                    break;
                case PhoneNo:
                    userInfo.setPhoneNo(userInfoDTO.getPhoneNo());
                    break;
                case OtherInfo:
                    userInfo.setOtherInfo(userInfoDTO.getOtherInfo());
                    break;
                default:
                    break;
            }
        }

        return userInfo;
    }

    public UserInfoDTO entityToDTO(UserInfo userInfo) {
        UserInfoDTO userInfoDTO = new UserInfoDTO();
        userInfoDTO.setFirstName(userInfo.getFirstName());
        userInfoDTO.setLastName(userInfo.getLastName());
        userInfoDTO.setPosition(userInfo.getPosition());
        userInfoDTO.setPhoneNo(userInfo.getPhoneNo());
        userInfoDTO.setOtherInfo(userInfo.getOtherInfo());
        return userInfoDTO;
    }
}
