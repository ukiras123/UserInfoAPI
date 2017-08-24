package com.kiran.controller.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Kiran
 * @since 8/24/17
 */
public class UserInfoDTO {

    public static enum UserInfoAttribute {
        FirstName,
        LastName,
        Position,
        PhoneNo,
        OtherInfo
    }

    @JsonIgnore
    private final Set<UserInfoAttribute> assignedAttributes = new HashSet<>();

    private String firstName;
    private String lastName;
    private String position;
    private String phoneNo;
    private String otherInfo;

    public Set<UserInfoAttribute> getAssignedAttributes() {
        return assignedAttributes;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        assignedAttributes.add(UserInfoAttribute.FirstName);
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        assignedAttributes.add(UserInfoAttribute.LastName);
        this.lastName = lastName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        assignedAttributes.add(UserInfoAttribute.Position);
        this.position = position;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        assignedAttributes.add(UserInfoAttribute.PhoneNo);
        this.phoneNo = phoneNo;
    }

    public String getOtherInfo() {
        return otherInfo;
    }

    public void setOtherInfo(String otherInfo) {
        assignedAttributes.add(UserInfoAttribute.OtherInfo);
        this.otherInfo = otherInfo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserInfoDTO that = (UserInfoDTO) o;

        if (!firstName.equals(that.firstName)) return false;
        if (!lastName.equals(that.lastName)) return false;
        if (!position.equals(that.position)) return false;
        if (!phoneNo.equals(that.phoneNo)) return false;
        return otherInfo != null ? otherInfo.equals(that.otherInfo) : that.otherInfo == null;
    }

    @Override
    public int hashCode() {
        int result = firstName.hashCode();
        result = 31 * result + lastName.hashCode();
        result = 31 * result + position.hashCode();
        result = 31 * result + phoneNo.hashCode();
        result = 31 * result + (otherInfo != null ? otherInfo.hashCode() : 0);
        return result;
    }
}
