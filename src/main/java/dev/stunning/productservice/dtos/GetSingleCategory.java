package dev.stunning.productservice.dtos;

import dev.stunning.productservice.models.Category;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GetSingleCategory {
    private Category category;
}
