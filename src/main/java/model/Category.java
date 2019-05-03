package model;

import javax.persistence.*;

import java.util.Set;

@Entity
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nameCategory;

    @OneToMany(mappedBy = "category")
    private Set<Blog> blogs;

    public Category(){
    }
    public Category(String name){

        this.nameCategory = name;
    }

    public Long getId() {

        return id;
    }

    public String getNameCategory() {

        return nameCategory;
    }

    public void setId(Long id) {

        this.id = id;
    }

    public void setNameCategory(String nameCategory)
    {
        this.nameCategory = nameCategory;
    }
    public Set<Blog> getBlogs()
    {
        return blogs;
    }

    public void setBlogs(Set<Blog> blogs) {

        this.blogs = blogs;
    }
}
