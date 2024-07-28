package org.oiab.productservice.services;

import org.oiab.productservice.dtos.ProductRequestDto;
import org.oiab.productservice.dtos.ProductResponseDto;

public interface ProductService {
  ProductResponseDto save(ProductRequestDto productRequest);
}
