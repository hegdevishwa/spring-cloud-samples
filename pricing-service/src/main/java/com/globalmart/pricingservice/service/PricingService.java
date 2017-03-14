package com.globalmart.pricingservice.service;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.globalmart.pricingservice.HomeController;
import com.globalmart.pricingservice.domain.dao.PricingDao;
import com.globalmart.pricingservice.domain.model.Price;

@Service(value = "pricingService")
public class PricingService {

	@Inject
	PricingDao pricingDao;

	private static final Logger logger = LoggerFactory.getLogger(PricingService.class);

	public Price getPrice(int productId) {
		logger.info("getPrice method:ENTERED");
		return pricingDao.getPrice(productId);

	}

	public int createPrice(Price price) {
		logger.info("getPrice method:ENTERED");
		return pricingDao.createPrice(price);

	}

	public PricingDao getPricingDao() {
		return pricingDao;
	}

	public void setPricingDao(PricingDao pricingDao) {
		this.pricingDao = pricingDao;
	}

}
