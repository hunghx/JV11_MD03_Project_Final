package ra.project.dao.category;

import org.springframework.stereotype.Repository;
import ra.project.entity.Category;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
@Repository
public class CategoryDaoImpl implements ICategoryDao{
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public List<Category> paginationList(String keyword, int limit, int offset) {
        return entityManager.createQuery("FROM Category C WHERE C.categoryName like :key", Category.class)
                .setParameter("key", "%"+keyword+"%")
                .setFirstResult(offset)
                .setMaxResults(limit)
                .getResultList();
    }

    @Override
    public List<Category> findAll() {
        return entityManager.createQuery("FROM Category", Category.class).getResultList();
    }

    @Override
    public boolean existByName(String name) {
        return !entityManager.createQuery("FROM Category  C where  C.categoryName like :name")
                .setParameter("name", name)
                .getResultList().isEmpty();
    }

    @Override
    public Category findById(Long id) {
        return entityManager.find(Category.class,id);
    }

    @Override
    public void save(Category category) {
        if (category.getCategoryId()==null){
            // thêm mới
            entityManager.persist(category);
        }else{
            entityManager.merge(category);
        }
    }

    @Override
    public void delete(Long id) {
        entityManager.remove(findById(id));
    }
}
