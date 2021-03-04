package com.example.ProjectOneBill.service.impl;

import com.example.ProjectOneBill.client.ProductClient;
import com.example.ProjectOneBill.dto.BillReponsePostDto;
import com.example.ProjectOneBill.dto.BillRequestDto;
import com.example.ProjectOneBill.dto.BillResponseDto;
import com.example.ProjectOneBill.dto.ProductResponseDto;
import com.example.ProjectOneBill.entity.Bill;
import com.example.ProjectOneBill.entity.Reference;
import com.example.ProjectOneBill.repository.BillRepository;
import com.example.ProjectOneBill.repository.ReferenceRepository;
import com.example.ProjectOneBill.service.BillService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BillServiceImpl implements BillService {

    @Autowired
    BillRepository billRepository;

    @Autowired
    ReferenceRepository referenceRepository;

    @Autowired
    ProductClient productClient;

    public ArrayList<ProductResponseDto> getProductResponseDtos(ArrayList<Long> arr) {
        ArrayList<ProductResponseDto> productResponseDtolist;
        productResponseDtolist = productClient.getProducts(arr);
        return productResponseDtolist;
    }



    @Override
    public BillResponseDto getBillByid(Long bill_id) {
        Optional<Bill> billOptional = billRepository.findById(bill_id);
        if(billOptional.isPresent()) {
            BillResponseDto billResponseDto = new BillResponseDto();
            BeanUtils.copyProperties(billOptional.get(),billResponseDto);
            billResponseDto.setBill_id(bill_id);

            ArrayList<Long> productIds;
            productIds = referenceRepository.getAllProductIdByBillId(bill_id);
            System.out.println(productIds+" "+productIds.size());
            billResponseDto.setProductResponseDtoList(getProductResponseDtos(productIds));

            return billResponseDto;
        }
        return null;
    }

    @Override
    public BillReponsePostDto createBill(List<BillRequestDto> billRequestDto) {
        Bill bill = new Bill();
        java.util.Date date=new java.util.Date();
        java.sql.Date sqlDate=new java.sql.Date(date.getTime());
        bill.setDate(sqlDate);

        billRepository.save(bill);
        List<Reference> referencesList= new ArrayList<>();
        for(BillRequestDto requestDto : billRequestDto) {
            Reference r = new Reference();
            r.setBill_id(bill.getId());
            r.setQuantity(requestDto.getQuantity());
            r.setProduct_id(requestDto.getProduct_id());
            referencesList.add(r);

            referenceRepository.save(r);
        }

        BillReponsePostDto billReponsePostDto = new BillReponsePostDto();
        billReponsePostDto.setBill(bill);
        billReponsePostDto.setReferlist(referencesList);
        return billReponsePostDto;
    }

    @Override
    public List<BillResponseDto> getBillByCalendar(java.sql.Date fromDate, java.sql.Date toDate) {
        ArrayList<Bill> billList = billRepository.getBillListByDate(fromDate, toDate);

        List<BillResponseDto> billResponseDtoList = new ArrayList<>();
        for (Bill b : billList) {

            BillResponseDto billResponseDto = new BillResponseDto();
            BeanUtils.copyProperties(b, billResponseDto);
            billResponseDto.setBill_id(b.getId());

            ArrayList<Long> productIds;
            productIds = referenceRepository.getAllProductIdByBillId(b.getId());

            billResponseDto.setProductResponseDtoList(getProductResponseDtos(productIds));

            billResponseDtoList.add(billResponseDto);
        }
        return billResponseDtoList;
    }
}
