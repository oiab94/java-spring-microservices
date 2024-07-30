package org.oiab.productservice.repositories.implementation;

import org.oiab.productservice.model.Product;
import org.oiab.productservice.repositories.ProductCustomRespository;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public class ProductCustomRepositoryImpl implements ProductCustomRespository {
	private final MongoTemplate mongoTemplate;

	public ProductCustomRepositoryImpl(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

	@Override
	public List<Product> findByName(String name) {
		Query query = new Query();
		Query productQuery = query.addCriteria(Criteria.where("name").is(name));

		return mongoTemplate.find(productQuery, Product.class);
	}
}
