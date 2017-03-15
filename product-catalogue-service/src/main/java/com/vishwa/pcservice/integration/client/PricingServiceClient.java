package com.vishwa.pcservice.integration.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.vishwa.pcservice.domain.model.PriceVo;
import com.vishwa.pcservice.resourcecontroller.ProductResourceController;

@Service
@RefreshScope
public class PricingServiceClient {

	@Value("${pricing-service.endpoint.uri}")
	private String baseUri;

	@Autowired
	EurekaClient discoveryClient;

	private static final Logger logger = LoggerFactory.getLogger(ProductResourceController.class);

	public PriceVo getPrice(int productId) {

		System.out.println("***** base URI:" + baseUri);

		RestTemplate restTemplate = new RestTemplate();

		PriceVo priceVo = restTemplate.getForObject(createUri(new Integer(productId).toString()), PriceVo.class);

		return priceVo;

	}

	public String createPrice(PriceVo priceVo) {

		logger.info(priceVo.getProductId() + " " + priceVo.getPrice());

		RestTemplate restTemplate = new RestTemplate();

		ServiceResponse serviceResponse = restTemplate.postForObject(serviceUrl(), priceVo, ServiceResponse.class);

		System.out.println(serviceResponse.getResponse());

		return serviceResponse.getResponse();

	}

	private String createUri(String input) {
		StringBuilder sb = new StringBuilder(serviceUrl());
		sb.append(input);
		System.out.println("URI created:" + sb.toString());
		return sb.toString();
	}

	public String serviceUrl() {
		InstanceInfo instance = discoveryClient.getNextServerFromEureka("PRICING-SERVICE", false);
		// discoveryClient.
		System.out.println("*****************Home page URLS****************" + instance.getHomePageUrl());
		StringBuilder sb = new StringBuilder(instance.getHomePageUrl());
		sb.append(baseUri);
		return sb.toString();
	}

}
