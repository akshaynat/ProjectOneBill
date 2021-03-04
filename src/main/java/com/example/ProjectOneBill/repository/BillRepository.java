package com.example.ProjectOneBill.repository;

import com.example.ProjectOneBill.entity.Bill;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

//import java.sql.Date;
import java.util.ArrayList;

@Repository
public interface BillRepository extends CrudRepository<Bill, Long> {

    @Query(value = "select * from bill where date between ?1 and ?2", nativeQuery = true)
    ArrayList<Bill> getBillListByDate(java.sql.Date fromDate, java.sql.Date toDate);
}
