package com.example.ProjectOneBill.service;

import com.example.ProjectOneBill.dto.BillReponsePostDto;
import com.example.ProjectOneBill.dto.BillRequestDto;
import com.example.ProjectOneBill.dto.BillResponseDto;

import java.sql.Timestamp;
import java.sql.Date;
import java.util.List;

public interface BillService {
    BillResponseDto getBillByid(Long bill_id);

    BillReponsePostDto createBill(List<BillRequestDto> billRequestDto);

    List<BillResponseDto> getBillByCalendar(Date fromDate, Date toDate);
}
