package work.sam.expensesApp.mapper;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import work.sam.expensesApp.DTO.CategoryDTO;
import work.sam.expensesApp.entity.Category;
import work.sam.expensesApp.exception.MappingException;

import java.util.List;
import java.util.stream.Collectors;
@Component
public class CategoryMapper {
    public static CategoryDTO toDTO(Category category) {
        if (category == null) {
            throw new MappingException("Category is null", HttpStatus.NOT_FOUND);
        }
        CategoryDTO dto = new CategoryDTO();
        dto.setId(category.getId());
        dto.setName(category.getName());
        dto.setAccountId(category.getAccount() != null ? category.getAccount().getId() : null);
        dto.setExpenses(ExpenseMapper.toDTOList(category.getExpenses()));
        return dto;
    }

    public static List<CategoryDTO> toDTOList(List<Category> categories) {
        return categories.stream().map(CategoryMapper::toDTO).collect(Collectors.toList());
    }

}
