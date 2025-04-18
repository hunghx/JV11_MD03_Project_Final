package ra.project.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(
        validatedBy = {CategoryValidator.class}
)
@Target({ ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CategoryUnique {
    String message() default "Tên danh mục đã tôn tại";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
