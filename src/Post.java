import java.time.LocalDateTime;

public class Post {
    private final String postId;
    private final String content;
    private final User user;
    private final LocalDateTime postTime;
    private int likes;
    private int dislikes;

    public Post(String postId, String content, User user) {
        this.postId = postId;
        this.content = content;
        this.user = user;
        this.postTime = LocalDateTime.now();
        this.likes = 0;
        this.dislikes = 0;
    }

    public String getPostId() {
        return postId;
    }

    public String getContent() {
        return content;
    }

    public User getUser() {
        return user;
    }

    public LocalDateTime getPostTime() {
        return postTime;
    }

    public int getLikes() {
        return likes;
    }

    public int getDislikes() {
        return dislikes;
    }

    public void like() {
        likes++;
    }

    public void dislike() {
        dislikes++;
    }
}
