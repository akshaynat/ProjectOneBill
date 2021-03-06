package com.example.ProjectOneBill.repository;

import com.example.ProjectOneBill.entity.Bill;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

//import java.sql.Date;
import java.util.ArrayList;
import java.util.Optional;

@Repository
public interface BillRepository extends CrudRepository<Bill, Long> {

    @Query(value = "select * from bill where username=?1 and date between ?2 and ?3", nativeQuery = true)
    ArrayList<Bill> getBillListByDate(String username, java.sql.Date fromDate, java.sql.Date toDate);

    @Query(value = "select * from bill where username=?1 and id=?2", nativeQuery = true)
    Optional<Bill> findBillByUsernameAndBillId(String username, Long bill_id);

    @Query(value = "select * from bill where username=?1", nativeQuery = true)
    ArrayList<Bill> getBillListByUsername(String username);
}
