package dev.stunning.productservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
public class BaseModel {
    @Id
    private Long id;
    private Date createdAt;
    private Date updatedAt;
    private boolean isDeleted;

}
