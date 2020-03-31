package com.api.controller;

import com.api.entity.Category;
import com.api.entity.Product;
import com.api.exceptions.CategoryExistsException;
import com.api.exceptions.CategoryNotFoundException;
import com.api.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/category")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @RequestMapping(value = "/addCategory", method = RequestMethod.POST, headers = "Accept=application/json")
    public Category addCategory(@RequestBody Category category){
        try {
            return categoryService.saveCategory(category);
        } catch (CategoryExistsException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
        }
    }

    @RequestMapping(value = "/addCategorySetLocationHeader", method = RequestMethod.POST, headers = "Accept=application/json")
    public ResponseEntity<Void> addCategorySetLocationHeader(@RequestBody Category category, UriComponentsBuilder uriComponentsBuilder){
        try {
            categoryService.saveCategory(category);
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setLocation(uriComponentsBuilder.path("addCategorySetLocationHeader/{id}").buildAndExpand(category.getId()).toUri());

            return new ResponseEntity<>(httpHeaders, HttpStatus.CREATED);
        } catch (CategoryExistsException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
        }
    }

    @RequestMapping(value = "/addCategories", method = RequestMethod.POST, headers = "Accept=application/json")
    public List<Category> addCategories(@RequestBody List<Category> categories){
        try {
            return categoryService.saveCategories(categories);
        } catch (CategoryExistsException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
        }
    }

    @RequestMapping(value = "/findAllCategories", method = RequestMethod.GET, headers = "Accept=application/json")
    public List<Category> findAllCategories(){
        return categoryService.getCategories();
    }

    @RequestMapping(value = "/findCategoryById/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
    public Category findCategoryById(@PathVariable("id") long id){
        try {
            return categoryService.getCategoryById(id);
        } catch (CategoryNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }

    @RequestMapping(value = "/findCategoryByName/{name}", method = RequestMethod.GET, headers = "Accept=application/json")
    public Category findCategoryByName(@PathVariable("name") String name){
        return categoryService.getCategoryByName(name);
    }

    @RequestMapping(value = "/updateCategory/{id}", method = RequestMethod.PUT, headers = "Accept=application/json")
    public Category updateCategory(@PathVariable("id") long id, @RequestBody Category category){
        try {
            return categoryService.updateCategory(id, category);
        } catch (CategoryNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
        }
    }

    @RequestMapping(value = "/deleteCategory/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
    public String deleteCategory(@PathVariable("id") long id){
        try {
            return categoryService.deleteCategory(id);
        } catch (CategoryNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
        }
    }

    @RequestMapping(value = "/findAllProductsByCategoryId/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
    public Map<Long, List<Product>> findAllProductsByCategoryId(@PathVariable("id") long id){
        return categoryService.getAllProductsByCategoryId(id);
    }

    @RequestMapping(value = "/findAllProductsByCategory", method = RequestMethod.GET, headers = "Accept=application/json")
    public Map<Long, List<Product>> findAllProductsByCategory(){
        return categoryService.getAllProductsByCategory();
    }
}
