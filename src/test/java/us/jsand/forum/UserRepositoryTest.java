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
    public void save_givenNewUser_returnsCreatedUserWithId() {
        User created = repo.save(new User("user-name"));

        assertTrue(created.getId() != null);
    }

    @Test
    public void findOne_givenExistingUserId_returnsUser() {
        User user1 = new User("user 1");
        User user2 = new User("user 2");

        Long user1Id = repo.save(user1).getId();
        repo.save(user2);

        User received = repo.findOne(user1Id);

        assertEquals(user1.getName(), received.getName());
    }

    @Test
    public void delete_removesUser() {
        User user = new User("user-name");
        Long userId = repo.save(user).getId();

        boolean result = repo.delete(userId);

        assertTrue(result);
        assertTrue(repo.findOne(userId) == null);
    }
}
