package ra.project.dao.product;

import org.springframework.stereotype.Repository;
import ra.project.entity.Product;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
@Repository
public class ProductDaoImpl implements IProductDao {
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public List<Product> paginationList(String keyword, int limit, int offset) {
        return entityManager.createQuery("FROM Product C WHERE C.productName like :key", Product.class)
                .setParameter("key", "%"+keyword+"%")
                .setFirstResult(offset)
                .setMaxResults(limit)
                .getResultList();
    }

    @Override
    public List<Product> findAll() {
        return entityManager.createQuery("FROM Product", Product.class).getResultList();
    }

    @Override
    public boolean existByName(String name) {
        return !entityManager.createQuery("FROM Product  C where  C.productName like :name")
                .setParameter("name", name)
                .getResultList().isEmpty();
    }

    @Override
    public boolean existByCategoryId(Long catId) {
        return !entityManager.createQuery("FROM Product P WHERE P.category.categoryId = :catId")
                .setParameter("catId", catId)
                .getResultList().isEmpty();
    }

    @Override
    public Product findById(Long id) {
        return entityManager.find(Product.class,id);
    }

    @Override
    public void save(Product product) {
        if (product.getProductId()==null){
            // thêm mới
            entityManager.persist(product);
        }else{
            entityManager.merge(product);
        }
    }

    @Override
    public void delete(Long id) {
        entityManager.remove(findById(id));
    }
}
