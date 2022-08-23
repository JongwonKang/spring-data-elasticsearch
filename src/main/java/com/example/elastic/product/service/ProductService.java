package com.example.elastic.product.service;

import com.example.elastic.product.ProductDocument;
import com.example.elastic.product.param.ProductListParam;
import com.example.elastic.product.param.ProductParam;
import com.example.elastic.product.repository.ProductDocumentQueryRepository;
import com.example.elastic.product.repository.ProductDocumentRepository;
import com.example.elastic.product.repository.ProductRepository;
import com.example.elastic.product.vo.ProductVo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductDocumentRepository productDocumentRepository;
    private final ProductDocumentQueryRepository productDocumentQueryRepository;

    @Transactional
    public void createProductDocument(){
        List<ProductDocument> productDocumentList = productRepository.findAll()
                .stream().map(ProductDocument::from).collect(Collectors.toList());
            productDocumentRepository.saveAll(productDocumentList);
    }

    @Transactional
    public void saveAllProductDocuments() {
        List<ProductDocument> productDocumentList
                = productRepository.findAll().stream().map(ProductDocument::from).collect(Collectors.toList());
        productDocumentRepository.saveAll(productDocumentList);
    }

    public List<ProductVo> findByName(String name, Pageable pageable) {
        return productDocumentRepository.findByName(name, pageable)
                .stream()
                .map(ProductVo::from)
                .collect(Collectors.toList());
    }
    @Transactional
    public void createProduct(ProductParam productParam){
        productRepository.save(productParam.toProduct());
    }

    @Transactional
    public List<ProductVo> getProductList(ProductListParam searchCondition, Pageable pageable){
        return productDocumentQueryRepository.findByCondition(searchCondition, pageable)
                .stream()
                .map(ProductVo::from)
                .collect(Collectors.toList());
    }

}
