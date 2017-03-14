package com.vishwa.pcservice.domain.dao;

import java.util.List;

import com.vishwa.pcservice.domain.model.Product;

public interface ProductDao {

	public List<Product> getProducts(String searchCriteria, String key);

	public int addProduct(Product product);

	public int deleteProduct(int productId);

}
