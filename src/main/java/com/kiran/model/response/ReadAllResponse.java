package com.kiran.model.response;

import com.kiran.controller.dto.UserInfoDTO;

import java.util.List;

/**
 * @author Kiran
 * @since 8/24/17
 */
public class ReadAllResponse{

    private Integer totalCount;
    private List<UserInfoDTO> results;

    public ReadAllResponse(Integer totalCount, List<UserInfoDTO> results) {
        this.totalCount = totalCount;
        this.results = results;
    }

    public List<UserInfoDTO> getResults() {
        return this.results;
    }

    public void setResults(List<UserInfoDTO> results) {
        this.results = results;
    }

    public void add(UserInfoDTO dto) {
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
