package controller;

import model.Blog;
import model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import service.BlogService;
import service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@Controller
public class ControllerWriteBlog {
    @Autowired
    private BlogService blogService;
    @Autowired
    private CategoryService categoryService;
    @ModelAttribute("categorys")
    public Iterable<Category> categorys(){
        return categoryService.findAll();
    }
@GetMapping("blog-list")
public ModelAndView listBlog(@RequestParam("s")Optional<String> s,Pageable pageable){
        Page<Blog> blogs;
    if(s.isPresent()){
        blogs = blogService.findAllByDescribesContaining(s.get(), pageable);
    } else {
        blogs = blogService.findAll(pageable);
    }
    ModelAndView modelAndView = new ModelAndView("/blog/list");
    modelAndView.addObject("bloglist", blogs);
    return modelAndView;
}

@GetMapping("/create-blog")
    public ModelAndView CreateForm(){
    ModelAndView modelAndView = new ModelAndView("/blog/create");
    modelAndView.addObject("blog",new Blog());
    return modelAndView;
}
@PostMapping("create-blog")
    public ModelAndView saveBlog(@ModelAttribute("blog") Blog blog){
    blogService.save(blog);
    ModelAndView modelAndView = new ModelAndView("/blog/create");
    modelAndView.addObject("blog",new Blog());
    modelAndView.addObject("message","New Blog create successfully");
    return modelAndView;
    }

    @GetMapping("/edit-blog/{id}")
    public ModelAndView showEdit(@PathVariable Long id){
       Blog blog=blogService.findById(id);
        if(blog != null) {
            ModelAndView modelAndView = new ModelAndView("/blog/edit");
            modelAndView.addObject("blog", blog);
            return modelAndView;

        }else {
            ModelAndView modelAndView = new ModelAndView("/blog/error.404");
            return modelAndView;
        }
    }
    @PostMapping("/edit-blog")
    public ModelAndView updateBlog(@ModelAttribute("blog") Blog blog){
        blogService.save(blog);
        ModelAndView modelAndView = new ModelAndView("/blog/edit");
        modelAndView.addObject("blog", blog);
        modelAndView.addObject("message", "Blog updated successfully");
        return modelAndView;
    }
    @GetMapping("/delete-blog/{id}")
    public ModelAndView showDelete(@PathVariable Long id){
        Blog blog = blogService.findById(id);
        if(blog != null) {
            ModelAndView modelAndView = new ModelAndView("/blog/delete");
            modelAndView.addObject("blog", blog);
            return modelAndView;

        }else {
            ModelAndView modelAndView = new ModelAndView("/blog/error.404");
            return modelAndView;
        }
    }
    @PostMapping("/delete-blog")
    public String deleteBlog(@ModelAttribute("blog") Blog blog){
    blogService.remove(blog.getId());
        return "redirect:blog-list";
    }
    @GetMapping("/view-blog/{id}")
    public ModelAndView showBlog(@PathVariable Long id){
        Blog blog=blogService.findById(id);
        if(blog != null) {
            ModelAndView modelAndView = new ModelAndView("/blog/view");
            modelAndView.addObject("blog", blog);
            return modelAndView;

        }else {
            ModelAndView modelAndView = new ModelAndView("/blog/error.404");
            return modelAndView;
        }
    }


}
