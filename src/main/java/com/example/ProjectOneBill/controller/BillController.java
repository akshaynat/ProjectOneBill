package com.example.ProjectOneBill.controller;

import com.example.ProjectOneBill.dto.BillReponsePostDto;
import com.example.ProjectOneBill.dto.BillRequestDto;
import com.example.ProjectOneBill.dto.BillResponseDto;
import com.example.ProjectOneBill.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

//@CrossOrigin
@RestController
@RequestMapping("/customerbillingsystem")
public class BillController {

    @Autowired
    private BillService billService;

    @GetMapping( "/{username}/orders/{id}")
    public BillResponseDto getBillById(@PathVariable String username, @PathVariable ("id") Long bill_id) {
        return billService.getBillByid(username, bill_id);
    }

    @PostMapping( "/{username}")
    public BillReponsePostDto createBill(@PathVariable String username, @RequestBody List<BillRequestDto> billRequestDto) {
        System.out.println(billRequestDto);
        return billService.createBill(username, billRequestDto);
    }

    @GetMapping("/{username}/orderDetails")
    public List<BillResponseDto> getBillByCalendar(@PathVariable String username, @RequestParam Date fromDate, @RequestParam Date toDate) {
        return billService.getBillByCalendar(username, fromDate, toDate);
    }

    @GetMapping("/{username}")
    public List<BillResponseDto> getBillsByUsername(@PathVariable String username) {
        return billService.getBillsByUsername(username);
    }
}