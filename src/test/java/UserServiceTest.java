import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
public class UserServiceTest {
    UserService service;
    User user;

    @BeforeEach
    public void initialize() {
        service = new UserService();
        user = new User("bob", "password", "bob@gmail.com");
        service.registerUser(user);
    }

    @AfterEach
    public void reset() {
        service = null;
        user = null;
    }
    @Test
    public void testUserNew() {
        User newUser = new User("jimmy", "password", "jimmy@gmail.com");
        assertTrue(service.registerUser(newUser));
    }
    @Test
    public void testUserOld() {
        assertFalse(service.registerUser(user));
    }

    @Test
    public void testUserNull() {
        service.equals(null);
    }

    @Test
    public void testUser() {
        User user = service.loginUser("bob", "password");
        assertNotNull(user);
        assertEquals(user, user);
    }

    @Test
    public void testUserWrongUsername() {
        assertNull(service.loginUser("no", "password"));
    }

    @Test
    public void testUserWrongPassword() {
        assertNull(service.loginUser("bob", "no"));
    }
}