package bvp.bvphackthon;

import android.app.Application;
import android.content.Context;

import com.parse.Parse;
import com.parse.ParseObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bvp.bvphackthon.models.Post;

public class BVPHackthonApplication extends Application {

    private final static String PARSE_KEY = "MB6SkXJCcCxSln9FkqYgdIt8dgs0CCjuRoOfRmbS";
    private final static String PARSE_SECRET = "WFSmGVLIiAGtq9FIAFtMTEXwqLEav0zBDXD1G3ap";
    private static Context context;
    private static Map<String, ParseObject> parseObjectCache = new HashMap<String, ParseObject>();
    private static List<Post> claimedPostsCache = new ArrayList<Post>();

    @Override
    public void onCreate() {
        super.onCreate();
        BVPHackthonApplication.context = this;

        ParseObject.registerSubclass(Post.class);
        Parse.initialize(this, PARSE_KEY, PARSE_SECRET);
    }

    //TODO: Replace with Parse Local Datastore pattern
    public static void putInCache(String objectId, ParseObject parseObject) {
        parseObjectCache.put(objectId, parseObject);
    }

    public static ParseObject readFromCache(String objectId) {
        return parseObjectCache.get(objectId);
    }

    public static void putInClaimedPostsCache(Post post) {
        claimedPostsCache.add(post);
    }

    public static ParseObject readFromClaimedCache() {
        return claimedPostsCache.get(0);
    }


}
