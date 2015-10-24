package bvp.bvphackthon.models;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;

import java.io.Serializable;

@ParseClassName("Post")
public class Post extends ParseObject implements Serializable {
    private static final String DESCRIPTION_KEY = "description";
    private static final String NOFEEDERS_KEY  = "number_will_feed";
    private static final String PHOTO_KEY = "photo";
    private static final String STATUS_KEY = "status";
    private static final String TITLE_KEY = "title";
    private static final String ADDRESS_KEY = "address";

    public String getStatus() {
        return getString(STATUS_KEY);
    }

    public String getDescription() {
        return getString(DESCRIPTION_KEY);
    }

    public String getTitle() {
        return getString(TITLE_KEY);
    }

    public String getAddress() {
        return getString(ADDRESS_KEY);
    }

    public ParseFile getPhoto() {
        return getParseFile(PHOTO_KEY);
    }

    public int getNumberOfFeeders() {
        return getInt(NOFEEDERS_KEY);
    }

}
