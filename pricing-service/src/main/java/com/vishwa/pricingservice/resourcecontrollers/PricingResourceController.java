package com.vishwa.pricingservice.resourcecontrollers;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.vishwa.pricingservice.domain.model.Price;
import com.vishwa.pricingservice.service.PricingService;

@RestController
@RequestMapping("/api")
public class PricingResourceController {

	@Inject
	PricingService pricingService;

	private static final Logger logger = LoggerFactory.getLogger(PricingResourceController.class);

	@RequestMapping("/price/{productId}")
	public Price getPrice(@PathVariable(value = "productId") int productId) {
		logger.info("getPrice:ENTER");

		return pricingService.getPrice(productId);
	}

	public PricingService getPricingService() {
		return pricingService;
	}

	public void setPricingService(PricingService pricingService) {
		this.pricingService = pricingService;
	}

	@RequestMapping(value = "/price", method = RequestMethod.POST)
	public Map createPrice(Price price) {

		logger.info("createPrice:ENTER");
		logger.info("PID:"+price.getProductId()+" price:"+price.getPrice());
		

		Map<String, String> resultMap = new HashMap<>();
		int status = pricingService.createPrice(price);
		String responseMessage = " ";
		if (status > 0) {
			responseMessage = "created";
		} else {
			responseMessage = "not created";
		}

		resultMap.put("response", responseMessage);

		logger.info("createPrice:EXIT");

		return resultMap;
	}

}
