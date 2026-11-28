package ra.edmo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ra.edmo.model.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Page<Product> findAllByProNameContaining(String proName, Pageable pageable);

    Page<Product> findAllByProducerContaining(String producer, Pageable pageable);

    Page<Product> findAllByYearMakingGreaterThanEqual(Integer yearMakingIsGreaterThan, Pageable pageable);

    Page<Product> findAllByPriceGreaterThanEqual(Double priceIsGreaterThan, Pageable pageable);
}
