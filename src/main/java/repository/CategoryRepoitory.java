package repository;

import model.Category;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CategoryRepoitory extends PagingAndSortingRepository<Category,Long> {
}
