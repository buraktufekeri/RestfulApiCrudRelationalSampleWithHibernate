package com.api.controller;

import com.api.entity.Product;
import com.api.exceptions.CategoryNotFoundException;
import com.api.exceptions.ProductNotFoundException;
import com.api.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping(value = "/api/product")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping(value = "/addProduct", method = RequestMethod.POST, headers = "Accept=application/json")
    public Product addProduct(@RequestBody Product product) {
        return productService.saveProduct(product);
    }

    @RequestMapping(value = "/addProducts", method = RequestMethod.POST, headers = "Accept=application/json")
    public List<Product> addProducts(@RequestBody List<Product> products){
        return productService.saveProducts(products);
    }

    @RequestMapping(value = "/findAllProducts", method = RequestMethod.GET, headers = "Accept=application/json")
    public List<Product> findAllProducts(){
        return productService.getProducts();
    }

    @RequestMapping(value = "/findProductById/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
    public Product findProductById(@PathVariable("id") long id){
        try {
            return productService.getProductById(id);
        } catch (ProductNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }

    @RequestMapping(value = "/findProductByName/{name}", method = RequestMethod.GET, headers = "Accept=application/json")
    public Product findProductByName(@PathVariable("name") String name){
        return productService.getProductByName(name);
    }

    @RequestMapping(value = "/updateProduct/{id}", method = RequestMethod.PUT, headers = "Accept=application/json")
    public Product updateProduct(@PathVariable("id") long id, @RequestBody Product product){
        try {
            return productService.updateProduct(id, product);
        } catch (ProductNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
        } catch (CategoryNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }

    @RequestMapping(value = "/deleteProduct/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
    public String deleteProduct(@PathVariable("id") long id){
        try {
            return productService.deleteProduct(id);
        } catch (ProductNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
        }
    }
}
