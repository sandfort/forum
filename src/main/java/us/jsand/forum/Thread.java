package us.jsand.forum;

public class Thread {
    private Long id;
    private String title;
    private User originalPoster;

    public Thread(String title, User originalPoster) {
        this.title = title;
        this.originalPoster = originalPoster;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public User getOriginalPoster() {
        return originalPoster;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
