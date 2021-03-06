package com.example.ProjectOneBill.service;

import com.example.ProjectOneBill.dto.BillReponsePostDto;
import com.example.ProjectOneBill.dto.BillRequestDto;
import com.example.ProjectOneBill.dto.BillResponseDto;

import java.sql.Timestamp;
import java.sql.Date;
import java.util.List;

public interface BillService {
    BillResponseDto getBillByid(String username, Long bill_id);

    BillReponsePostDto createBill(String username, List<BillRequestDto> billRequestDto);

    List<BillResponseDto> getBillByCalendar(String username, Date fromDate, Date toDate);

    List<BillResponseDto> getBillsByUsername(String username);
}
