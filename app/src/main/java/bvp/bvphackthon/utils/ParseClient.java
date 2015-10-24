package bvp.bvphackthon.utils;

import java.util.List;
import java.util.Map;

import android.util.Log;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import bvp.bvphackthon.models.Post;

public class ParseClient {

    public static <T extends ParseObject> void getAll(Class<T> classObj, FindCallback<T> callback) {
        ParseQuery<T> query = ParseQuery.getQuery(classObj);
        query.findInBackground(callback);
    }

    public static <T extends ParseObject> List<T> getAllSync(Class<T> classObj) {
        try {
            ParseQuery<T> query = ParseQuery.getQuery(classObj);
            return query.find();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void markPostAsClaimed(Post post) {
        post.markClaimed();
        post.saveInBackground();
    }

}
