package com.example.elastic.product.repository;

import com.example.elastic.product.ProductDocument;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface ProductDocumentRepository extends ElasticsearchRepository<ProductDocument,Long> {
    List<ProductDocument> findByName(String name, Pageable pageable);
}
