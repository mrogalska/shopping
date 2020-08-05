package practices.shopping.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import practices.shopping.model.Product;
import practices.shopping.repository.ProductRepository;

@RestController
public class AppController {
    @Autowired
    ProductRepository repository;

    @RequestMapping("/products")
    public String process(){
        repository.save(new Product("milk", 2, 3.0));
        repository.save(new Product("apple", 3, 1.2));
        return "Done";
    }

    @RequestMapping(value= "/products", method = RequestMethod.GET)
    public String findAll(){
        String result = "<html>";
        for(Product prod : repository.findAll()){
            result += "<div>" + prod.toString() + "</div>";
        }
        return result;
    }
}
