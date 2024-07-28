package org.oiab.productservice.mappers;

public interface Mapper<A, B> {
  A mapToEntity(B dto);

  B mapToDto(A entity);
}
