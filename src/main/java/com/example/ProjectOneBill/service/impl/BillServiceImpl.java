package com.example.ProjectOneBill.service.impl;

import com.example.ProjectOneBill.client.ProductClient;
import com.example.ProjectOneBill.dto.*;
import com.example.ProjectOneBill.entity.Bill;
import com.example.ProjectOneBill.entity.Reference;
import com.example.ProjectOneBill.repository.BillRepository;
import com.example.ProjectOneBill.repository.ReferenceRepository;
import com.example.ProjectOneBill.service.BillService;
import org.apache.commons.lang.WordUtils;
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
    public BillResponseDto getBillByid( String username, Long bill_id) {
        Optional<Bill> billOptional = billRepository.findBillByUsernameAndBillId(username, bill_id);
        if (billOptional.isPresent()) {
            BillResponseDto billResponseDto = new BillResponseDto();

            BeanUtils.copyProperties(billOptional.get(), billResponseDto);
            billResponseDto.setBill_id(bill_id);
            username = WordUtils.capitalize(username);
            billResponseDto.setUsername(username);
            ArrayList<Long> productIds;
            productIds = referenceRepository.getAllProductIdByBillId(bill_id);

            ArrayList<ProductResponseDto> productResponseDtoArrayList = getProductResponseDtos(productIds);

            List<CustomResponseDto> customResponseDtos = new ArrayList<>();

            for (ProductResponseDto p : productResponseDtoArrayList) {
                CustomResponseDto c = new CustomResponseDto();
                c.setQuantity(referenceRepository.getQuantityByQuery(bill_id, p.getId()));
                BeanUtils.copyProperties(p, c);
                customResponseDtos.add(c);
            }

            billResponseDto.setCustomResponseDtoList(customResponseDtos);

            return billResponseDto;
        }
        return null;
    }

    @Override
    public BillReponsePostDto createBill(String username, List<BillRequestDto> billRequestDto) {
        Bill bill = new Bill();
        java.util.Date date = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        bill.setDate(sqlDate);
        bill.setUsername(username);

        billRepository.save(bill);
        List<Reference> referencesList = new ArrayList<>();
        for (BillRequestDto requestDto : billRequestDto) {
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
    public List<BillResponseDto> getBillByCalendar(String username, java.sql.Date fromDate, java.sql.Date toDate) {
        ArrayList<Bill> billList = billRepository.getBillListByDate(username, fromDate, toDate);

        List<BillResponseDto> billResponseDtoList = new ArrayList<>();
        for (Bill b : billList) {

            BillResponseDto billResponseDto = new BillResponseDto();
            BeanUtils.copyProperties(b, billResponseDto);
            billResponseDto.setBill_id(b.getId());

            billResponseDto.setUsername(username);

            ArrayList<Long> productIds;
            productIds = referenceRepository.getAllProductIdByBillId(b.getId());

            ArrayList<ProductResponseDto> productResponseDtoArrayList = getProductResponseDtos(productIds);

            List<CustomResponseDto> customResponseDtos = new ArrayList<>();

            for (ProductResponseDto p : productResponseDtoArrayList) {
                CustomResponseDto c = new CustomResponseDto();
                c.setQuantity(referenceRepository.getQuantityByQuery(b.getId(), p.getId()));
                BeanUtils.copyProperties(p, c);
                customResponseDtos.add(c);
            }

            billResponseDto.setCustomResponseDtoList(customResponseDtos);

            billResponseDtoList.add(billResponseDto);
        }
        return billResponseDtoList;
    }

    @Override
    public List<BillResponseDto> getBillsByUsername(String username) {
        ArrayList<Bill> billList = billRepository.getBillListByUsername(username);
        List<BillResponseDto> billResponseDtoList = new ArrayList<>();
        for (Bill b : billList) {

            BillResponseDto billResponseDto = new BillResponseDto();
            BeanUtils.copyProperties(b, billResponseDto);
            billResponseDto.setBill_id(b.getId());

            billResponseDto.setUsername(username);

            ArrayList<Long> productIds;
            productIds = referenceRepository.getAllProductIdByBillId(b.getId());

            ArrayList<ProductResponseDto> productResponseDtoArrayList = getProductResponseDtos(productIds);

            List<CustomResponseDto> customResponseDtos = new ArrayList<>();

            for (ProductResponseDto p : productResponseDtoArrayList) {
                CustomResponseDto c = new CustomResponseDto();
                c.setQuantity(referenceRepository.getQuantityByQuery(b.getId(), p.getId()));
                BeanUtils.copyProperties(p, c);
                customResponseDtos.add(c);
            }

            billResponseDto.setCustomResponseDtoList(customResponseDtos);

            billResponseDtoList.add(billResponseDto);
        }
        return billResponseDtoList;
    }


}
