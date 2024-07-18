package dev.stunning.productservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class getProductsDto {
    private int numberOfResults;
    private int offset;
}
