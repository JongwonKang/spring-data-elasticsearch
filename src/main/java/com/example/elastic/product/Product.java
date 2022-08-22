package com.example.elastic.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "product")
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long categoryIdx;

    @Column(name="account_idx")
    private Long accountIdx;

    @Column(name = "\"name\"")
    private String name;

    @Column
    private String description;

    @Column(name="price")
    private Long price;

    @CreationTimestamp
    @Column(updatable = false)
    private Date startDate;

    @UpdateTimestamp
    private Date endDate;

}