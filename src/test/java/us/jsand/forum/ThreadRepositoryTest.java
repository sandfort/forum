package us.jsand.forum;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertTrue;

public class ThreadRepositoryTest {
    ThreadRepository repo;

    @Before
    public void setUp() {
        this.repo = new ThreadRepository();
    }

    @Test
    public void save_givenNewThread_returnsCreatedThread() {
        Thread thread = new Thread("thread-title");

        assertTrue(repo.save(thread).getId() != null);
    }

    @Test
    public void save_givenExistingThread_returnsUpdatedThread() {
        Thread thread = new Thread("thread-title");
        Thread created = repo.save(thread);
        created.setTitle("new-thread-title");
        Thread updated = repo.save(thread);

        assertThat(updated.getId(), equalTo(created.getId()));
        assertThat(updated.getTitle(), equalTo("new-thread-title"));
    }

    @Test
    public void findOne_givenExistingThreadId_returnsThread() {
        Thread t1 = repo.save(new Thread("first-thread"));
        Thread t2 = repo.save(new Thread("second-thread"));
        Thread t3 = repo.save(new Thread("third-thread"));

        assertThat(repo.findOne(t1.getId()), equalTo(t1));
        assertThat(repo.findOne(t2.getId()), equalTo(t2));
        assertThat(repo.findOne(t3.getId()), equalTo(t3));
    }

    @Test
    public void findAll_returnsAllThreads() {
        Thread t1 = repo.save(new Thread("first-thread"));
        Thread t2 = repo.save(new Thread("second-thread"));
        Thread t3 = repo.save(new Thread("third-thread"));

        assertThat(repo.findAll(), containsInAnyOrder(t1, t2, t3));
    }

    @Test
    public void count_returnsNumberOfThreadsInRepository() {
        assertThat(repo.count(), equalTo(0L));
        repo.save(new Thread("first-thread"));
        assertThat(repo.count(), equalTo(1L));
        repo.save(new Thread("second-thread"));
        assertThat(repo.count(), equalTo(2L));
        repo.save(new Thread("third-thread"));
        assertThat(repo.count(), equalTo(3L));
    }

    @Test
    public void delete_removesThreadFromRepository() {
        Thread thread = new Thread("thread-title");
        Thread saved = repo.save(thread);

        repo.delete(saved.getId());

        assertThat(repo.findOne(saved.getId()), nullValue());
    }

    @Test
    public void exists_givenExistantThreadId_returnsTrue() {
        Thread thread = new Thread("thread-title");
        Thread saved = repo.save(thread);

        assertThat(repo.exists(saved.getId()), equalTo(true));
    }

    @Test
    public void exists_givenNonexistantThreadId_returnsFalse() {
        assertThat(repo.exists(7L), equalTo(false));
    }
}
