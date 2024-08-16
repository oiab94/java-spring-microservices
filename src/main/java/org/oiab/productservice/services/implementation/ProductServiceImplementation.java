package org.oiab.productservice.services.implementation;

import lombok.AllArgsConstructor;
import org.oiab.productservice.dtos.ProductRequestDto;
import org.oiab.productservice.dtos.ProductResponseDto;
import org.oiab.productservice.mappers.implementation.ProductRequestMapperImplementation;
import org.oiab.productservice.mappers.implementation.ProductResponseMapperImplementation;
import org.oiab.productservice.model.Product;
import org.oiab.productservice.repositories.ProductRepository;
import org.oiab.productservice.services.ProductService;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductServiceImplementation implements ProductService {
  private final Logger log;
  private final ProductRepository productRepository;
  private final ProductRequestMapperImplementation productRequestMapper;
  private final ProductResponseMapperImplementation productResponseMapper;


  /**
   * Save a product
   * @param productRequest {name, description, price}
   * @return ProductResponseDto {id, name, description, price}
   */
  @Override
  public ProductResponseDto save(ProductRequestDto productRequest) {
    Product product = productRequestMapper.mapToEntity(productRequest);
    Product savedProduct = productRepository.save(product);

	  return productResponseMapper.mapToDto(savedProduct);
  }

  /**
   * Get all products
   * @return List<ProductResponseDto> {id, name, description, price}
   */
  @Override
  public List<ProductResponseDto> findAll() {
    List<Product> products = productRepository.findAll();

    return products
        .stream()
        .map(productResponseMapper::mapToDto)
        .toList();
  }

  @Override
  public List<ProductResponseDto> findByName(String name) {
    List<Product> products = productRepository.findByName(name);

    return products
      .stream()
      .map(productResponseMapper::mapToDto)
      .toList();
  }
}
