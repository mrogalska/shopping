package practices.shopping.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

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

    @Column()
    private double price;

    @Column()
    private String category;

}
