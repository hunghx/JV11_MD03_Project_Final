package ra.project.dto.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductAdd {
    private String productName;
    private String description;
    private BigDecimal unitPrice;
    private int quantity;
    private MultipartFile image;
    private Long categoryId;
}
