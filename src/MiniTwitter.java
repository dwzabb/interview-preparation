import java.util.*;

public class MiniTwitter {
    
    HashMap<Integer, HashSet<Integer>> friendMap;
    HashMap<Integer, ArrayList<Tweet>> tweetMap;
    int globalId;

    /** Initialize your data structure here. */
    public MiniTwitter() {
        friendMap = new HashMap<>();
        tweetMap = new HashMap<>();
        globalId = 0;
    }
    
    /** Compose a new tweet. */
    public void postTweet(int userId, int tweetId) {
        if (!tweetMap.containsKey(userId)) {
            tweetMap.put(userId, new ArrayList<Tweet>());
        }
        tweetMap.get(userId).add(new Tweet(tweetId, ++globalId));
    }
    
    /** Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent. */
    public List<Integer> getNewsFeed(int userId) {
        
        List<List<Tweet>> timelineList = new ArrayList<>();
        if (friendMap.containsKey(userId)) {
            for (int followee: friendMap.get(userId)) {
                if (tweetMap.containsKey(followee)) {
                    timelineList.add(tweetMap.get(followee));    
                }
            }
        }
        
        if (tweetMap.containsKey(userId)) {
            timelineList.add(tweetMap.get(userId));
        }
        
        System.out.println("----- timlineList -----");
        for (List<Tweet> timeline: timelineList) {
        	for (Tweet tweet: timeline) {
        		System.out.print("tweet id: "+tweet.id + " gid: "+tweet.gId + ", ");
        	}
        	System.out.println();
        }
        
        return mergeTimelineList(timelineList);
    }
    
    private List<Integer> mergeTimelineList(List<List<Tweet>> timelineList) {
        PriorityQueue<Element> heap = new PriorityQueue<Element>();
        for (int i = 0; i < timelineList.size(); i++) {
            List<Tweet> timeline = timelineList.get(i);
            heap.offer(new Element(i, 0, timeline.get(0)));
        }
        
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            if (heap.isEmpty()) {
                break;
            }
            Element current = heap.poll();
            result.add(current.tweet.id);
            if (current.col < timelineList.get(current.row).size() - 1) {
                heap.offer(new Element(current.row, current.col + 1, timelineList.get(current.row).get(current.col + 1)));
            }
        }
        return result;
    }
    
    /** Follower follows a followee. If the operation is invalid, it should be a no-op. */
    public void follow(int followerId, int followeeId) {
        if (!friendMap.containsKey(followerId)) {
            friendMap.put(followerId, new HashSet<Integer>());
        }
        friendMap.get(followerId).add(followeeId);
    }
    
    /** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
    public void unfollow(int followerId, int followeeId) {
        if (!friendMap.containsKey(followerId)) {
            return;
        }
        friendMap.get(followerId).remove(followeeId);
    }
}

class Element implements Comparable<Element>{
    int row, col;
    Tweet tweet;
    public Element(int row, int col, Tweet tweet) {
        this.row = row;
        this.col = col;
        this.tweet = tweet;
    }
    
    @Override
    public int compareTo(Element another) {
        //return another.tweet.gId - this.tweet.gId;
        return this.tweet.gId - another.tweet.gId;
    }
}

class Tweet {
    int id, gId;
    public Tweet(int id, int gId) {
        this.id = id;
        this.gId = gId;
    }
}

/**
 * Your Twitter object will be instantiated and called as such:
 * Twitter obj = new Twitter();
 * obj.postTweet(userId,tweetId);
 * List<Integer> param_2 = obj.getNewsFeed(userId);
 * obj.follow(followerId,followeeId);
 * obj.unfollow(followerId,followeeId);
 */