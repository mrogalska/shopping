package practices.shopping.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import practices.shopping.model.ProductEntity;
import practices.shopping.repository.ProductRepository;

import java.util.*;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ProductController {

    private final ProductRepository productRepository;

    public ProductController(final ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public List<ProductEntity> getAllProducts() {
        return this.productRepository.findAll();
    }

    @RequestMapping(value = "/products", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public ResponseEntity<ProductEntity> createProduct(@RequestBody ProductEntity productEntity) {
        return new ResponseEntity<>(this.productRepository.save(productEntity), HttpStatus.OK);

    }

    @RequestMapping(value = "/products/{id}", method = RequestMethod.GET)
    public ResponseEntity<Optional<ProductEntity>> getProductById(@PathVariable(value = "id") Long productId) {
        Optional<ProductEntity> productEntity = productRepository.findById(productId);
        return ResponseEntity.ok().body(productEntity);
    }


    @RequestMapping(value = "/products/{id}", method = RequestMethod.PUT)
    public ResponseEntity<ProductEntity> updateProduct(@PathVariable(value = "id") Long productId, @Validated @RequestBody ProductEntity productDetails) {
        Optional<ProductEntity> productEntityOptional = productRepository.findById(productId);
        ProductEntity productEntity;
        try {
            productEntity = productEntityOptional.get();
            productEntity.setName(productDetails.getName());
            productEntity.setCategory(productDetails.getCategory());
            productEntity.setPrice(productDetails.getPrice());
        } catch (NoSuchElementException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(this.productRepository.save(productEntity), HttpStatus.OK);
    }


    @RequestMapping(value = "/products/{id}", method = RequestMethod.DELETE)
    void deleteProduct(@PathVariable(value = "id") Long productId) {
        productRepository.deleteById(productId);
    }


}
