package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.exception.ProductGroupNotFoundException;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.domain.ProductDto;
import com.kodilla.ecommercee.service.ProductDbService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductMapper {

    private ProductDbService productDbService;

    public ProductMapper(ProductDbService productDbService) {
        this.productDbService = productDbService;
    }

    public Product mapToProduct(final ProductDto productDto) throws ProductGroupNotFoundException {

        return new Product(
                productDto.getProductId(),
                productDto.getName(),
                productDto.getPrice(),
                productDto.getStock(),
                productDbService.getCards(productDto.getCardsId()),
                productDbService.getProductGroup(productDto.getId())
        );
    }

    public ProductDto mapToProductDto(final Product product) {

        return new ProductDto(
                product.getProductId(),
                product.getName(),
                product.getPrice(),
                product.getStock(),
                product.getCarts().stream().map(cart -> cart.getCartId()).collect(Collectors.toList()),
                product.getProductGroup().getId()
        );
    }

    public List<ProductDto> mapToProductDtoList (final List<Product>productList) {
        return productList.stream()
                .map(this::mapToProductDto)
                .collect(Collectors.toList());
    }

}
