package us.jsand.forum;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserRepositoryTest {
    private UserRepository repo;

    @Before
    public void setUp() {
        this.repo = new UserRepository();
    }

    @Test
    public void create_returnsCreatedUserWithId() {
        User created = repo.create(new User("user-name"));

        assertTrue(created.getId() != null);
    }

    @Test
    public void get_returnsUser() {
        User user1 = new User("user 1");
        User user2 = new User("user 2");

        Long user1Id = repo.create(user1).getId();
        repo.create(user2);

        User received = repo.get(user1Id);

        assertEquals(user1.getName(), received.getName());
    }

    @Test
    public void delete_removesUser() {
        User user = new User("user-name");
        Long userId = repo.create(user).getId();

        boolean result = repo.delete(userId);

        assertTrue(result);
        assertTrue(repo.get(userId) == null);
    }

    @Test
    public void delete_ifUserDoesNotExist_returnsFalse() {
        assertFalse(repo.delete(7L));
    }
}
