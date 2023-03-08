package com.vista.accouting.service.models;

import com.vista.accouting.dal.entity.User;
import com.vista.accouting.enums.BanksEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message {
    private String mobile;

    private String imei;

    private User user;

    private LocalDateTime from;

    private LocalDateTime to;

    private Integer count;

    private BanksEnum banksEnum;

    private String numberAlert;

    private List<String> data;
}
