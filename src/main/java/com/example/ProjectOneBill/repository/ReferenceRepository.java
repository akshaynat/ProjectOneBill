package com.example.ProjectOneBill.repository;

import com.example.ProjectOneBill.entity.Reference;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface ReferenceRepository extends CrudRepository<Reference, Long> {

    @Query(value = "select product_id from reference where bill_id=?1 ", nativeQuery = true)
    ArrayList<Long> getAllProductIdByBillId(Long bill_id);

    @Query(value = "select quantity from reference where bill_id=?1 and product_id=?2", nativeQuery = true)
    Long getQuantityByQuery(Long bill_id, Long product_id);

}
