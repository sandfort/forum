package us.jsand.forum;

public class UserRepository {
    public User create(User user) {
        user.setId(1L);
        return user;
    }
}
