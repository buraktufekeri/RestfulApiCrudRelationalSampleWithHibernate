package com.api.dto;

import java.util.List;

public class ProductByCategoryDto {

    private List<ProductDto> productDtoList = null;

    public List<ProductDto> getProductDtoList() {
        return productDtoList;
    }

    public void setProductDtoList(List<ProductDto> productDtoList) {
        this.productDtoList = productDtoList;
    }
}
