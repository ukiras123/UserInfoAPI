package com.kiran.dao;

import com.kiran.model.entity.UserInfoEntity;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Kiran
 * @since 8/24/17
 */
public interface UserInfoDao extends CrudRepository<UserInfoEntity, Long> {

    Iterable<UserInfoEntity> findByFirstName(String firstName);
    UserInfoEntity findByPhoneNo(String phoneNo);

}