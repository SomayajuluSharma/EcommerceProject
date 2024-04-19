package dev.stunning.productservice.InheritanceExample.TablePerClass;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "tbc_mentor")
public class  Mentor extends User {
    private int numberOfSessions;
    private int numberOfMentees;
}
