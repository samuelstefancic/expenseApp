package work.sam.expensesApp.DTOTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import work.sam.expensesApp.DTO.UserDTO;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserDTOTest {
    private UserDTO userDTO;
    @BeforeEach
    public void setUp() {
        userDTO = new UserDTO();
        userDTO.setFirstName("John");
        userDTO.setLastname("Doe");
        userDTO.setAge(30);
        userDTO.setLocation("New York");
        userDTO.setIncome(new BigDecimal("50000"));
    }

    @Test
    public void testUserDTOProperties() {
        assertEquals("John", userDTO.getFirstName());
        assertEquals("Doe", userDTO.getLastname());
        assertEquals(30, userDTO.getAge());
        assertEquals("New York", userDTO.getLocation());
        assertEquals(new BigDecimal("50000"), userDTO.getIncome());
    }
}
