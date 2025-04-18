package ra.project.dto.request;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;
import ra.project.validator.CategoryUnique;

@Getter
@Setter
public class CategoryAdd {
    @NotBlank(message = "Không được đc trống!")
    @CategoryUnique
    private String categoryName;
    @NotBlank(message = "Không được đc trống!")
    private String description;
}
