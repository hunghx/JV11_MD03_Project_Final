package ra.project.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;
import ra.project.validator.CategoryUnique;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
public class CategoryUpdate {
    @NotBlank(message = "Không được đc trống!")
    @CategoryUnique
    private String categoryName;
    @NotBlank(message = "Không được đc trống!")
    private String description;
    @NotNull(message = "Ko được bỏ trống")
    private Boolean status;
}
