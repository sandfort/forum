package us.jsand.forum;

public class PostThreadUseCase {
    private ThreadRepository threadRepository;
    private UserRepository userRepository;

    public PostThreadUseCase(ThreadRepository threadRepository, UserRepository userRepository) {
        this.threadRepository = threadRepository;
        this.userRepository = userRepository;
    }

    public Thread perform(Thread thread, User user) {
        if (threadRepository.exists(thread.getId())) {
            throw new RuntimeException("A thread with this ID already exists.");
        }

        if (!userRepository.exists(user.getId())) {
            throw new RuntimeException("This user does not exist.");
        }

        thread.setId(1L);
        thread.setOriginalPoster(user);
        return thread;
    }
}
