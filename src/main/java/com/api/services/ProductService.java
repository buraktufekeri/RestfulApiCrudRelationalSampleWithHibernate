package com.api.services;

import com.api.entity.Product;
import com.api.exceptions.CategoryNotFoundException;
import com.api.exceptions.ProductNotFoundException;
import com.api.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Autowired
    CategoryService categoryService;

    public Product saveProduct(Product product){
        return productRepository.save(product);
    }

    public List<Product> saveProducts(List<Product> products){
        return productRepository.saveAll(products);
    }

    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(long id) throws ProductNotFoundException {
        Optional<Product> product = productRepository.findById(id);
        if (!product.isPresent())
            throw new ProductNotFoundException("Product Not Found in Product Repository ProductId:" + id);

        return product.get();
    }

    public Product getProductByName(String name){
        return productRepository.findByName(name);
    }

    public Product updateProduct(long id, Product product) throws ProductNotFoundException, CategoryNotFoundException {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (!optionalProduct.isPresent())
            throw new ProductNotFoundException("Product Not Found in Product Repository ProductId:" + id + ", Provide The Correct ProductId!");

        categoryService.getCategoryById(product.getCategory().getId());
        optionalProduct.get().setName(product.getName());
        optionalProduct.get().setPrice(product.getPrice());
        optionalProduct.get().setQuantity(product.getQuantity());
        optionalProduct.get().setUpdatedBy(product.getUpdatedBy());
        optionalProduct.get().setCategory(product.getCategory());

        return productRepository.save(optionalProduct.get());
    }

    public String deleteProduct(long id) throws ProductNotFoundException {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (!optionalProduct.isPresent())
            throw new ProductNotFoundException("Product Not Found in Product Repository ProductId:" + id + ", Provide The Correct ProductId!");

        productRepository.deleteById(id);

        return "Product Removed! Product ID:" + id;
    }
}
