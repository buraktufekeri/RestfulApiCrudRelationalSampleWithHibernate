package com.api.services;

import com.api.entity.Category;
import com.api.entity.Product;
import com.api.exceptions.CategoryExistsException;
import com.api.exceptions.CategoryNotFoundException;
import com.api.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Autowired
    ProductService productService;

    public Category saveCategory(Category category) throws CategoryExistsException {
        Category existingCategory = categoryRepository.findByName(category.getName());
        if (existingCategory != null)
            throw new CategoryExistsException("Category Already Exists in Category Repository CategoryName:" + category.getName());

        return categoryRepository.save(category);
    }

    public List<Category> saveCategories(List<Category> categories) throws CategoryExistsException {
        List<Category> existingCategoryList = categoryRepository.findAll();
        for (int i = 0; i < categories.size()-1; i++)
            if (categories.get(i).getName().equals(categories.get(i+1).getName()))
                throw new CategoryExistsException("Category Names Cannot Be The Same! CategoryName:" + categories.get(i).getName());

        for (Category paramCategory : categories)
            for (Category existingCategory : existingCategoryList)
                if (paramCategory.getName().equals(existingCategory.getName()))
                    throw new CategoryExistsException("Category Already Exists in Category Repository CategoryName:" + paramCategory.getName());

        return categoryRepository.saveAll(categories);
    }

    public List<Category> getCategories(){
        return categoryRepository.findAll();
    }

    public Category getCategoryById(long id) throws CategoryNotFoundException {
        Optional<Category> category = categoryRepository.findById(id);
        if (!category.isPresent())
            throw new CategoryNotFoundException("Category Not Found in Category Repository CategoryId:" + id);

        return category.get();
    }

    public Category getCategoryByName(String name){
        return categoryRepository.findByName(name);
    }

    public Category updateCategory(long id, Category category) throws CategoryNotFoundException {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (!optionalCategory.isPresent())
            throw new CategoryNotFoundException("Category Not Found in Category Repository CategoryId:" + id + ", Provide The Correct CategoryId!");

        optionalCategory.get().setName(category.getName());

        return categoryRepository.save(optionalCategory.get());
    }

    public String deleteCategory(long id) throws CategoryNotFoundException {
        Optional<Category> category = categoryRepository.findById(id);
        if (!category.isPresent())
            throw new CategoryNotFoundException("Category Not Found in Category Repository CategoryId:" + id + ", Provide The Correct CategoryId!");
        else if (getAllProductsByCategoryId(id).get(id).size() != 0)
            throw new CategoryNotFoundException("Products With Category Id Are Available! CategoryId:" + id);

        categoryRepository.deleteById(id);

        return "Category Removed! Category ID:" + id;
    }

    public Map<Long, List<Product>> getAllProductsByCategoryId(long id){
        List<Product> productsByCategoryIdList = new ArrayList<>();
        Map<Long, List<Product>> productsByCategoryIdMap = new HashMap<>();

        for (Product product : productService.getProducts())
            if (product.getCategory().getId() == id)
                productsByCategoryIdList.add(product);

        productsByCategoryIdMap.put(id, productsByCategoryIdList);

        return productsByCategoryIdMap;
    }

    public Map<Long, List<Product>> getAllProductsByCategory(){
        List<Product> productsByCategoryList = new ArrayList<>();;
        Map<Long, List<Product>> productsByCategoryMap = new HashMap<>();

        for (int i = 0; i < getCategories().size(); i++){
            for (int j = 0; j < productService.getProducts().size(); j++){
                if (getCategories().get(i).getId().equals(productService.getProducts().get(j).getCategory().getId())){
                    productsByCategoryList.add(productService.getProducts().get(j));
                    productsByCategoryMap.put(getCategories().get(i).getId(), productsByCategoryList);
                }
            }
            productsByCategoryList = new ArrayList<>();
        }

        return productsByCategoryMap;
    }
}
