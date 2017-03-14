package com.globalmart.pcservices.resources;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.globalmart.pcservices.domain.model.Product;
import com.globalmart.pcservices.domain.model.ProductList;
import com.globalmart.pcservices.services.ProductService;

@RestController
@RequestMapping("/api")
public class ProductResourceController {

	private static final Logger logger = LoggerFactory.getLogger(ProductResourceController.class);

	@Inject
	ProductService productService;

	@RequestMapping(value = "/products/{searchCriteria}/{searchKey}", method = RequestMethod.GET)
	public ProductList getProducts(@RequestHeader(value = "Accept") String acceptHeader,
			@PathVariable(value = "searchCriteria") String searchCriteria,
			@PathVariable(value = "searchKey") String searchKey) {

		logger.info("ProductResourceController.getProducts:Enter");
		logger.info("Accept header info:" + acceptHeader);
		logger.info("searchCriteria:" + searchCriteria);
		logger.info("searchKey:" + searchKey);

		ProductList productList = new ProductList();
		productList.setProductList(productService.getProducts(searchCriteria, searchKey));

		logger.info("ProductResourceController.getProducts:Exit");

		return productList;
	}

	@RequestMapping(value = "/products", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.CREATED)
	public String createProduct(@RequestBody Product product, @RequestHeader(value = "Content-Type") String mediaType) {

		logger.info("ProductResourceController.createProduct:Enter");
		logger.info("Media type info:" + mediaType);

		int status = productService.addProduct(product);
		String responseMessage = " ";

		if (status > 0) {
			responseMessage = "created";
		} else {
			responseMessage = "not created";
		}

		
		logger.info("ProductResourceController.createProduct:Exit");

		return responseMessage;
	}

	@RequestMapping(value = "/products/{productId}", method = RequestMethod.DELETE)
	@ResponseStatus(value = HttpStatus.OK)
	public String deleteProduct(@PathVariable(value = "productId") int productId) {

		int status = productService.removeProduct(productId);
		String responseMessage = " ";

		if (status > 0) {
			responseMessage = "Deleted";
		} else {
			responseMessage = "not Deleted";
		}

		return responseMessage;

	}

	public ProductService getProductService() {
		return productService;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

}
