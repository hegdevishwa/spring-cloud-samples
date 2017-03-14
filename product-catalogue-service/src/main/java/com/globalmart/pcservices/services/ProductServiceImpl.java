package com.globalmart.pcservices.services;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.globalmart.pcservices.domain.dao.ProductDao;
import com.globalmart.pcservices.domain.model.PriceVo;
import com.globalmart.pcservices.domain.model.Product;
import com.globalmart.pcservices.integration.client.PricingServiceClient;

@Service(value = "productService")
public class ProductServiceImpl implements ProductService {

	private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

	@Inject
	ProductDao productDao;

	@Inject
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
