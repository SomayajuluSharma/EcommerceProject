package dev.stunning.productservice.InheritanceExample.TablePerClass;

import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "tbc_instructor")
public class Instructor extends User {
    private boolean isHandsome;
}