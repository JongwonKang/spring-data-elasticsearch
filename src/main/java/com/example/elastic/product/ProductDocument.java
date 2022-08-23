package com.example.elastic.product;

import lombok.*;
import org.springframework.data.elasticsearch.annotations.*;

import javax.persistence.*;
import java.util.Date;

import static org.springframework.data.elasticsearch.annotations.DateFormat.date_hour_minute_second_millis;
import static org.springframework.data.elasticsearch.annotations.DateFormat.epoch_millis;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Document(indexName = "product")
//@Mapping(mappingPath = "elastic/product-mapping.json")
//@Setting(settingPath = "elastic/product-setting.json")
public class ProductDocument {
    @Id
    private Long id;

    private Long categoryIdx;

    private Long accountIdx;

    private String name;

    private String description;

    private Long price;

    @Field(type = FieldType.Date, format = {date_hour_minute_second_millis, epoch_millis})
    private Date createDate;

    @Field(type = FieldType.Date, format = {date_hour_minute_second_millis, epoch_millis})
    private Date updateDate;

    public static ProductDocument from(Product product){
        return ProductDocument.builder()
                .id(product.getId())
                .name(product.getName())
                .categoryIdx(product.getCategoryIdx())
                .accountIdx(product.getAccountIdx())
                .description(product.getDescription())
                .price(product.getPrice())
                .createDate(product.getStartDate())
                .updateDate(product.getEndDate())
                .build();
    }
}
