package dev.stunning.productservice.dtos;

import dev.stunning.productservice.models.Product;
import lombok.Getter;
import lombok.Setter;

import java.util.Optional;

@Getter
@Setter
public class GetSingleProductResponseDto {
    private Optional<Product> product;
}
