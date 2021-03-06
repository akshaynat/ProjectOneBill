package com.example.ProjectOneBill.dto;

import lombok.Data;

import java.util.List;


@Data
public class CustomResponseDto {

    private Long id;
    private String product_name;
    private double price;
    private Long quantity;
}
