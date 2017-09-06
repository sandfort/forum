package us.jsand.forum;

import java.util.Map;
import java.util.HashMap;

public class UserRepository {
    private Map<Long, User> users;
    private Long sequence;

    public UserRepository() {
        this.users = new HashMap<>();
        this.sequence = 0L;
    }

    public User create(User user) {
        user.setId(sequence++);

        users.put(user.getId(), user);

        return user;
    }

    public User get(Long id) {
        return users.get(id);
    }
}
