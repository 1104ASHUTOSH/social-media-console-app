import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class SocialMediaApp {
    private final Map<Integer, User> users;
    private final Map<String, Post> posts;
    private int postIdCounter;

    public SocialMediaApp() {
        users = new HashMap<>();
        posts = new HashMap<>();
        postIdCounter = 1;
    }

    // User Registration
    public String registerUser(int userId, String userName) {
        if (users.containsKey(userId)) return "User ID already exists!";
        User newUser = new User(userId, userName);
        users.put(userId, newUser);
        return userName + " Registered!!";
    }

    // Upload a Post
    public String uploadPost(int userId, String content) {
        if (!users.containsKey(userId)) return "User not found!";
        String postId = String.format("%03d", postIdCounter++);
        Post newPost = new Post(postId, content, users.get(userId));
        users.get(userId).addPost(newPost);
        posts.put(postId, newPost);
        return "Upload Successful with post id: " + postId;
    }

    // Follow/Unfollow Users
    public String interactWithUser(String interactionType, int userId1, int userId2) {
        if (!users.containsKey(userId1) || !users.containsKey(userId2)) return "User not found!";
        User user1 = users.get(userId1);
        User user2 = users.get(userId2);
        if (interactionType.equalsIgnoreCase("FOLLOW")) {
            user1.follow(user2);
            return "Followed " + user2.getUserName() + "!!";
        } else if (interactionType.equalsIgnoreCase("UNFOLLOW")) {
            user1.unfollow(user2);
            return "Unfollowed " + user2.getUserName() + "!!";
        }
        return "Invalid interaction type!";
    }

    // Show Feed for a User
    public String showFeed(int userId) {
        if (!users.containsKey(userId)) return "User not found!";
        User user = users.get(userId);
        List<Post> feed = new ArrayList<>();

        // Add posts from followed users first, then others
        for (User followedUser : user.getFollowing()) feed.addAll(followedUser.getPosts());
        for (User otherUser : users.values()) {
            if (!user.isFollowing(otherUser) && otherUser != user) feed.addAll(otherUser.getPosts());
        }

        // Sort feed by posting time
        feed.sort(Comparator.comparing(Post::getPostTime).reversed());

        // Build feed display
        StringBuilder feedDisplay = new StringBuilder();
        for (Post post : feed) {
            feedDisplay.append("UserName - ").append(post.getUser().getUserName()).append("\n")
                    .append("Post - ").append(post.getContent()).append("\n")
                    .append("Post time - ").append(getRelativeTime(post.getPostTime())).append("\n")
                    .append("# of Likes - ").append(post.getLikes()).append("\n")
                    .append("# of Dislikes - ").append(post.getDislikes()).append("\n\n");
        }
        return feedDisplay.toString();
    }

    // Like/Dislike Post
    public String interactWithPost(String interactionType, int userId, String postId) {
        if (!posts.containsKey(postId)) return "Post not found!";
        Post post = posts.get(postId);
        if (interactionType.equalsIgnoreCase("LIKE")) {
            post.like();
            return "Post Liked!!";
        } else if (interactionType.equalsIgnoreCase("DISLIKE")) {
            post.dislike();
            return "Post Disliked!!";
        }
        return "Invalid interaction type!";
    }

    // Helper for relative time
    private String getRelativeTime(LocalDateTime dateTime) {
        LocalDateTime now = LocalDateTime.now();
        long minutes = ChronoUnit.MINUTES.between(dateTime, now);
        if (minutes < 60) return minutes + "m ago";
        long hours = ChronoUnit.HOURS.between(dateTime, now);
        if (hours < 24) return hours + "h ago";
        long days = ChronoUnit.DAYS.between(dateTime, now);
        return days + "d ago";
    }
}
