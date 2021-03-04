package com.example.ProjectOneBill.controller;

import com.example.ProjectOneBill.dto.BillReponsePostDto;
import com.example.ProjectOneBill.dto.BillRequestDto;
import com.example.ProjectOneBill.dto.BillResponseDto;
import com.example.ProjectOneBill.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/customerbillingsystem")
public class BillController {

    @Autowired
    private BillService billService;

    @GetMapping( "/orders/{id}")
    public BillResponseDto getBillById(@PathVariable ("id") Long bill_id) {
        return billService.getBillByid(bill_id);
    }

    @PostMapping
    public BillReponsePostDto createBill(@RequestBody List<BillRequestDto> billRequestDto) {
        return billService.createBill(billRequestDto);
    }

    @GetMapping("/orderDetails")
    public List<BillResponseDto> getBillByCalendar(@RequestParam Date fromDate, @RequestParam Date toDate) {
        return billService.getBillByCalendar(fromDate, toDate);
    }
}