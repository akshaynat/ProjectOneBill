package com.example.ProjectOneBill.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Data
public class Bill {
    @Id
    @GenericGenerator(name = "bill_id_seq", strategy = "increment")
    @GeneratedValue(generator = "bill_id_seq", strategy = GenerationType.AUTO)
    private Long id;

    /*@JoinColumn(referencedColumnName = "id", name = "bill_id")
    @OneToMany
    private Customer customer;*/

    private Date date;

    private String username;


}
