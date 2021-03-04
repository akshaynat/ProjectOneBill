package com.example.ProjectOneBill.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class Reference {

    @Id
    @GenericGenerator(name = "refer_id_seq", strategy = "increment")
    @GeneratedValue(generator = "refer_id_seq", strategy = GenerationType.AUTO)
    private Long refer_id;

    private Long bill_id;

    private Long product_id;

    private Long quantity;
}
