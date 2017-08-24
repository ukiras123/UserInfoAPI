package com.kiran.model.entity;

import javax.persistence.*;

/**
 * @author Kiran
 * @since 8/24/17
 */
@Entity
@Table(name="users")
public class UserInfo {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="id")
    private long id;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name="position")
    private String position;

    @Column(name="phone_no", unique = true)
    private String phoneNo;

    @Column(name="other_info")
    private String otherInfo;

    public UserInfo() {

    }

    public UserInfo(String firstName, String lastName, String position, String phoneNo) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
        this.phoneNo = phoneNo;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getOtherInfo() {
        return otherInfo;
    }

    public void setOtherInfo(String otherInfo) {
        this.otherInfo = otherInfo;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserInfo userInfo = (UserInfo) o;

        if (!firstName.equals(userInfo.firstName)) return false;
        if (!lastName.equals(userInfo.lastName)) return false;
        if (!position.equals(userInfo.position)) return false;
        if (!phoneNo.equals(userInfo.phoneNo)) return false;
        return otherInfo != null ? otherInfo.equals(userInfo.otherInfo) : userInfo.otherInfo == null;
    }

    @Override
    public int hashCode() {
        int result = lastName.hashCode();
        result = 31 * result + position.hashCode();
        result = 31 * result + phoneNo.hashCode();
        result = 31 * result + (otherInfo != null ? otherInfo.hashCode() : 0);
        return result;
    }
}
