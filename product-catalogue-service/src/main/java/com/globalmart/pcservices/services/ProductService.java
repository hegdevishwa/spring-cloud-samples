package com.globalmart.pcservices.services;

import java.util.List;

import com.globalmart.pcservices.domain.model.Product;

public interface ProductService {

	public List<Product> getProducts(String searchCriteria, String searchKey);

	public int addProduct(Product product);

	public int removeProduct(int productId);
}
