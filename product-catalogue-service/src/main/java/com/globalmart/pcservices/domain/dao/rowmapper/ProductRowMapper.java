package com.globalmart.pcservices.domain.dao.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.globalmart.pcservices.domain.model.Product;

public class ProductRowMapper implements RowMapper<Product> {

	@Override
	public Product mapRow(ResultSet rs, int arg1) throws SQLException {

		Product product = new Product();
		product.setProductId(rs.getInt("PRODUCT_ID"));
		product.setName(rs.getString("NAME"));
		product.setCategory(rs.getString("CATEGORY"));

		return product;
	}

}
