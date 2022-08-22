package com.example.elastic.product.vo;

import com.example.elastic.product.ProductDocument;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import java.util.Date;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@NoArgsConstructor
public class ProductVo {
    public String id;
    private Long categoryIdx;

    private Long accountIdx;

    private String name;

    private String description;

    private Long price;
    public Date createDate;
    public Date updateDate;

    public static ProductVo from(ProductDocument product){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(product, ProductVo.class);
    }
}
