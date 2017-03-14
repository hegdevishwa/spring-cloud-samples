package com.vishwa.pricingservice.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vishwa.pricingservice.domain.dao.PricingDao;
import com.vishwa.pricingservice.domain.model.Price;

@Service
public class PricingService {

	@Autowired
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
