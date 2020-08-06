package practices.shopping.controller;

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
public class ProductController {

    private final ProductRepository productRepository;

    public ProductController(final ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public List<ProductEntity> getAllProducts(){
        return this.productRepository.findAll();
    }

    @RequestMapping(value = "/products", method = RequestMethod.POST)
    public ProductEntity createProduct(@RequestBody ProductEntity productEntity) {
        return this.productRepository.save(productEntity);
    }

    @RequestMapping(value = "/products/{id}", method = RequestMethod.GET)
    public ResponseEntity<Optional<ProductEntity>> getProductById(@PathVariable(value = "id") Long productId){
        Optional<ProductEntity> productEntity = productRepository.findById(productId);
        return ResponseEntity.ok().body(productEntity);
    }

    @RequestMapping(value = "/products/{id}", method = RequestMethod.PUT)
    public ResponseEntity <ProductEntity> updateProduct(@PathVariable(value = "id") Long productId, @Validated @RequestBody ProductEntity productDetails){
        ProductEntity productEntity = productRepository.getOne(productId);

        if (productDetails.getProductName() != null){
            productEntity.setProductName(productDetails.getProductName());
        }
        if (productDetails.getPrice() != 0){
            productEntity.setPrice(productDetails.getPrice());
        }
        if (productDetails.getAmount() != 0){
            productEntity.setAmount(productDetails.getAmount());
        }


        return ResponseEntity.ok(this.productRepository.save(productEntity));
    }



    @RequestMapping(value = "/products/{id}", method = RequestMethod.DELETE)
    void deleteProduct(@PathVariable(value = "id") Long productId) {
        productRepository.deleteById(productId);
    }

}
