package com.vista.accouting.service.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TagDefaultPageModel {

    public TagDefaultPageModel(String _id, Integer count) {
        this._id = _id;
        this.count = count;
    }

    @JsonProperty(value = "tag")
    private String _id;

    private Integer count;

    private Double percent;
}
