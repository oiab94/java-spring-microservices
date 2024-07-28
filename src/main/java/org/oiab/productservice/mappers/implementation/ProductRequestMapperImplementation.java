package org.oiab.productservice.mappers.implementation;

import org.modelmapper.ModelMapper;
import org.oiab.productservice.dtos.ProductRequestDto;
import org.oiab.productservice.mappers.Mapper;
import org.oiab.productservice.model.Product;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Component;

@Component
public class ProductRequestMapperImplementation implements Mapper<Product, ProductRequestDto> {
  private final ModelMapper modelMapper;

  public ProductRequestMapperImplementation(ModelMapper modelMapper) {
    this.modelMapper = modelMapper;
  }

  @Override
  public Product mapToEntity(ProductRequestDto dto) {
    return modelMapper.map(dto, Product.class);
  }

  @Override
  public ProductRequestDto mapToDto(Product entity) {
    return modelMapper.map(entity, ProductRequestDto.class);
  }
}
