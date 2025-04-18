package ra.project.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ra.project.dao.category.ICategoryDao;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
@Component
public class CategoryValidator implements ConstraintValidator<CategoryUnique,String> {
    @Autowired
    private ICategoryDao categoryDao;
    @Override
    public void initialize(CategoryUnique categoryUnique) {

    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return !categoryDao.existByName(s);
    }
}
