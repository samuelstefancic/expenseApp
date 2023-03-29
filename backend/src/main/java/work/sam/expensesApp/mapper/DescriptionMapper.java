package work.sam.expensesApp.mapper;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import work.sam.expensesApp.DTO.DescriptionDTO;
import work.sam.expensesApp.entity.Description;
import work.sam.expensesApp.exception.DescriptionException;
@Component
public class DescriptionMapper {

    public static DescriptionDTO toDTO(Description description) {
        if (description == null) {
            throw new DescriptionException("Description is null", HttpStatus.NOT_FOUND);
        }
        DescriptionDTO dto = new DescriptionDTO();
        dto.setId(description.getId());
        dto.setText(description.getText());
        dto.setUpdatedAt(description.getUpdatedAt());

        return dto;
    }
}
