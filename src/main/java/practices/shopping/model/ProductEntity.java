package practices.shopping.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "products")
@AllArgsConstructor
@NoArgsConstructor
@Data

public class ProductEntity implements Serializable {


    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private long id;

    @Column(length = 500)
    private String name;
    //lookup double disadvantages
    @Column(columnDefinition = "default double 0.0")
    private double price;

    @Column()
    private String category;

    //onLists or onList?
    @OneToMany(mappedBy = "productEntity")
    Set<ProductsOnList> productsOnLists;

    public ProductEntity(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Set<ProductsOnList> getProductsOnLists() {
        return productsOnLists;
    }

    public void setProductsOnLists(Set<ProductsOnList> productsOnLists) {
        this.productsOnLists = productsOnLists;
    }
}
