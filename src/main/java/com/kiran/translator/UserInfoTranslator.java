package com.kiran.translator;

import com.kiran.controller.dto.UserInfoDTO;
import com.kiran.controller.dto.UserInfoDTO.UserInfoAttribute;
import com.kiran.model.entity.UserInfoEntity;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Kiran
 * @since 8/24/17
 */


@Component
public class UserInfoTranslator {

    public UserInfoEntity dtoToEntity (final UserInfoDTO userInfoDTO)
    {
        UserInfoEntity UserInfoEntity = new UserInfoEntity();

        for (UserInfoAttribute attribute : userInfoDTO.getAssignedAttributes()) {
            switch(attribute) {
                case FirstName:
                    if(UserInfoEntity.getFirstName() == null)
                        UserInfoEntity.setFirstName(userInfoDTO.getFirstName());
                    break;
                case LastName:
                    UserInfoEntity.setLastName(userInfoDTO.getLastName());
                    break;
                case Position:
                    UserInfoEntity.setPosition(userInfoDTO.getPosition());
                    break;
                case PhoneNo:
                    UserInfoEntity.setPhoneNo(userInfoDTO.getPhoneNo());
                    break;
                case OtherInfo:
                    UserInfoEntity.setOtherInfo(userInfoDTO.getOtherInfo());
                    break;
                default:
                    break;
            }
        }

        return UserInfoEntity;
    }

    public UserInfoDTO entityToDTO(UserInfoEntity UserInfoEntity) {
        UserInfoDTO userInfoDTO = new UserInfoDTO();
        userInfoDTO.setFirstName(UserInfoEntity.getFirstName());
        userInfoDTO.setLastName(UserInfoEntity.getLastName());
        userInfoDTO.setPosition(UserInfoEntity.getPosition());
        userInfoDTO.setPhoneNo(UserInfoEntity.getPhoneNo());
        userInfoDTO.setOtherInfo(UserInfoEntity.getOtherInfo());
        return userInfoDTO;
    }

    public List<UserInfoDTO> entityListToDTOList(Iterable<UserInfoEntity> iterable) {
        LinkedList list = new LinkedList();
        Iterator iter = iterable.iterator();
        while(iter.hasNext()) {
            list.add(this.entityToDTO((UserInfoEntity) iter.next()));
        }

        return list;
    }
}
