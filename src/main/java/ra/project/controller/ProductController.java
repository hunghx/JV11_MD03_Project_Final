package ra.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ra.project.dto.request.CategoryAdd;
import ra.project.dto.request.CategoryUpdate;
import ra.project.dto.request.ProductAdd;
import ra.project.dto.request.ProductUpdate;
import ra.project.entity.Category;
import ra.project.entity.Product;
import ra.project.service.category.ICategoryService;
import ra.project.service.product.IProductService;

import javax.validation.Valid;

@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ICategoryService categoryService;
    @Autowired
    private IProductService productService;
    // điều hướng sang trang list
    @GetMapping({"","/list"})
    public String list(@RequestParam(value = "page", defaultValue = "0") int page,
                       @RequestParam(value = "size", defaultValue = "5") int size,
                       @RequestParam(value = "keyword", defaultValue = "") String keyword,
                       Model model){
        model.addAttribute("products", productService.paginationList(keyword,page,size));
        model.addAttribute("page", page);
        model.addAttribute("size", size);
        model.addAttribute("totalPages",productService.countTotalPages(size));
        return "product/list";
    }
    // điều hướng sang trang add
    @GetMapping("/new")
    public String showAddForm(Model model){
        model.addAttribute("product", new ProductAdd());
        model.addAttribute("categories",categoryService.findAll());
        return "product/add";
    }
    // xuwr lý thêm mơi
    @PostMapping("/add")
    public String doAdd( @ModelAttribute("product") ProductAdd request ){

        productService.create(request);
        return "redirect:/product";
    }
    // điều hướng sang trang edit
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model){
        Product entity = productService.findById(id);
        ProductUpdate dto = new ProductUpdate(entity.getProductName(), entity.getDescription(),entity.getUnitPrice(),entity.getQuantity(),null, entity.getCategory().getCategoryId());
        model.addAttribute("product", dto);
        model.addAttribute("id", id);
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("imageUrl", entity.getImage());
        return "product/edit";
    }

    @PostMapping("/update")
    public String doUpdate( @ModelAttribute("product")ProductUpdate request,
                           @RequestParam("productId") Long id){
        productService.update(request, id);
        return "redirect:/product";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id){
        productService.delete(id);
        return "redirect:/product";
    }

}
