package com.vista.accouting.dal.entity;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "USER")
public class User  extends Entity{

    private String mobile;

    private String firstName;

    private String lastName;
    private String imei;
}
