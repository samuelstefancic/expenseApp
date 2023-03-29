package work.sam.expensesApp.service.category;

import org.springframework.stereotype.Service;
import work.sam.expensesApp.entity.Category;
@Service
public interface CategoryService {
    Category createCategory(Category category);
    Category getCategory(Long id);
    Category updateCategory(Long id, Category updatedCategory);
    void deleteCategory(Long id);
}
