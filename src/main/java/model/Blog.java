package model;
import javax.persistence.*;
@Entity
@Table(name = "Blog")
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String describes;
    private String content;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;


    public Blog(){}

    public Blog(String describe, String content,Category category){
        this.describes = describe;
        this.content = content;
        this.category = category;

    }

    @Override
        public String toString() {
            return String.format("Blog[id=%d, describes='%s', content='%s']", id, describes, content);
        }

    public Long getId() {
        return id;
    }

    public String getDescribes() {
        return describes;
    }

    public String getContent() {
        return content;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDescribes(String describes) {
        this.describes = describes;
    }

    public void setContent(String content) {
        this.content = content;
    }
    public Category getCategory(){
        return category;
    }
    public void setCategory(Category category){
        this.category = category;
    }
}
