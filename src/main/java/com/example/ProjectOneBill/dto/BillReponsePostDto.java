package com.example.ProjectOneBill.dto;

import com.example.ProjectOneBill.entity.Bill;
import com.example.ProjectOneBill.entity.Reference;
import lombok.Data;

import java.util.List;

@Data
public class BillReponsePostDto {

    private Bill bill;

    private List<Reference> referlist;
}
