package com.globalmart.pcservices.domain.dao;

import java.util.List;

import com.globalmart.pcservices.domain.model.Product;

public interface ProductDao {

	public List<Product> getProducts(String searchCriteria, String key);

	public int addProduct(Product product);
	
	public int deleteProduct(int productId);

}
