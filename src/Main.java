public class Main {
    public static void main(String[] args) {
        SocialMediaApp app = new SocialMediaApp();

        // Test Case 1: Register Users
        System.out.println(app.registerUser(1, "Akash")); // Expected: "Akash Registered!!"
        System.out.println(app.registerUser(2, "Hemant")); // Expected: "Hemant Registered!!"
        System.out.println(app.registerUser(1, "Rohit"));  // Expected: "User ID already exists!"

        // Test Case 2: Upload Posts
        System.out.println(app.uploadPost(1, "This is my first post. My name is Akash")); // Expected: "Upload Successful with post id: 001"
        System.out.println(app.uploadPost(1, "I work at Flipkart as a SDE1")); // Expected: "Upload Successful with post id: 002"
        System.out.println(app.uploadPost(2, "I too worked at Flipkart as a SDE1")); // Expected: "Upload Successful with post id: 003"
        System.out.println(app.uploadPost(3, "This post shouldn't upload")); // Expected: "User not found!"

        // Test Case 3: Follow/Unfollow Users
        System.out.println(app.interactWithUser("FOLLOW", 2, 1)); // Expected: "Followed Akash!!"
        System.out.println(app.interactWithUser("UNFOLLOW", 2, 1)); // Expected: "Unfollowed Akash!!"
        System.out.println(app.interactWithUser("FOLLOW", 2, 3)); // Expected: "User not found!"
        System.out.println(app.interactWithUser("BLOCK", 2, 1));   // Expected: "Invalid interaction type!"

        // Test Case 4: Like/Dislike Posts
        System.out.println(app.interactWithPost("LIKE", 1, "001")); // Expected: "Post Liked!!"
        System.out.println(app.interactWithPost("DISLIKE", 1, "001")); // Expected: "Post Disliked!!"
        System.out.println(app.interactWithPost("LIKE", 1, "999")); // Expected: "Post not found!"
        System.out.println(app.interactWithPost("LOVE", 1, "001")); // Expected: "Invalid interaction type!"

        // Test Case 5: Show Feed for User 1
        System.out.println("Feed for User 1:");
        System.out.println(app.showFeed(1)); // Expected: Feed display with Akash and Hemant's posts

        // Test Case 6: Show Feed for User 2 (after following User 1)
        app.interactWithUser("FOLLOW", 2, 1);
        System.out.println("Feed for User 2:");
        System.out.println(app.showFeed(2)); // Expected: Feed display with Akash's posts appearing first

        // Test Case 7: Show Feed for Non-Existent User
        System.out.println(app.showFeed(3)); // Expected: "User not found!"
    }
}
