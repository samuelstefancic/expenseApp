package work.sam.expensesApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import work.sam.expensesApp.entity.Description;
import work.sam.expensesApp.exception.DescriptionException;
import work.sam.expensesApp.repository.DescriptionRepository;
@Service
public class DescriptionService {

    private final DescriptionRepository descriptionRepository;

    @Autowired
    public DescriptionService(DescriptionRepository descriptionRepository) {
        this.descriptionRepository = descriptionRepository;
    }

    public Description createDescription(Description description) {
        try {
            return descriptionRepository.save(description);
        } catch (Exception e) {
            throw new DescriptionException("Failed to create description " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
