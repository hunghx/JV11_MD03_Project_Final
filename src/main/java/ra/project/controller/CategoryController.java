package ra.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ra.project.dto.request.CategoryAdd;
import ra.project.dto.request.CategoryUpdate;
import ra.project.entity.Category;
import ra.project.exception.CategoryNotDeleteException;
import ra.project.service.category.ICategoryService;
import ra.project.service.product.IProductService;

import javax.validation.Valid;
import java.util.Objects;

@Controller
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private ICategoryService categoryService;
    @Autowired
    private IProductService productService;
    // điều hướng sang trang list
    @GetMapping({"","/list"})
    public String list(@RequestParam(value = "page", defaultValue = "0") int page,
                       @RequestParam(value = "size", defaultValue = "5") int size,
                       @RequestParam(value = "keyword", defaultValue = "") String keyword,
                       @RequestParam(value = "error", defaultValue = "") String message,
                       Model model){
        model.addAttribute("categories", categoryService.paginationList(keyword,page,size));
        model.addAttribute("page", page);
        model.addAttribute("size", size);
        model.addAttribute("totalPages",categoryService.countTotalPages(size));
        if (!Objects.equals(message, "")){
            model.addAttribute("error", message);
        }
        return "category/list";
    }
    // điều hướng sang trang add
    @GetMapping("/new")
    public String showAddForm(Model model){
        model.addAttribute("category", new CategoryAdd());
        return "category/add";
    }
    // xuwr lý thêm mơi
    @PostMapping("/add")
    public String doAdd(@Valid @ModelAttribute("category") CategoryAdd request, BindingResult result, Model model){
        if (result.hasErrors()){
            model.addAttribute("category", request);
            return "category/add";
        }
        categoryService.create(request);
        return "redirect:/category";
    }
    // điều hướng sang trang edit
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model){
        Category entity = categoryService.findById(id);
        CategoryUpdate dto = new CategoryUpdate(entity.getCategoryName(), entity.getDescription(),entity.getStatus());
        model.addAttribute("category", dto);
        model.addAttribute("id", id);
        return "category/edit";
    }

    @PostMapping("/update")
    public String doUpdate(@Valid @ModelAttribute("category")CategoryUpdate request,
            @RequestParam("categoryId") Long id, BindingResult result, Model model){
        if (result.hasErrors()){
            model.addAttribute("category", request);
            return "category/edit";
        }
        categoryService.update(request, id);
        return "redirect:/category";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id){
        if(productService.existProductByCategoryId(id)){
            return "redirect:/category?error=true";
        }
        categoryService.delete(id);
        return "redirect:/category";
    }

}
