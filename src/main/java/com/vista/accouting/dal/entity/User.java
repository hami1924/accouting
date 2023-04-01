package com.vista.accouting.dal.entity;

import lombok.*;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "USER")
public class User  extends Entity{
    @Indexed
    private String mobile;
    @Indexed
    private String firstName;
    @Indexed
    private String lastName;
    @Indexed
    private String imei;
}
