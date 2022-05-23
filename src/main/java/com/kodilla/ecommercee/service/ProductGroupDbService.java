package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.controller.exception.ProductGroupNotFoundException;
import com.kodilla.ecommercee.domain.ProductGroup;
import com.kodilla.ecommercee.repository.ProductGroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductGroupDbService {

    private ProductGroupRepository productGroupRepository;

    public List<ProductGroup> getAllProdctGroup() {
        return productGroupRepository.findAll();
    }

    public ProductGroup getProductGroup(final Long productGroupId) throws ProductGroupNotFoundException {
        return productGroupRepository.findById(productGroupId).orElseThrow(ProductGroupNotFoundException::new);
    }

    public void createProductGroup(final ProductGroup productGroup) {
        productGroupRepository.save(productGroup);
    }

    public String updateProductGroup(final ProductGroup productGroup) throws ProductGroupNotFoundException {
        productGroupRepository.findById(productGroup.getId()).orElseThrow(ProductGroupNotFoundException::new);
        productGroupRepository.save(productGroup);
        return "ProductGroup has been updated";
    }

}
