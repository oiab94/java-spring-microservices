package org.oiab.productservice.repositories;

import org.oiab.productservice.model.Product;

import java.util.List;

public interface ProductCustomRespository {
	List<Product> findByName(String name);
}
