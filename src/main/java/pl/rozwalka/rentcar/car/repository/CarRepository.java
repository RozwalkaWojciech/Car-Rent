package pl.rozwalka.rentcar.car.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.rozwalka.rentcar.car.entity.Car;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

    Optional<Car> findCarByBrand(String brand);

    @Query("SELECT c FROM Car c WHERE c.brand LIKE %:like% OR c.model LIKE %:like%")
    List<Car> findByBrandOrModelLike(@Param("like") String like);
}
