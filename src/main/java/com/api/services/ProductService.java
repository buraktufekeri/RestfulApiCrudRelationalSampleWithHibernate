package com.api.services;

import com.api.entity.Product;
import com.api.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product saveProduct(Product product){
        return productRepository.save(product);
    }

    public List<Product> saveProducts(List<Product> products){
        return productRepository.saveAll(products);
    }

    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(long id){
        return productRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
    }

    public Product getProductByName(String name){
        return productRepository.findByName(name);
    }

    public Product updateProduct(Product product) {
        Product existingProduct = getProductById(product.getId());

        if (existingProduct != null){
            existingProduct.setUpdatedBy(product.getUpdatedBy());
            existingProduct.setName(product.getName());
            existingProduct.setQuantity(product.getQuantity());
            existingProduct.setPrice(product.getPrice());
            existingProduct.setCategory(product.getCategory());
        }

        assert existingProduct != null;
        return productRepository.save(existingProduct);
    }

    public String deleteProduct(long id){
        productRepository.deleteById(id);
        return "Product Removed ! Product ID:" + id;
    }
}
