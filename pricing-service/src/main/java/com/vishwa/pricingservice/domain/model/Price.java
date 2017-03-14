package com.vishwa.pricingservice.domain.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "ProductPrice")
public class Price {

	int productId;
	long price;

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}

}
