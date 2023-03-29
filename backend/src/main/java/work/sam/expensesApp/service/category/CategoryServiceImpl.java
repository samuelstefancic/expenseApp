package work.sam.expensesApp.service.category;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import work.sam.expensesApp.entity.Category;
import work.sam.expensesApp.exception.CategoryException;
import work.sam.expensesApp.repository.CategoryRepository;
@Service
@Transactional
public class CategoryServiceImpl implements CategoryService{

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    //Create category

    public Category createCategory(Category category) {
        try {
            return categoryRepository.save(category);
        } catch ( Exception e) {
            throw new CategoryException("Failed to create category : " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // Find the category

    public Category getCategory(Long id) {
        return categoryRepository.findById(id).orElseThrow(() ->
                new CategoryException("Category with id + " + id + " not found", HttpStatus.NOT_FOUND));
    }

    //Update


    public Category updateCategory(Long id, Category updatedCategory) {
        Category category = categoryRepository.findById(id).orElseThrow(() ->
                new CategoryException("Category with id " + id + " not found", HttpStatus.NOT_FOUND));
        category.setName(updatedCategory.getName());

        try {
            return categoryRepository.save(category);
        } catch (Exception e) {
            throw new CategoryException("Failed to update category with id : " + id, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    //Delete

    public void deleteCategory(Long id) {
        if (!categoryRepository.existsById(id)) {
            throw new CategoryException("Category with id " + id + " not found", HttpStatus.NOT_FOUND);
        }
        try {
            categoryRepository.deleteById(id);
        } catch (Exception e) {
            throw new CategoryException("Failed to delete account with id : " + id, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
