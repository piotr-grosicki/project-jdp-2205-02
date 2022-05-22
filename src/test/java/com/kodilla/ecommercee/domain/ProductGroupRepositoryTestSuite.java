package com.kodilla.ecommercee.domain;

import com.kodilla.ecommercee.repository.ProductGroupRepository;
import com.kodilla.ecommercee.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class ProductGroupRepositoryTestSuite {

    @Autowired
    private ProductGroupRepository productGroupRepository;

    @Autowired
    private ProductRepository productRepository;

    @Test
    void findByIdTest() {
        //Given
        ProductGroup productGroup = new ProductGroup( "Test Group");
        productGroupRepository.save(productGroup);
        Long id = productGroup.getId();
        //When
        Optional<ProductGroup> result = productGroupRepository.findById(id);
        Long groupId = result.get().getId();
        //Then
        assertEquals(id, groupId);
        //CleanUp
        productGroupRepository.deleteById(id);
    }

    @Test
    void saveProductGroupTest() {
        //Given
        ProductGroup productGroup = new ProductGroup("Test Group");
        //When
        productGroupRepository.save(productGroup);
        Long id = productGroup.getId();
        Optional<ProductGroup> result = productGroupRepository.findById(id);
        //Then
        assertTrue(result.isPresent());
        //CleanUp
        productGroupRepository.deleteById(id);
    }

    @Test
    void findByNameTest() {
        //Given
        ProductGroup productGroup = new ProductGroup("Test Group");
        productGroupRepository.save(productGroup);
        Long id = productGroup.getId();
        //When
        Optional<ProductGroup> result = productGroupRepository.findByName("Test Group");
        //Then
        assertEquals("Test Group", result.get().getName());
        //CleanUp
        productGroupRepository.deleteById(id);
    }

    @Test
    void findAllTest() {
        //Given
        ProductGroup productGroup = new ProductGroup("Test Group");
        ProductGroup productGroup2 = new ProductGroup("Second Test Group");
        ProductGroup productGroup3 = new ProductGroup("Third Test Group");
        productGroupRepository.save(productGroup);
        productGroupRepository.save(productGroup2);
        productGroupRepository.save(productGroup3);
        Long id = productGroup.getId();
        Long id2 = productGroup2.getId();
        Long id3 = productGroup3.getId();
        //When
        List<ProductGroup> resultList = productGroupRepository.findAll();
        //Then
        assertEquals(3, resultList.size());
        //CleanUp
        productGroupRepository.deleteById(id);
        productGroupRepository.deleteById(id2);
        productGroupRepository.deleteById(id3);
    }

    @Test
    void saveWithProductsTest() {
        //Given
        ProductGroup productGroup = new ProductGroup("Test Group");
        Product product1 = new Product(null, "product1", new BigDecimal(10), 3, new ArrayList<>(), productGroup);
        Product product2 = new Product(null, "product2", new BigDecimal(10), 3, new ArrayList<>(), productGroup);
        Product product3 = new Product(null, "product3", new BigDecimal(10), 3, new ArrayList<>(), productGroup);
        productGroup.getProducts().add(product1);
        productGroup.getProducts().add(product2);
        productGroup.getProducts().add(product3);
        productGroupRepository.save(productGroup);
        Long id = productGroup.getId();
        //When
        List<Product> list = productRepository.findAll();
        //Then
        assertEquals(3, list.size());
        //CleanUp
        productGroupRepository.deleteById(id);
    }

    @Test
    void deleteGroupWithProductsTest() {
        //Given
        ProductGroup productGroup = new ProductGroup("Test Group");
        Product product1 = new Product(null, "product1", new BigDecimal(10), 3, new ArrayList<>(), productGroup);
        Product product2 = new Product(null, "product2", new BigDecimal(10), 3, new ArrayList<>(), productGroup);
        Product product3 = new Product(null, "product3", new BigDecimal(10), 3, new ArrayList<>(), productGroup);
        productGroup.getProducts().add(product1);
        productGroup.getProducts().add(product2);
        productGroup.getProducts().add(product3);
        productGroupRepository.save(productGroup);
        Long id = productGroup.getId();
        //When
        productGroupRepository.deleteById(id);
        List<Product> list = productRepository.findAll();
        //Then
        assertEquals(0, list.size());
    }

    @Test
    void updateGroupWithProductsTest() {
        //Given
        ProductGroup productGroup = new ProductGroup("Test Group");
        Product product1 = new Product(null, "product1", new BigDecimal(10), 3, new ArrayList<>(), productGroup);
        Product product2 = new Product(null, "product2", new BigDecimal(10), 3, new ArrayList<>(), productGroup);
        productGroup.getProducts().add(product1);
        productGroup.getProducts().add(product2);
        productGroupRepository.save(productGroup);
        Long id = productGroup.getId();
        List<Product> firstList = productRepository.findAll();
        //When
        productGroup.setName("Update group name");
        Product product3 = new Product(null, "product3", new BigDecimal(10), 3, new ArrayList<>(), productGroup);
        productGroup.getProducts().add(product3);
        productGroupRepository.save(productGroup);
        List<Product> secondList = productRepository.findAll();
        //Then
        assertEquals(2, firstList.size());
        assertEquals(3, secondList.size());
        assertEquals("Update group name", productGroupRepository.findById(id).get().getName());
        //CleanUp
        productGroupRepository.deleteById(id);
    }
}
