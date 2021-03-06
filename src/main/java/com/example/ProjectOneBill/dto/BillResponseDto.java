package com.example.ProjectOneBill.dto;

import lombok.Data;

import java.sql.Date;
import java.util.List;

@Data
public class BillResponseDto {

    private Long bill_id;

    private Date date;

    private String username;

//    private Customer customer;

    private List<CustomResponseDto> customResponseDtoList;
}
