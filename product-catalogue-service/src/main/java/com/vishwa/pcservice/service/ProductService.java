package com.vishwa.pcservice.service;

import java.util.List;

import com.vishwa.pcservice.domain.model.Product;

public interface ProductService {

	public List<Product> getProducts(String searchCriteria, String searchKey);

	public int addProduct(Product product);

	public int removeProduct(int productId);
}
