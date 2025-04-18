package ra.project.dao.product;

import ra.project.dao.IGenericDao;
import ra.project.entity.Product;

import java.util.List;

public interface IProductDao extends IGenericDao<Product, Long> {
    List<Product> paginationList(String keyword, int limit, int offset);
    boolean existByName(String name);
    boolean existByCategoryId(Long catId);
}
