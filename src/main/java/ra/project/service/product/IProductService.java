package ra.project.service.product;

import ra.project.dto.request.CategoryAdd;
import ra.project.dto.request.CategoryUpdate;
import ra.project.dto.request.ProductAdd;
import ra.project.dto.request.ProductUpdate;
import ra.project.entity.Category;
import ra.project.entity.Product;
import ra.project.service.IGenericService;

import java.util.List;

public interface IProductService extends IGenericService<Product,Long, ProductAdd, ProductUpdate> {
    List<Product> paginationList(String keyword, int page, int size);
    int countTotalPages(int size);
    boolean existProductByCategoryId(Long id);
}
