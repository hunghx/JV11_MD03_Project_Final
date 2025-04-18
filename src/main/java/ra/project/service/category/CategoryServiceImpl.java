package ra.project.service.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.project.dao.category.ICategoryDao;
import ra.project.dao.product.IProductDao;
import ra.project.dto.request.CategoryAdd;
import ra.project.dto.request.CategoryUpdate;
import ra.project.entity.Category;
import ra.project.exception.CategoryNotDeleteException;

import javax.transaction.Transactional;
import java.util.List;
@Service
public class CategoryServiceImpl implements ICategoryService{
    @Autowired
    private ICategoryDao categoryDao;
    @Autowired
    private IProductDao productDao;
    @Override
    public List<Category> findAll() {
      return categoryDao.findAll();
    }

    @Override
    public List<Category> paginationList(String keyword, int page, int size) {
        return categoryDao.paginationList(keyword,size, page*size);
    }

    @Override
    public int countTotalPages(int size) {
        int totalElements = categoryDao.findAll().size();
        int mod = totalElements % size;
        int total = totalElements/size;
        return mod==0?total:total+1;
    }

    @Override
    public Category findById(Long id) {
        return categoryDao.findById(id);
    }
    @Transactional
    @Override
    public void create(CategoryAdd categoryAdd) {
        // map từ dto -> entity
        Category cat = new Category(
                null,
                categoryAdd.getCategoryName(),
                categoryAdd.getDescription(),
                true
        );
        categoryDao.save(cat);
    }
    @Transactional
    @Override
    public void update(CategoryUpdate categoryUpdate, Long id) {
        Category cat = new Category(
                id,
                categoryUpdate.getCategoryName(),
                categoryUpdate.getDescription(),
                categoryUpdate.getStatus()
        );
        categoryDao.save(cat);
    }
    @Transactional
    @Override
    public void delete(Long id) {
        categoryDao.delete(id);

    }
}
