package practices.shopping.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import practices.shopping.model.Product;

public interface ProductRepository extends CrudRepository<Product, Long>{
    List<Product> findByProductName(String productName);
}
