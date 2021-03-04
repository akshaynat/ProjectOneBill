package com.example.ProjectOneBill.client;

import com.example.ProjectOneBill.dto.ProductResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@FeignClient(name = "product-client", url = "10.177.68.56:8080")
public interface ProductClient {
    /**
     * API Ref
     * [GET] 10.177.68.40:8983/solr/productCollection/browse?q=samsung
     */

    @RequestMapping(method = RequestMethod.GET, path = "/product/serviceCalls/")
    public ArrayList<ProductResponseDto> getProducts(@RequestParam ArrayList<Long> arrOfP);// from q = samsung



}
