package dev.stunning.productservice;

import dev.stunning.productservice.InheritanceExample.JoinedTable.JTMentorRepository;
import dev.stunning.productservice.InheritanceExample.JoinedTable.JTUserRepository;
import dev.stunning.productservice.InheritanceExample.MappedSuperClass.MSMentorRepository;
import dev.stunning.productservice.InheritanceExample.SingleClass.SCMentorRepository;
import dev.stunning.productservice.InheritanceExample.SingleClass.SCUserRepository;
import dev.stunning.productservice.InheritanceExample.JoinedTable.Mentor;
import dev.stunning.productservice.InheritanceExample.TablePerClass.TPCMentorRepository;
import dev.stunning.productservice.InheritanceExample.TablePerClass.TPCUserRepository;
import dev.stunning.productservice.InheritanceExample.JoinedTable.User;
import jakarta.persistence.MappedSuperclass;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProductServiceApplicationTests {
    @Autowired
    private JTMentorRepository jtMentorRepository;
    @Autowired
    private JTUserRepository jtUserRepository;

    @Test
    void contextLoads() {
    }

    @Test
    void testDifferentInheritence(){
         User user = new User();
         user.setEmail("saikrishna");
        user.setPassword("saikrishna");
        jtUserRepository.save(user);

        Mentor mentor = new Mentor();
        mentor.setEmail("saikrishna");
        mentor.setPassword("saikrishna");
        mentor.setNumberOfMentees(2);
        mentor.setNumberOfSessions(50);
        jtMentorRepository.save(mentor);

    }

}
