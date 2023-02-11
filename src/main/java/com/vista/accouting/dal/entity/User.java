package com.vista.accouting.dal.entity;

import lombok.Data;
import lombok.NonNull;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "USER")
public class User  extends Entity{

    private String mobile;

    private String firstName;

    private String lastName;
    private String imei;
}
