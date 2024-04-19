package dev.stunning.productservice.InheritanceExample.MappedSuperClass;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "msc_Instructor")
public class Instructor extends User {
    private boolean isHandsome;
}
