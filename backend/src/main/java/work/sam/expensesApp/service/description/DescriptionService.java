package work.sam.expensesApp.service.description;

import org.springframework.stereotype.Service;
import work.sam.expensesApp.entity.Description;

@Service
public interface DescriptionService {

    Description createDescription(Description description);

    Description findDescriptionById(Long descriptionId);

    Description updateDescriptionById(Long descriptionId,Description updateDescription);

    void deleteDescriptionById(Long descriptionId);
}
