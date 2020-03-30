package com.api.controller;

import com.api.entity.Category;
import com.api.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
        return categoryService.saveCategory(category);
    }

    @RequestMapping(value = "/addCategories", method = RequestMethod.POST, headers = "Accept=application/json")
    public List<Category> addCategories(@RequestBody List<Category> categories){
        return categoryService.saveCategories(categories);
    }

    @RequestMapping(value = "/findAllCategories", method = RequestMethod.GET, headers = "Accept=application/json")
    public List<Category> findAllCategories(){
        return categoryService.getCategories();
    }

    @RequestMapping(value = "/findCategoryById/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
    public Category findCategoryById(@PathVariable("id") long id){
        return categoryService.getCategoryById(id);
    }

    @RequestMapping(value = "/findCategoryByName/{name}", method = RequestMethod.GET, headers = "Accept=application/json")
    public Category findCategoryByName(@PathVariable("name") String name){
        return categoryService.getCategoryByName(name);
    }

    @RequestMapping(value = "/updateCategory", method = RequestMethod.PUT, headers = "Accept=application/json")
    public Category updateCategory(@RequestBody Category category){
        return categoryService.updateCategory(category);
    }

    @RequestMapping(value = "/deleteCategory/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
    public String deleteCategory(@PathVariable("id") long id){
        return categoryService.deleteCategory(id);
    }

    @RequestMapping(value = "/findAllProductsByCategoryId/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
    public Map<Long, List<String>> findAllProductsByCategoryId(@PathVariable("id") long id){
        return categoryService.getAllProductsByCategoryId(id);
    }

    @RequestMapping(value = "/findAllProductsByCategory", method = RequestMethod.GET, headers = "Accept=application/json")
    public Map<Long, List<String>> findAllProductsByCategory(){
        return categoryService.getAllProductsByCategory();
    }
}
