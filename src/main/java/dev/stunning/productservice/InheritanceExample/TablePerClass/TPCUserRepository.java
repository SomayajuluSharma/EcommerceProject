package dev.stunning.productservice.InheritanceExample.TablePerClass;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TPCUserRepository extends JpaRepository<User, Long> {
    User save(User user);

    User findUserById(Long id);
}
