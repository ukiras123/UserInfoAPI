package com.kiran.dao;

import com.kiran.model.entity.UserInfo;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Kiran
 * @since 8/24/17
 */
public interface UserInfoDao extends CrudRepository<UserInfoDao, Long> {

    UserInfo findByFirstName(String firstName);
    UserInfo findByPhoneNo(String phoneNo);

}