package dev.stunning.productservice.dtos;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class RatingDto implements Serializable {
    private double rating;
    private double count;
}
