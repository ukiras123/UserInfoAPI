package com.kiran.model.response;

import com.kiran.model.entity.UserInfo;

import java.util.List;

/**
 * @author Kiran
 * @since 8/24/17
 */
public class ReadAllResponse{

    private Integer totalCount;
    private List<UserInfo> results;

    public ReadAllResponse(Integer totalCount, List<UserInfo> results) {
        this.totalCount = totalCount;
        this.results = results;
    }

    public List<UserInfo> getResults() {
        return this.results;
    }

    public void setResults(List<UserInfo> results) {
        this.results = results;
    }

    public void add(UserInfo dto) {
        if(this.results != null) {
            this.results.add(dto);
        }
    }

    public Integer getTotalCount() {
        return this.totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

}
