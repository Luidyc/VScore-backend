package Lexus.Score.dev.Repository;

import Lexus.Score.dev.Entity.Product.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Number> {
}
