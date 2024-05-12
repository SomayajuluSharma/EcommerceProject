package dev.stunning.productservice.AuthenticationClient.dtos;

import dev.stunning.productservice.models.BaseModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Role extends BaseModel {
    private String name;
}