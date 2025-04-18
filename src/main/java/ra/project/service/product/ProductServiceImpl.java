package ra.project.service.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.project.dao.category.ICategoryDao;
import ra.project.dao.product.IProductDao;
import ra.project.dto.request.ProductAdd;
import ra.project.dto.request.ProductUpdate;
import ra.project.entity.Product;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class ProductServiceImpl implements IProductService {
    @Autowired
    private IProductDao productDao;
    @Autowired
    private ICategoryDao categoryDao;
    @Override
    public List<Product> findAll() {
      return productDao.findAll();
    }

    @Override
    public List<Product> paginationList(String keyword, int page, int size) {
        return productDao.paginationList(keyword,size, page*size);
    }

    @Override
    public int countTotalPages(int size) {
        int totalElements = productDao.findAll().size();
        int mod = totalElements % size;
        int total = totalElements/size;
        return mod==0?total:total+1;
    }

    @Override
    public Product findById(Long id) {
        return productDao.findById(id);
    }
    @Transactional
    @Override
    public void create(ProductAdd request) {
        // upload file
        String uploadPath = "/upload/"+request.getImage().getOriginalFilename();
        // map từ dto -> entity
        Product pro = new Product(
            null, UUID.randomUUID().toString(),
                request.getProductName(),
                request.getDescription(),
                request.getUnitPrice(),
                request.getQuantity(),
                uploadPath,
                categoryDao.findById(request.getCategoryId()),
                LocalDate.now(),
                null
        );
        productDao.save(pro);
    }
    @Transactional
    @Override
    public void update(ProductUpdate request, Long id) {
        // upload file
        String uploadPath = "/upload/"+request.getImage().getOriginalFilename();
        Product entity = productDao.findById(id);
        // map từ dto -> entity
        Product pro = new Product(id, entity.getSku(),
                request.getProductName(),
                request.getDescription(),
                request.getUnitPrice(),
                request.getQuantity(),
                uploadPath,
                categoryDao.findById(request.getCategoryId()),
                entity.getCreatedAt(),
                LocalDate.now()
        );
        productDao.save(pro);
    }
    @Transactional
    @Override
    public void delete(Long id) {
        productDao.delete(id);
    }

    @Override
    public boolean existProductByCategoryId(Long id) {
        return productDao.existByCategoryId(id);
    }
}
