import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class User {
    private final int userId;
    private final String userName;
    private final List<Post> posts;
    private final Set<User> following;

    public User(int userId, String userName) {
        this.userId = userId;
        this.userName = userName;
        this.posts = new ArrayList<>();
        this.following = new HashSet<>();
    }

    public int getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public List<Post> getPosts() {
        return new ArrayList<>(posts); // Encapsulation: Return a copy
    }

    public Set<User> getFollowing() {
        return new HashSet<>(following); // Encapsulation: Return a copy
    }

    public void addPost(Post post) {
        posts.add(post);
    }

    public void follow(User user) {
        following.add(user);
    }

    public void unfollow(User user) {
        following.remove(user);
    }

    public boolean isFollowing(User user) {
        return following.contains(user);
    }
}
