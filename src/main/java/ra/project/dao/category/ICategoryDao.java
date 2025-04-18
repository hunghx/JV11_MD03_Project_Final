package ra.project.dao.category;

import ra.project.dao.IGenericDao;
import ra.project.entity.Category;

import java.util.List;

public interface ICategoryDao extends IGenericDao<Category, Long> {
    List<Category> paginationList(String keyword, int limit, int offset);
    boolean existByName(String name);
}
