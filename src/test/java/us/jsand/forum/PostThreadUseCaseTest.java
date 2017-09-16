package us.jsand.forum;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertNotNull;

public class PostThreadUseCaseTest {

    private ThreadRepository threadRepository;
    private UserRepository userRepository;
    private PostThreadUseCase useCase;

    @Before
    public void setUp() {
        threadRepository = new ThreadRepository();
        userRepository = new UserRepository();
        useCase = new PostThreadUseCase(threadRepository, userRepository);
    }

    @Test
    public void perform_givenNewThreadAndExistingUser_returnsThreadWithIdAndPoster() {
        User user = userRepository.save(new User("user name"));
        Thread thread = new Thread("thread title");

        Thread result = useCase.perform(thread, user);

        assertNotNull(result.getId());
        assertThat(result.getTitle(), equalTo(thread.getTitle()));
        assertThat(thread.getOriginalPoster(), equalTo(user));
    }

    @Test(expected = RuntimeException.class)
    public void perform_givenExistingThread_throwsException() {
        Thread thread = threadRepository.save(new Thread("thread title"));

        useCase.perform(thread, new User("user name"));
    }

    @Test(expected = RuntimeException.class)
    public void perform_givenNewUser_throwsException() {
        useCase.perform(new Thread("thread title"), new User("user name"));
    }
}
