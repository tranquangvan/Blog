package service.impl;

import model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import repository.CategoryRepoitory;
import service.CategoryService;

public class CategoryServiceImpl implements CategoryService {


    @Autowired
    private CategoryRepoitory categoryRepoitory;

    @Override
    public Iterable<Category> findAll(){
        return categoryRepoitory.findAll();
    }

    @Override
     public Category findById(Long id){
        return categoryRepoitory.findOne(id);
    }

    @Override
    public void save(Category category){
        categoryRepoitory.save(category);
    }
    @Override
    public void remove(Long id){
        categoryRepoitory.delete(id);
    }
}
