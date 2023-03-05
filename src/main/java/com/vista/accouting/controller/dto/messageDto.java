package com.vista.accouting.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class messageDto {

    private String mobile;

    private String imei;

    private String id;

    private LocalDateTime from;

    private LocalDateTime to;

    private Integer count;

    private String numberAlert;

    private List<String> data;
}
