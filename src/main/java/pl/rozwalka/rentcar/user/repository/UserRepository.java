package pl.rozwalka.rentcar.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.rozwalka.rentcar.user.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
