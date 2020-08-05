package practices.shopping.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import practices.shopping.model.ProductEntity;
import practices.shopping.repository.ProductRepository;

import java.util.List;


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

    @RequestMapping (value = "/products", method = RequestMethod.POST)
    public ProductEntity createProduct(@RequestBody ProductEntity productEntity) {
        return this.productRepository.save(productEntity);
    }

}
