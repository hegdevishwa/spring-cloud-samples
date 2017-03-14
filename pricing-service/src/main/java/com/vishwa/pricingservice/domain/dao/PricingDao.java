package com.vishwa.pricingservice.domain.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.vishwa.pricingservice.domain.model.Price;
import com.vishwa.pricingservice.resourcecontrollers.PricingResourceController;

@Repository
public class PricingDao {

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Autowired
	NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	private static final Logger logger = LoggerFactory.getLogger(PricingResourceController.class);

	public Price getPrice(int productId) {

		logger.info("productId:" + productId);

		String sql = "select * from price_list where product_id = :productId";

		SqlParameterSource namedParameters = new MapSqlParameterSource("productId", productId);

		Price price = (Price) namedParameterJdbcTemplate.queryForObject(sql, namedParameters, new RowMapper<Price>() {
			@Override
			public Price mapRow(ResultSet rs, int arg1) throws SQLException {
				Price price = new Price();
				price.setProductId(rs.getInt("product_id"));
				price.setPrice(rs.getInt("price"));
				return price;
			}
		});

		return price;

	}

	public int createPrice(Price price) {

		logger.info("Pid:" + price.getProductId() + " price:" + price.getPrice());

		String sql = "insert into price_list (product_id, price) values(?,?)";

		int status = jdbcTemplate.update(sql, price.getProductId(), price.getPrice());

		return status;

	}

}
