package practices.shopping.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Embeddable
public class ProductsOnListKey implements Serializable {

    @Column(name = "product_id")
    Long productId;

    @Column(name = "list_id")
    Long listId;

}
