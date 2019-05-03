package controller;


import model.Blog;
import model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import service.BlogService;
import service.CategoryService;

@Controller
public class CategoryController {
    @Autowired
    public CategoryService categoryService;
    @Autowired
    public BlogService blogService;
    @GetMapping("/categorys")
    public ModelAndView listCateogrys(){
        Iterable<Category> categorys = categoryService.findAll();
        ModelAndView modelAndView = new ModelAndView("/category/list");
        modelAndView.addObject("categorys",categorys);
        return modelAndView;
    }
    @GetMapping("/create-category")
    public ModelAndView createCategory(){
        ModelAndView modelAndView = new ModelAndView("/category/create");
        modelAndView.addObject("category",new Category());
        return modelAndView;
    }
    @PostMapping("/create-category")
    public ModelAndView saveCategory(@ModelAttribute("category") Category category){
        categoryService.save(category);
        ModelAndView modelAndView = new ModelAndView("/category/create");
        modelAndView.addObject("category",new Category());
        modelAndView.addObject("message","New Category created successfully");
        return modelAndView;
    }
    @GetMapping("/edit-category/{id}")
    public ModelAndView editCategoryForm(@PathVariable Long id){
        Category category = categoryService.findById(id );
        if (category!=null){
            ModelAndView modelAndView = new ModelAndView("/category/edit");
            modelAndView.addObject("category",category);
            return modelAndView;
        }
        else {
            ModelAndView modelAndView = new ModelAndView("/blog/error.404");
            return modelAndView;
        }
    }

    @PostMapping("/edit-category")
    public ModelAndView updateCategory(@ModelAttribute("category") Category category){
        categoryService.save(category);
        ModelAndView modelAndView = new ModelAndView("/category/edit");
        modelAndView.addObject("category",category);
        modelAndView.addObject("message","New Category update successfully");
        return modelAndView;
    }
    @GetMapping("delete-category/{id}")
    public ModelAndView deleteCategoryForm(@PathVariable Long id){
        Category category = categoryService.findById(id);
        if (category!=null){
            ModelAndView modelAndView = new ModelAndView("/category/delete");
            modelAndView.addObject("category",category);
            return modelAndView;
        }
        else {
            ModelAndView modelAndView = new ModelAndView("/blog/error.404");
            return modelAndView;
        }
    }
    @PostMapping("delete-category")
    public String deleteCategory(@ModelAttribute("category") Category category){
        categoryService.remove(category.getId());
        return "redirect:categorys";
    }
    @GetMapping("/view-category/{id}")
    public ModelAndView viewCategory(@PathVariable("id") Long id){
        Category category = categoryService.findById(id);
        if(category == null){
            return new ModelAndView("/error.404");
        }

        Iterable<Blog> blogs = blogService.findAllByCategory(category);

        ModelAndView modelAndView = new ModelAndView("/category/view");
        modelAndView.addObject("category", category);
        modelAndView.addObject("blogs",blogs);
        return modelAndView;
    }
}
