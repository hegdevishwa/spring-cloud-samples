package com.globalmart.pcservices.domain.dao;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.globalmart.pcservices.domain.dao.rowmapper.ProductRowMapper;
import com.globalmart.pcservices.domain.model.Product;

@Repository(value = "productDao")
public class ProductDaoImpl implements ProductDao {

	@Inject
	JdbcTemplate jdbcTemplate;

	@Inject
	NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Override
	public List<Product> getProducts(String searchCriteria, String searchKey) {

		List<Product> productList = new ArrayList<>();

		StringBuffer sql = new StringBuffer("select * from product where ");
		if (searchCriteria.equalsIgnoreCase("category")) {
			sql.append("category = :searchKey ");
		} else if (searchCriteria.equalsIgnoreCase("name")) {
			sql.append("name = :searchKey ");
		} else {
			sql.append("product_id = :searchKey ");
		}

		SqlParameterSource namedParameters = new MapSqlParameterSource("searchCriteria", searchCriteria)
				.addValue("searchKey", searchKey);

		productList = namedParameterJdbcTemplate.query(sql.toString(), namedParameters, new ProductRowMapper());

		return productList;
	}

	@Override
	public int addProduct(Product product) {

		String sqlMax = "select max(product_id) from product";
		int maxKey = jdbcTemplate.queryForObject(sqlMax, Integer.class);

		product.setProductId(maxKey + 1);

		String sql = "insert into product (product_id, name, category) values(?,?,?)";

		int status = jdbcTemplate.update(sql, product.getProductId(), product.getName(), product.getCategory());

		return status;

	}

	public int deleteProduct(int productId) {

		String sql = "delete product where product_id = ?";

		int status = jdbcTemplate.update(sql, productId);

		return status;

	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public NamedParameterJdbcTemplate getNamedParameterJdbcTemplate() {
		return namedParameterJdbcTemplate;
	}

	public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}

}
