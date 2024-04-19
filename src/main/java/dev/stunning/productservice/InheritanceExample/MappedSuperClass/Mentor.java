package dev.stunning.productservice.InheritanceExample.MappedSuperClass;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "msc_Mentor")
        public class Mentor extends User {
        private int numberOfSessions;
        private int numberOfMentees;
}
