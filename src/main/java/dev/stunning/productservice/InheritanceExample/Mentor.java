package dev.stunning.productservice.InheritanceExample;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Mentor extends User{
    private int numberOfSessions;
    private int numberOfMentees;
}
