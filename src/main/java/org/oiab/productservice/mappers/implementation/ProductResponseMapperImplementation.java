package org.oiab.productservice.mappers.implementation;

import org.modelmapper.ModelMapper;
import org.oiab.productservice.dtos.ProductResponseDto;
import org.oiab.productservice.mappers.Mapper;
import org.oiab.productservice.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductResponseMapperImplementation implements Mapper<Product, ProductResponseDto> {
  private final ModelMapper modelMapper;

  public ProductResponseMapperImplementation(ModelMapper modelMapper) {
    this.modelMapper = modelMapper;
  }

  @Override
  public Product mapToEntity(ProductResponseDto dto) {
    return modelMapper.map(dto, Product.class);
  }

  @Override
  public ProductResponseDto mapToDto(Product entity) {
    return modelMapper.map(entity, ProductResponseDto.class);
  }
}
