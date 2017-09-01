package us.jsand.forum;

import org.junit.Test;

import static org.junit.Assert.*;

public class UserRepositoryTest {
    @Test
    public void create_returnsCreatedUserWithId() {
        UserRepository repo = new UserRepository();

        User created = repo.create(new User());

        assertTrue(created.getId() != null);
    }
}
