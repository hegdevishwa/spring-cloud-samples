package com.vishwa.pcservice.integration.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.vishwa.pcservice.domain.model.PriceVo;
import com.vishwa.pcservice.resourcecontroller.ProductResourceController;

@Service(value = "pricingServiceClient")
public class PricingServiceClient {

	private static final String baseUri = "http://localhost:8082/pricing-service/api/price/";

	private static final Logger logger = LoggerFactory.getLogger(ProductResourceController.class);

	public PriceVo getPrice(int productId) {

		RestTemplate restTemplate = new RestTemplate();

		PriceVo priceVo = restTemplate.getForObject(createUri(new Integer(productId).toString()), PriceVo.class);

		return priceVo;

	}

	public String createPrice(PriceVo priceVo) {

		logger.info(priceVo.getProductId() + " " + priceVo.getPrice());

		RestTemplate restTemplate = new RestTemplate();

		ServiceResponse serviceResponse = restTemplate.postForObject(baseUri, priceVo, ServiceResponse.class);

		System.out.println(serviceResponse.getResponse());

		return serviceResponse.getResponse();

	}

	private String createUri(String input) {
		return baseUri.concat(input);
	}

}
