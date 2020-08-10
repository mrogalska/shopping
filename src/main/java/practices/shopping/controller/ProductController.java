package practices.shopping.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import practices.shopping.model.ProductEntity;
import practices.shopping.repository.ProductRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ProductController {

    private final ProductRepository productRepository;

    public ProductController(final ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public List<ProductEntity> getAllProducts(){
        return this.productRepository.findAll();
    }

    @RequestMapping(value = "/products", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public ResponseEntity<ProductEntity> createProduct(@RequestBody ProductEntity productEntity) {
        return new ResponseEntity<>(this.productRepository.save(productEntity), HttpStatus.OK);

    }

    @RequestMapping(value = "/products/{id}", method = RequestMethod.GET)
    public ResponseEntity<Optional<ProductEntity>> getProductById(@PathVariable(value = "id") Long productId){
        Optional<ProductEntity> productEntity = productRepository.findById(productId);
        return ResponseEntity.ok().body(productEntity);
    }


    @RequestMapping(value = "/products/{id}", method = RequestMethod.PUT)
    public ResponseEntity <ProductEntity> updateProduct(@PathVariable(value = "id") Long productId, @Validated @RequestBody ProductEntity productDetails){
        ProductEntity productEntity = productRepository.getOne(productId);

        productEntity.setName(productDetails.getName());
        productEntity.setPrice(productDetails.getPrice());
        productEntity.setAmount(productDetails.getAmount());

        return ResponseEntity.ok(this.productRepository.save(productEntity));
    }



    @RequestMapping(value = "/products/{id}", method = RequestMethod.DELETE)
    void deleteProduct(@PathVariable(value = "id") Long productId) {
        productRepository.deleteById(productId);
    }


}
