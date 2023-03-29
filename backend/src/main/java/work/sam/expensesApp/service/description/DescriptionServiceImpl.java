package work.sam.expensesApp.service.description;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import work.sam.expensesApp.entity.Description;
import work.sam.expensesApp.exception.DescriptionException;
import work.sam.expensesApp.repository.DescriptionRepository;

import java.time.LocalDateTime;

@Service
@Transactional
public class DescriptionServiceImpl implements DescriptionService{

    private final DescriptionRepository descriptionRepository;

    @Autowired
    public DescriptionServiceImpl(DescriptionRepository descriptionRepository) {
        this.descriptionRepository = descriptionRepository;
    }

    //Create
    public Description createDescription(Description description) {
        try {
            return descriptionRepository.save(description);
        } catch (Exception e) {
            throw new DescriptionException("Failed to create description " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Find

    public Description findDescriptionById(Long descriptionId) {
        return descriptionRepository.findById(descriptionId)
                .orElseThrow(() ->
                        new DescriptionException("Description with id : " + descriptionId + " not found", HttpStatus.NOT_FOUND));
    }


    //Update

    public Description updateDescriptionById(Long descriptionId, Description updateDescription) {
        Description description = descriptionRepository.findById(descriptionId)
                .orElseThrow(() -> new DescriptionException("Failed to find the description id with id " + descriptionId, HttpStatus.NOT_FOUND));
        description.setText(updateDescription.getText());
        description.setUpdatedAt(LocalDateTime.now());
        return descriptionRepository.save(description);
    }
    //Delete
    public void deleteDescriptionById(Long descriptionId) {
        if(!descriptionRepository.existsById(descriptionId)) {
            throw new DescriptionException("Id " + descriptionId + " to delete not found", HttpStatus.NOT_FOUND);
        }
        try {
            descriptionRepository.deleteById(descriptionId);
        } catch(Exception e) {
            throw new DescriptionException("Failed to delete the description with ID " + descriptionId,
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
