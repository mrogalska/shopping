package practices.shopping.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "lists")
@AllArgsConstructor
@NoArgsConstructor
@Data

public class ListEntity {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private long id;

    private String name;

    @OneToMany(mappedBy = "listEntity")
    Set<ProductsOnList> productsOnLists;
}
