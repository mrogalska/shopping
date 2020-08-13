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


    public ProductsOnListKey getId() {
        return id;
    }

    public void setId(ProductsOnListKey id) {
        this.id = id;
    }

    public ProductEntity getProductEntity() {
        return productEntity;
    }

    public void setProductEntity(ProductEntity productEntity) {
        this.productEntity = productEntity;
    }

    public ListEntity getListEntity() {
        return listEntity;
    }

    public void setListEntity(ListEntity listEntity) {
        this.listEntity = listEntity;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }


}
