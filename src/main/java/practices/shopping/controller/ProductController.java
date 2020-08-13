package practices.shopping.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import practices.shopping.model.ListEntity;
import practices.shopping.model.ProductEntity;
import practices.shopping.model.ProductsOnList;
import practices.shopping.repository.ListRepository;
import practices.shopping.repository.ProductRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ProductController {

    private final ProductRepository productRepository;
    private final ListRepository listRepository;

    public ProductController(final ProductRepository productRepository, final ListRepository listRepository) {
        this.productRepository = productRepository;
        this.listRepository = listRepository;
    }


    
    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public ResponseEntity<List<ProductEntity>> getAllProducts() {
        return new ResponseEntity<>(this.productRepository.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/products/{id}", method = RequestMethod.GET)
    public ResponseEntity<ProductEntity> getProductById(@PathVariable(value = "id") Long productId) {
        Optional<ProductEntity> productEntityOptional = productRepository.findById(productId);
        ProductEntity productEntity = productEntityOptional.get();
        return new ResponseEntity<>(productEntity, HttpStatus.OK);
    }




    @RequestMapping(value = "/products", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public ResponseEntity<ProductEntity> createProduct(@RequestBody ProductEntity productEntity) {
        return new ResponseEntity<>(this.productRepository.save(productEntity), HttpStatus.OK);
    }
    




    @RequestMapping(value = "/products/{id}", method = RequestMethod.PUT)
    public ResponseEntity<ProductEntity> updateProduct(@PathVariable(value = "id") Long productId,
                                                       @Validated @RequestBody ProductEntity productDetails) {
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
