package org.oiab.productservice.controllers;

import org.oiab.productservice.dtos.ProductRequestDto;
import org.oiab.productservice.dtos.ProductResponseDto;
import org.oiab.productservice.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
  private ProductService productService;

  public ProductController(ProductService productService) {
    this.productService = productService;
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public ProductResponseDto save(@RequestBody ProductRequestDto productRequest) {
    return this.productService.save(productRequest);
  }

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<ProductResponseDto> findAll() {
    return this.productService.findAll();
  }

  @GetMapping(params = "name")
  @ResponseStatus(HttpStatus.OK)
  public List<ProductResponseDto> findByName(@RequestParam String name) {
    return this.productService.findByName(name);
  }
}
