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
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductServiceImplementation implements ProductService {
  private static final Logger log = LoggerFactory.getLogger(ProductServiceImplementation.class);
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
    ProductResponseDto productResponseDto = productResponseMapper.mapToDto(savedProduct);

    log.info("Product saved: {}", savedProduct);
    return productResponseDto;
  }
}
