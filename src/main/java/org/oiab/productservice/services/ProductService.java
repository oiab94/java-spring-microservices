package org.oiab.productservice.services;

import org.oiab.productservice.dtos.ProductRequestDto;
import org.oiab.productservice.dtos.ProductResponseDto;

import java.util.List;

public interface ProductService {
  ProductResponseDto save(ProductRequestDto productRequest);

  List<ProductResponseDto> findAll();
}
