package com.vishwa.pcservice.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vishwa.pcservice.domain.dao.ProductDao;
import com.vishwa.pcservice.domain.model.PriceVo;
import com.vishwa.pcservice.domain.model.Product;
import com.vishwa.pcservice.integration.client.PricingServiceClient;

@Service(value = "productService")
public class ProductServiceImpl implements ProductService {

	private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

	@Autowired
	ProductDao productDao;

	@Autowired
	PricingServiceClient pricingServiceClient;
	
	@Override
	public List<Product> getProducts(String searchCriteria, String searchKey) {

		List<Product> products = productDao.getProducts(searchCriteria, searchKey);
		for (Product p : products) {
			p.setPrice(pricingServiceClient.getPrice(p.getProductId()).getPrice());
		}
		return products;
	}

	@Override
	public int addProduct(Product product) {

		PriceVo priceVo = new PriceVo();
		priceVo.setProductId(product.getProductId());
		priceVo.setPrice(product.getPrice());
		String response = pricingServiceClient.createPrice(priceVo);

		int status = productDao.addProduct(product);

		if (status == 1 && response.equalsIgnoreCase("created")) {
			return 1;
		} else {
			return 0;
		}

	}

	@Override
	public int removeProduct(int productId) {
		return productDao.deleteProduct(productId);
	}

	public ProductDao getProductDao() {
		return productDao;
	}

	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}

}
