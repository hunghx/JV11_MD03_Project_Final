package ra.project.service.category;

import ra.project.dto.request.CategoryAdd;
import ra.project.dto.request.CategoryUpdate;
import ra.project.entity.Category;
import ra.project.service.IGenericService;

import java.util.List;

public interface ICategoryService extends IGenericService<Category,Long, CategoryAdd, CategoryUpdate> {
    List<Category> paginationList(String keyword, int page, int size);
    int countTotalPages(int size);
}
