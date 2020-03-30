package com.api.controller;

import com.api.entity.Product;
import com.api.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
        return productService.getProductById(id);
    }

    @RequestMapping(value = "/findProductByName/{name}", method = RequestMethod.GET, headers = "Accept=application/json")
    public Product findProductByName(@PathVariable("name") String name){
        return productService.getProductByName(name);
    }

    @RequestMapping(value = "/updateProduct", method = RequestMethod.PUT, headers = "Accept=application/json")
    public Product updateProduct(@RequestBody Product product){
        return productService.updateProduct(product);
    }

    @RequestMapping(value = "/deleteProduct/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
    public String deleteProduct(@PathVariable("id") long id){
        return productService.deleteProduct(id);
    }
}
