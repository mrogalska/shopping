package practices.shopping.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductsOnList {

    @EmbeddedId
    ProductsOnListKey id;

    @ManyToOne
    @MapsId("product_id")
    @JoinColumn(name = "product_id")
    ProductEntity productEntity;

    @ManyToOne
    @MapsId("list_id")
    @JoinColumn(name = "list_id")
    ListEntity listEntity;

    int amount;
}
