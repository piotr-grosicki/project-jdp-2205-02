package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.controller.exception.ProductGroupNotFoundException;
import com.kodilla.ecommercee.domain.ProductGroup;
import com.kodilla.ecommercee.domain.ProductGroupDto;
import com.kodilla.ecommercee.mapper.ProductGroupMapper;
import com.kodilla.ecommercee.service.ProductGroupDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("v1/groups")
@RequiredArgsConstructor
public class ProductGroupController {

    private ProductGroupDbService productGroupDbService;
    private ProductGroupMapper productGroupMapper;

    @GetMapping()
    public ResponseEntity<List<ProductGroupDto>> getGroupList() {
            return ResponseEntity.ok(productGroupMapper.mapToProductGroupsDto(productGroupDbService.getAllProdctGroup()));
    }

    @PostMapping()
    public ResponseEntity<Void> addGroup(@RequestBody ProductGroupDto productGroupDto) {
        ProductGroup productGroup = productGroupMapper.mapToProductGroup(productGroupDto);
        productGroupDbService.createProductGroup(productGroup);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "{groupId}")
    public ResponseEntity<ProductGroupDto> getGroup(@PathVariable Long groupId) {
        try {
            return ResponseEntity.ok(productGroupMapper.mapToProductGroupDto(productGroupDbService.getProductGroup(groupId)));
        } catch (ProductGroupNotFoundException e) {
            return ResponseEntity.badRequest().body(new ProductGroupDto(0L, "There is no user with id: " + groupId));
        }
    }

    @PutMapping
    public ResponseEntity<String> updateGroup(@RequestBody ProductGroupDto productGroupDto) {
        try {
            return ResponseEntity.ok(productGroupDbService.updateProductGroup(productGroupMapper.mapToProductGroup(productGroupDto)));
        } catch (ProductGroupNotFoundException e) {
            return ResponseEntity.badRequest().body("There is no user with id: " + productGroupDto.getProductGroupId());
        }
    }
}
