package com.api.services;

import com.api.entity.Category;
import com.api.entity.Product;
import com.api.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CategoryService {

    CategoryRepository categoryRepository;
    private final ProductService productService;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository, ProductService productService) {
        this.categoryRepository = categoryRepository;
        this.productService = productService;
    }

    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }

    public List<Category> saveCategories(List<Category> categories){
        return categoryRepository.saveAll(categories);
    }

    public List<Category> getCategories(){
        return categoryRepository.findAll();
    }

    public Category getCategoryById(long id){
        return categoryRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
    }

    public Category getCategoryByName(String name){
        return categoryRepository.findByName(name);
    }

    public String deleteCategory(long id){
        categoryRepository.deleteById(id);
        return "Category Removed ! Category ID:" + id;
    }

    public Category updateCategory(Category category) {
        Category existingCategory = getCategoryById(category.getId());

        if (existingCategory != null){
            existingCategory.setName(category.getName());
        }

        assert existingCategory != null;
        return categoryRepository.save(existingCategory);
    }

    public Map<Long, List<String>> getAllProductsByCategoryId(long id){
        List<String> productsNameByCategoryIdList = new ArrayList<>();
        Map<Long, List<String>> productsByCategoryIdMap = new HashMap<>();

        for (Product product : productService.getProducts()){
            if (product.getCategory().getId() == id){
                productsNameByCategoryIdList.add(product.getName());
            }
        }
        productsByCategoryIdMap.put(id, productsNameByCategoryIdList);

        return productsByCategoryIdMap;
    }

    public Map<Long, List<String>> getAllProductsByCategory(){
        List<String> productsNameByCategoryList = new ArrayList<>();;
        Map<Long, List<String>> productsByCategoryMap = new HashMap<>();

        for (int i = 0; i < getCategories().size(); i++){
            for (int j = 0; j < productService.getProducts().size(); j++){
                if (getCategories().get(i).getId().equals(productService.getProducts().get(j).getCategory().getId())){
                    productsNameByCategoryList.add(productService.getProducts().get(j).getName());
                    productsByCategoryMap.put(getCategories().get(i).getId(), productsNameByCategoryList);
                }
            }
            productsNameByCategoryList = new ArrayList<>();
        }

        return productsByCategoryMap;
    }
}
