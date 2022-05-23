package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.ProductGroup;
import com.kodilla.ecommercee.domain.ProductGroupDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductGroupMapper {

    public ProductGroup mapToProductGroup(final ProductGroupDto productGroupDto) {
        ProductGroup productGroup = new ProductGroup();
        productGroup.setId(productGroup.getId());
        productGroup.setName(productGroupDto.getName());
        return productGroup;
    }

    public ProductGroupDto mapToProductGroupDto(final ProductGroup productGroup) {
        return new ProductGroupDto(productGroup.getId(), productGroup.getName());
    }

    public List<ProductGroupDto> mapToProductGroupsDto(final List<ProductGroup> productGroupList) {
        return productGroupList.stream()
                .map(this::mapToProductGroupDto)
                .collect(Collectors.toList());
    }
}
