package bvp.bvphackthon;

import android.app.Application;
import android.content.Context;

import com.parse.Parse;
import com.parse.ParseObject;

import java.util.HashMap;
import java.util.Map;

import bvp.bvphackthon.models.Post;

public class BVPHackthonApplication extends Application {

    private final static String PARSE_KEY = "MB6SkXJCcCxSln9FkqYgdIt8dgs0CCjuRoOfRmbS";
    private final static String PARSE_SECRET = "WFSmGVLIiAGtq9FIAFtMTEXwqLEav0zBDXD1G3ap";
    private static Context context;
    private static Map<String, ParseObject> parseObjectCache = new HashMap<String, ParseObject>();

    @Override
    public void onCreate() {
        super.onCreate();
        BVPHackthonApplication.context = this;

        ParseObject.registerSubclass(Post.class);
        Parse.initialize(this, PARSE_KEY, PARSE_SECRET);
    }

}
