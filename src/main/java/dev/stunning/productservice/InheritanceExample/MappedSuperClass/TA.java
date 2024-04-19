package dev.stunning.productservice.InheritanceExample.MappedSuperClass;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "msc_TA")
public class TA extends User {
    private double averageRating;
}
