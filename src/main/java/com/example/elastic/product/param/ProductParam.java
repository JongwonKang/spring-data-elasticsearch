package com.example.elastic.product.param;

import com.example.elastic.product.Product;
import com.example.elastic.product.ProductDocument;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ProductParam {
    public Long price;
    public String description;
    public Long categoryIdx;
    private Long accountIdx;
    private String name;

    public ProductDocument toProductDocument(){
        return ProductDocument.builder()
                .accountIdx(this.accountIdx)
                .name(this.name)
                .price(this.price)
                .description(this.description)
                .categoryIdx(this.categoryIdx)
                .createDate(new Date())
                .updateDate(new Date())
                .build();
    }

    public Product toProduct(){
        return Product.builder()
                .accountIdx(this.accountIdx)
                .name(this.name)
                .price(this.price)
                .description(this.description)
                .categoryIdx(this.categoryIdx).build();
    }
}
