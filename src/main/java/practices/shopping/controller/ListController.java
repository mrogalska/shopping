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
public class ListController {

    private final ProductRepository productRepository;
    private final ListRepository listRepository;

    public ListController(final ProductRepository productRepository, final ListRepository listRepository) {
        this.productRepository = productRepository;
        this.listRepository = listRepository;
    }


    @RequestMapping(value = "/lists", method = RequestMethod.GET)
    public ResponseEntity<List<ListEntity>> getAllLists() {
        return new ResponseEntity<>(this.listRepository.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/lists/{id}", method = RequestMethod.GET)
    public ResponseEntity<ListEntity> getListById(@PathVariable(value = "id") Long listId) {
        Optional<ListEntity> listEntityOptional = listRepository.findById(listId);
        ListEntity listEntity = listEntityOptional.get();
        return new ResponseEntity<>(listEntity, HttpStatus.OK);
    }


    @RequestMapping(value = "/lists", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public ResponseEntity<ListEntity> createList(@RequestBody ListEntity listEntity) {
        return new ResponseEntity<>(this.listRepository.save(listEntity), HttpStatus.OK);
    }


    @RequestMapping(value = "/lists/{listId}/products/add/{productId}", method = RequestMethod.PUT)
    public ResponseEntity<ListEntity> updateList(@PathVariable(value = "listId") Long listId,
            @PathVariable(value = "productId") Long productId, @Validated @RequestBody ListEntity listDetails) {
        Optional<ListEntity> listEntityOptional = listRepository.findById(listId);
        Optional<ProductEntity> productEntityOptional = productRepository.findById(productId);
        ListEntity listEntity;
        ProductEntity productEntity;
        try {
            listEntity = listEntityOptional.get();
            // productEntity = productEntityOptional.get();
            ProductsOnList newProduct = new ProductsOnList();
            newProduct.setAmount(newProduct.getAmount());
            newProduct.setListEntity(listEntity);
            newProduct.setProductEntity(new ProductEntity(productId));
            listEntity.getProductsOnLists().add(newProduct);

        } catch (NoSuchElementException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(this.listRepository.save(listEntity), HttpStatus.OK);
    }

    @RequestMapping(value = "/list/{id}", method = RequestMethod.DELETE)
    void deleteList(@PathVariable(value = "id") Long listId) {
        listRepository.deleteById(listId);
    }
}
