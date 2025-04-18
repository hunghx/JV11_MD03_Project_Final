package ra.project.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long productId;
    @Column(length = 100, unique = true)
    private String sku;
    @Column(name = "product_name", length = 100, nullable = false, unique = true)
    private String productName;
    @Column(columnDefinition = "text")
    private String description;
    @Column(name = "unit_price",precision = 10, scale = 2)
    private BigDecimal unitPrice;
    @Column(name = "stock_quantity", columnDefinition = "INT check(stock_quantity >= 0)")
    private int quantity;
    private String image;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    @Column(name = "created_at")
    private LocalDate createdAt;
    @Column(name = "updated_at")
    private LocalDate updateAt;
}
