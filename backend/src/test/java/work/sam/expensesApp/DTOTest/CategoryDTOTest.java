package work.sam.expensesApp.DTOTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import work.sam.expensesApp.DTO.CategoryDTO;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CategoryDTOTest {

    private CategoryDTO categoryDTO;

    @BeforeEach
    public void setUp() {
        categoryDTO = new CategoryDTO();
        categoryDTO.setName("Groceries");
    }

    @Test
    public void testCategoryDTOProperties() {
        assertEquals("Groceries", categoryDTO.getName());
    }
}
