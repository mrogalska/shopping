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
    // decorate with ResponseEntity
    public List<ListEntity> getAllLists() {
        return this.listRepository.findAll();
    }


    @RequestMapping(value = "/lists", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public ResponseEntity<ListEntity> createList(@RequestBody ListEntity listEntity) {
        // make sure the list does not have id, otherwise it may exist in database and save() method will update the existing record
        return new ResponseEntity<>(this.listRepository.save(listEntity), HttpStatus.OK);
    }


    @RequestMapping(value = "/lists/{id}", method = RequestMethod.GET)
    // definitely do not return optional
    public ResponseEntity<Optional<ListEntity>> getListById(@PathVariable(value = "id") Long listId) {
        Optional<ListEntity> listEntity = listRepository.findById(listId);
        return ResponseEntity.ok().body(listEntity);
    }


    @RequestMapping(value = "/lists/{listId}/products/add/{productId}", method = RequestMethod.PUT)
    // more like addProductToList, add also deleteProductFromList - deletion is also handled by UPDATING a list entity
    public ResponseEntity<ListEntity> updateList(@PathVariable(value = "listId") Long listId,
                                                 @PathVariable(value = "productId") Long productId,
                                                 @Validated @RequestBody ListEntity listDetails) {
        Optional<ListEntity> listEntityOptional = listRepository.findById(listId);
        // not used, alt + enter on the "productEntityOptional" phrase and remove
        Optional<ProductEntity> productEntityOptional = productRepository.findById(productId);
        ListEntity listEntity;
        ProductEntity productEntity;
        try {
            listEntity = listEntityOptional.get();
//            productEntity = productEntityOptional.get();
            ProductsOnList newProduct = new ProductsOnList();
            // newProduct does not have an amount to set the amount in itself
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