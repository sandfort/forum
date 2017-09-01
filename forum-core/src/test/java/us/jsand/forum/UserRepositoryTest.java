package us.jsand.forum;

import org.junit.Test;

import static org.junit.Assert.*;

public class UserRepositoryTest {
    @Test
    public void create_returnsCreatedUserWithId() {
        UserRepository repo = new UserRepository();

        User created = repo.create(new User("user-name"));

        assertTrue(created.getId() != null);
    }

    @Test
    public void get_returnsUser() {
        UserRepository repo = new UserRepository();

        User user1 = new User("user 1");
        User user2 = new User("user 2");

        Long user1Id = repo.create(user1).getId();
        repo.create(user2);

        User received = repo.get(user1Id);

        assertEquals(user1.getName(), received.getName());
    }
}
