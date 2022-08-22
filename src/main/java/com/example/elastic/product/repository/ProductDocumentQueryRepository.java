package com.example.elastic.product.repository;

import com.example.elastic.product.ProductDocument;
import com.example.elastic.product.param.ProductListParam;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class ProductDocumentQueryRepository {
    private final ElasticsearchOperations operations;

    public List<ProductDocument> findByCondition(ProductListParam searchCondition, Pageable pageable) {
        CriteriaQuery query = createConditionCriteriaQuery(searchCondition).setPageable(pageable);

        SearchHits<ProductDocument> search = operations.search(query, ProductDocument.class);
        return search.stream()
                .map(SearchHit::getContent)
                .collect(Collectors.toList());
    }

    private CriteriaQuery createConditionCriteriaQuery(ProductListParam searchCondition) {
        CriteriaQuery query = new CriteriaQuery(new Criteria());

        if (searchCondition.getSearchType() != null) {
            if (searchCondition.getSearchValue() != null) {
                query.addCriteria(Criteria.where("id").is(searchCondition.getSearchValue()));
            }
        }

        return query;
    }
}
