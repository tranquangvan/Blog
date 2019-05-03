package service.impl;

import model.Blog;
import model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import repository.BlogRepository;
import service.BlogService;

public class BlogServiceImpl implements BlogService {
    @Autowired
    private BlogRepository blogRepository;

    @Override
    public Iterable<Blog> findAll(){
        return blogRepository.findAll();
    }
    @Override
    public Blog findById(Long id){

        return blogRepository.findOne(id);
    }
    @Override
    public void save(Blog blog){
        blogRepository.save(blog);
    }
    @Override
    public void remove(Long id){
        blogRepository.delete(id);
    }
    @Override
    public Iterable<Blog> findAllByCategory(Category category){
        return blogRepository.findAllByCategory(category);
    }
    @Override
    public Page<Blog> findAll(Pageable pageable){
        return blogRepository.findAll(pageable);
    }
    @Override
    public Page<Blog>  findAllByDescribesContaining(String content,Pageable pageable){
        return blogRepository.findAllByDescribesContaining(content,pageable);
    }
}
